import { useState, useEffect } from 'react';
import { attendanceAPI } from '../services/api';
import '../styles/Attendance.css';

export default function Attendance() {
  const [attendanceRecords, setAttendanceRecords] = useState([]);
  const [showMarkForm, setShowMarkForm] = useState(false);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');
  const [filterType, setFilterType] = useState('all');
  const [filterId, setFilterId] = useState('');

  const [markData, setMarkData] = useState({
    studentId: '',
    teacherId: '',
    markId: '',
    attendanceDate: new Date().toISOString().split('T')[0],
    checkInTime: '',
    checkOutTime: '',
    status: 'PRESENT',
  });

  // Fetch attendance records
  useEffect(() => {
    loadAttendance();
  }, []);

  const loadAttendance = async () => {
    try {
      setLoading(true);
      const data = await attendanceAPI.getAll();
      setAttendanceRecords(data);
      setError('');
    } catch (err) {
      setError(`Failed to load attendance: ${err.message}`);
    } finally {
      setLoading(false);
    }
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setMarkData({ ...markData, [name]: value });
  };

  const handleMarkAttendance = async (e) => {
    e.preventDefault();
    try {
      setLoading(true);
      await attendanceAPI.mark(markData);
      setSuccess('Attendance marked successfully!');
      setMarkData({
        studentId: '',
        teacherId: '',
        markId: '',
        attendanceDate: new Date().toISOString().split('T')[0],
        checkInTime: '',
        checkOutTime: '',
        status: 'PRESENT',
      });
      setShowMarkForm(false);
      loadAttendance();
      setTimeout(() => setSuccess(''), 3000);
    } catch (err) {
      setError(`Error marking attendance: ${err.message}`);
    } finally {
      setLoading(false);
    }
  };

  const handleCheckOut = async (id) => {
    if (window.confirm('Mark this attendance as checked out?')) {
      try {
        setLoading(true);
        await attendanceAPI.checkOut(id);
        setSuccess('Checked out successfully!');
        loadAttendance();
        setTimeout(() => setSuccess(''), 3000);
      } catch (err) {
        setError(`Error checking out: ${err.message}`);
      } finally {
        setLoading(false);
      }
    }
  };

  const handleFilter = async () => {
    if (!filterId) {
      loadAttendance();
      return;
    }

    try {
      setLoading(true);
      let data;
      if (filterType === 'student') {
        data = await attendanceAPI.getStudentAttendance(filterId);
      } else if (filterType === 'teacher') {
        data = await attendanceAPI.getTeacherAttendance(filterId);
      }
      setAttendanceRecords(data);
      setError('');
    } catch (err) {
      setError(`Failed to filter: ${err.message}`);
    } finally {
      setLoading(false);
    }
  };

  const getStatusBadgeClass = (status) => {
    switch (status) {
      case 'PRESENT':
        return 'badge-success';
      case 'ABSENT':
        return 'badge-danger';
      default:
        return 'badge-secondary';
    }
  };

  return (
    <div className="attendance-container">
      <h1>Attendance Management</h1>

      {error && <div className="alert alert-error">{error}</div>}
      {success && <div className="alert alert-success">{success}</div>}

      <div className="attendance-controls">
        <button
          className="btn btn-primary"
          onClick={() => setShowMarkForm(!showMarkForm)}
        >
          {showMarkForm ? 'Cancel' : '+ Mark Attendance'}
        </button>

        <div className="filter-group">
          <select value={filterType} onChange={(e) => setFilterType(e.target.value)}>
            <option value="all">All Records</option>
            <option value="student">Filter by Student</option>
            <option value="teacher">Filter by Teacher</option>
          </select>

          {filterType !== 'all' && (
            <>
              <input
                type="number"
                placeholder={filterType === 'student' ? 'Enter Student ID' : 'Enter Teacher ID'}
                value={filterId}
                onChange={(e) => setFilterId(e.target.value)}
              />
              <button className="btn btn-secondary" onClick={handleFilter}>
                Filter
              </button>
              <button
                className="btn btn-secondary"
                onClick={() => {
                  setFilterId('');
                  setFilterType('all');
                  loadAttendance();
                }}
              >
                Reset
              </button>
            </>
          )}
        </div>
      </div>

      {showMarkForm && (
        <form className="mark-attendance-form" onSubmit={handleMarkAttendance}>
          <h2>Mark Attendance</h2>

          <div className="form-grid">
            <div className="form-group">
              <label>Student ID *</label>
              <input
                type="number"
                name="studentId"
                value={markData.studentId}
                onChange={handleInputChange}
                required
              />
            </div>

            <div className="form-group">
              <label>Teacher ID *</label>
              <input
                type="number"
                name="teacherId"
                value={markData.teacherId}
                onChange={handleInputChange}
                required
              />
            </div>

            <div className="form-group">
              <label>Mark ID (Optional)</label>
              <input
                type="number"
                name="markId"
                value={markData.markId}
                onChange={handleInputChange}
              />
            </div>

            <div className="form-group">
              <label>Attendance Date *</label>
              <input
                type="date"
                name="attendanceDate"
                value={markData.attendanceDate}
                onChange={handleInputChange}
                required
              />
            </div>

            <div className="form-group">
              <label>Check In Time</label>
              <input
                type="time"
                name="checkInTime"
                value={markData.checkInTime}
                onChange={handleInputChange}
              />
            </div>

            <div className="form-group">
              <label>Check Out Time</label>
              <input
                type="time"
                name="checkOutTime"
                value={markData.checkOutTime}
                onChange={handleInputChange}
              />
            </div>

            <div className="form-group">
              <label>Status *</label>
              <select name="status" value={markData.status} onChange={handleInputChange} required>
                <option value="PRESENT">Present</option>
                <option value="ABSENT">Absent</option>
              </select>
            </div>
          </div>

          <button type="submit" className="btn btn-success" disabled={loading}>
            {loading ? 'Marking...' : 'Mark Attendance'}
          </button>
        </form>
      )}

      <div className="attendance-list">
        <h2>Attendance Records ({attendanceRecords.length})</h2>
        {loading ? (
          <p>Loading...</p>
        ) : attendanceRecords.length === 0 ? (
          <p>No attendance records found</p>
        ) : (
          <div className="table-responsive">
            <table>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Student ID</th>
                  <th>Teacher ID</th>
                  <th>Date</th>
                  <th>Check In</th>
                  <th>Check Out</th>
                  <th>Status</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {attendanceRecords.map((record) => (
                  <tr key={record.attendanceId}>
                    <td>{record.attendanceId}</td>
                    <td>{record.studentId}</td>
                    <td>{record.teacherId}</td>
                    <td>{record.attendanceDate}</td>
                    <td>{record.checkInTime || '-'}</td>
                    <td>{record.checkOutTime || '-'}</td>
                    <td>
                      <span className={`badge ${getStatusBadgeClass(record.status)}`}>
                        {record.status}
                      </span>
                    </td>
                    <td>
                      {!record.checkOutTime && (
                        <button
                          className="btn btn-sm btn-info"
                          onClick={() => handleCheckOut(record.attendanceId)}
                        >
                          Check Out
                        </button>
                      )}
                      {record.checkOutTime && (
                        <span className="text-muted">Checked Out</span>
                      )}
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  );
}
