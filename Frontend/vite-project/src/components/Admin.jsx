import { useState, useEffect } from 'react';
import { teacherAPI, attendanceAPI } from '../services/api';
import '../styles/Admin.css';

export default function Admin() {
  const [stats, setStats] = useState({
    totalTeachers: 0,
    totalAttendance: 0,
    presentToday: 0,
    absentToday: 0,
    teachers: [],
    attendance: [],
  });
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [activeTab, setActiveTab] = useState('overview');

  useEffect(() => {
    loadStatistics();
  }, []);

  const loadStatistics = async () => {
    try {
      setLoading(true);
      const [teachers, attendance] = await Promise.all([
        teacherAPI.getAll(),
        attendanceAPI.getAll(),
      ]);

      const today = new Date().toISOString().split('T')[0];
      const todayAttendance = attendance.filter((a) => a.attendanceDate === today);
      const presentToday = todayAttendance.filter((a) => a.status === 'PRESENT').length;
      const absentToday = todayAttendance.filter((a) => a.status === 'ABSENT').length;

      setStats({
        totalTeachers: teachers.length,
        totalAttendance: attendance.length,
        presentToday,
        absentToday,
        teachers: teachers.slice(0, 5),
        attendance: attendance.slice(-10),
      });
      setError('');
    } catch (err) {
      setError(`Failed to load statistics: ${err.message}`);
    } finally {
      setLoading(false);
    }
  };

  const getAttendancePercentage = () => {
    const total = stats.presentToday + stats.absentToday;
    return total === 0 ? 0 : Math.round((stats.presentToday / total) * 100);
  };

  if (loading) {
    return (
      <div className="admin-container">
        <div className="loading-spinner">
          <div className="spinner"></div>
          <p>Loading Dashboard...</p>
        </div>
      </div>
    );
  }

  return (
    <div className="admin-container">
      {error && <div className="alert alert-error">{error}</div>}

      {/* Header */}
      <div className="admin-header">
        <div>
          <h1>Admin Dashboard</h1>
          <p>System Overview & Statistics</p>
        </div>
        <button className="btn btn-primary" onClick={loadStatistics}>
          🔄 Refresh
        </button>
      </div>

      {/* Tab Navigation */}
      <div className="admin-tabs">
        <button
          className={`tab-btn ${activeTab === 'overview' ? 'active' : ''}`}
          onClick={() => setActiveTab('overview')}
        >
          📊 Overview
        </button>
        <button
          className={`tab-btn ${activeTab === 'teachers' ? 'active' : ''}`}
          onClick={() => setActiveTab('teachers')}
        >
          👨‍🏫 Teachers ({stats.totalTeachers})
        </button>
        <button
          className={`tab-btn ${activeTab === 'attendance' ? 'active' : ''}`}
          onClick={() => setActiveTab('attendance')}
        >
          ✅ Attendance ({stats.totalAttendance})
        </button>
      </div>

      {/* Overview Tab */}
      {activeTab === 'overview' && (
        <div className="tab-content">
          {/* Key Statistics Cards */}
          <div className="stats-grid">
            <div className="stat-card stat-card-primary">
              <div className="stat-icon">👨‍🏫</div>
              <div className="stat-content">
                <h3>Total Teachers</h3>
                <p className="stat-number">{stats.totalTeachers}</p>
                <span className="stat-label">Registered</span>
              </div>
            </div>

            <div className="stat-card stat-card-success">
              <div className="stat-icon">✅</div>
              <div className="stat-content">
                <h3>Present Today</h3>
                <p className="stat-number">{stats.presentToday}</p>
                <span className="stat-label">Marked</span>
              </div>
            </div>

            <div className="stat-card stat-card-danger">
              <div className="stat-icon">❌</div>
              <div className="stat-content">
                <h3>Absent Today</h3>
                <p className="stat-number">{stats.absentToday}</p>
                <span className="stat-label">Not marked</span>
              </div>
            </div>

            <div className="stat-card stat-card-info">
              <div className="stat-icon">📈</div>
              <div className="stat-content">
                <h3>Attendance Rate</h3>
                <p className="stat-number">{getAttendancePercentage()}%</p>
                <span className="stat-label">Today</span>
              </div>
            </div>
          </div>

          {/* Charts Section */}
          <div className="charts-section">
            {/* Attendance Chart */}
            <div className="chart-card">
              <h3>📊 Today's Attendance</h3>
              <div className="attendance-chart">
                <div className="chart-bars">
                  <div className="bar-group">
                    <div className="bar present" style={{ height: `${getAttendancePercentage()}%` }}>
                      <span className="bar-label">{stats.presentToday}</span>
                    </div>
                    <p>Present</p>
                  </div>
                  <div className="bar-group">
                    <div className="bar absent" style={{ height: `${100 - getAttendancePercentage()}%` }}>
                      <span className="bar-label">{stats.absentToday}</span>
                    </div>
                    <p>Absent</p>
                  </div>
                </div>
              </div>
            </div>

            {/* Gender Distribution */}
            <div className="chart-card">
              <h3>👥 Teacher Distribution</h3>
              <div className="info-grid">
                <div className="info-item">
                  <span>Total Teachers</span>
                  <strong>{stats.totalTeachers}</strong>
                </div>
                <div className="info-item">
                  <span>Total Attendance Records</span>
                  <strong>{stats.totalAttendance}</strong>
                </div>
                <div className="info-item">
                  <span>Avg Records/Teacher</span>
                  <strong>
                    {stats.totalTeachers === 0
                      ? '0'
                      : (stats.totalAttendance / stats.totalTeachers).toFixed(1)}
                  </strong>
                </div>
                <div className="info-item">
                  <span>Active Today</span>
                  <strong>{stats.presentToday + stats.absentToday}</strong>
                </div>
              </div>
            </div>
          </div>

          {/* Quick Info */}
          <div className="quick-actions">
            <h3>⚡ Quick Actions</h3>
            <div className="action-buttons">
              <div className="action-btn" onClick={() => window.scrollTo(0, 0)}>
                <span className="action-icon">📋</span>
                <span>Go to Teachers</span>
              </div>
              <div className="action-btn" onClick={() => window.scrollTo(0, 0)}>
                <span className="action-icon">✅</span>
                <span>Mark Attendance</span>
              </div>
              <div className="action-btn" onClick={loadStatistics}>
                <span className="action-icon">🔄</span>
                <span>Refresh Data</span>
              </div>
              <div className="action-btn">
                <span className="action-icon">📊</span>
                <span>Export Report</span>
              </div>
            </div>
          </div>
        </div>
      )}

      {/* Teachers Tab */}
      {activeTab === 'teachers' && (
        <div className="tab-content">
          <div className="list-header">
            <h2>Recent Teachers (Last {stats.teachers.length} Added)</h2>
          </div>
          {stats.teachers.length === 0 ? (
            <div className="empty-state">
              <div className="empty-icon">👨‍🏫</div>
              <p>No teachers found</p>
              <p className="empty-hint">Add teachers from the Teachers page</p>
            </div>
          ) : (
            <div className="teachers-list-admin">
              {stats.teachers.map((teacher) => (
                <div key={teacher.teacherId} className="teacher-card-admin">
                  <div className="teacher-header">
                    <div>
                      <h4>{teacher.fullName}</h4>
                      <p className="teacher-id">ID: {teacher.teacherId}</p>
                    </div>
                    <span className={`badge ${teacher.status === 'ACTIVE' ? 'badge-success' : 'badge-danger'}`}>
                      {teacher.status}
                    </span>
                  </div>
                  <div className="teacher-details">
                    <div className="detail-item">
                      <span className="label">Email</span>
                      <span className="value">{teacher.email}</span>
                    </div>
                    <div className="detail-item">
                      <span className="label">Department</span>
                      <span className="value">{teacher.department}</span>
                    </div>
                    <div className="detail-item">
                      <span className="label">Subject</span>
                      <span className="value">{teacher.subject}</span>
                    </div>
                    <div className="detail-item">
                      <span className="label">Experience</span>
                      <span className="value">{teacher.experience} years</span>
                    </div>
                  </div>
                </div>
              ))}
            </div>
          )}
        </div>
      )}

      {/* Attendance Tab */}
      {activeTab === 'attendance' && (
        <div className="tab-content">
          <div className="list-header">
            <h2>Recent Attendance Records (Last 10)</h2>
          </div>
          {stats.attendance.length === 0 ? (
            <div className="empty-state">
              <div className="empty-icon">✅</div>
              <p>No attendance records found</p>
              <p className="empty-hint">Mark attendance from the Attendance page</p>
            </div>
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
                  </tr>
                </thead>
                <tbody>
                  {stats.attendance.map((record) => (
                    <tr key={record.attendanceId}>
                      <td>{record.attendanceId}</td>
                      <td>{record.studentId}</td>
                      <td>{record.teacherId}</td>
                      <td>{record.attendanceDate}</td>
                      <td>{record.checkInTime || '-'}</td>
                      <td>{record.checkOutTime || '-'}</td>
                      <td>
                        <span className={`badge ${record.status === 'PRESENT' ? 'badge-success' : 'badge-danger'}`}>
                          {record.status}
                        </span>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          )}
        </div>
      )}
    </div>
  );
}
