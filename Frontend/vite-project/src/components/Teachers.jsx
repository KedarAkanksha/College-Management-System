import { useState, useEffect } from 'react';
import { teacherAPI } from '../services/api';
import '../styles/Teachers.css';

export default function Teachers() {
  const [teachers, setTeachers] = useState([]);
  const [showForm, setShowForm] = useState(false);
  const [editingId, setEditingId] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');

  const [formData, setFormData] = useState({
    fullName: '',
    email: '',
    mobile: '',
    gender: '',
    dob: '',
    qualification: '',
    experience: '',
    department: '',
    subject: '',
    salary: '',
    aadhaarNumber: '',
    address: '',
    joiningDate: '',
    status: 'ACTIVE',
  });

  // Fetch all teachers
  useEffect(() => {
    loadTeachers();
  }, []);

  const loadTeachers = async () => {
    try {
      setLoading(true);
      const data = await teacherAPI.getAll();
      setTeachers(data);
      setError('');
    } catch (err) {
      setError(`Failed to load teachers: ${err.message}`);
    } finally {
      setLoading(false);
    }
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      setLoading(true);
      if (editingId) {
        await teacherAPI.update(editingId, formData);
        setSuccess('Teacher updated successfully!');
      } else {
        await teacherAPI.register(formData);
        setSuccess('Teacher registered successfully!');
      }
      setFormData({
        fullName: '',
        email: '',
        mobile: '',
        gender: '',
        dob: '',
        qualification: '',
        experience: '',
        department: '',
        subject: '',
        salary: '',
        aadhaarNumber: '',
        address: '',
        joiningDate: '',
        status: 'ACTIVE',
      });
      setShowForm(false);
      setEditingId(null);
      loadTeachers();
      setTimeout(() => setSuccess(''), 3000);
    } catch (err) {
      setError(`Error: ${err.message}`);
    } finally {
      setLoading(false);
    }
  };

  const handleEdit = async (id) => {
    try {
      const teacher = await teacherAPI.getById(id);
      setFormData(teacher);
      setEditingId(id);
      setShowForm(true);
    } catch (err) {
      setError(`Failed to load teacher: ${err.message}`);
    }
  };

  const handleDelete = async (id) => {
    if (window.confirm('Are you sure you want to delete this teacher?')) {
      try {
        setLoading(true);
        await teacherAPI.delete(id);
        setSuccess('Teacher deleted successfully!');
        loadTeachers();
        setTimeout(() => setSuccess(''), 3000);
      } catch (err) {
        setError(`Failed to delete teacher: ${err.message}`);
      } finally {
        setLoading(false);
      }
    }
  };

  return (
    <div className="teachers-container">
      <h1>Teachers Management</h1>

      {error && <div className="alert alert-error">{error}</div>}
      {success && <div className="alert alert-success">{success}</div>}

      <button
        className="btn btn-primary"
        onClick={() => {
          setShowForm(!showForm);
          setEditingId(null);
          setFormData({
            fullName: '',
            email: '',
            mobile: '',
            gender: '',
            dob: '',
            qualification: '',
            experience: '',
            department: '',
            subject: '',
            salary: '',
            aadhaarNumber: '',
            address: '',
            joiningDate: '',
            status: 'ACTIVE',
          });
        }}
      >
        {showForm ? 'Cancel' : '+ Add New Teacher'}
      </button>

      {showForm && (
        <form className="teacher-form" onSubmit={handleSubmit}>
          <h2>{editingId ? 'Edit Teacher' : 'Register New Teacher'}</h2>

          <div className="form-grid">
            <div className="form-group">
              <label>Full Name *</label>
              <input
                type="text"
                name="fullName"
                value={formData.fullName}
                onChange={handleInputChange}
                required
              />
            </div>

            <div className="form-group">
              <label>Email *</label>
              <input
                type="email"
                name="email"
                value={formData.email}
                onChange={handleInputChange}
                required
              />
            </div>

            <div className="form-group">
              <label>Mobile *</label>
              <input
                type="tel"
                name="mobile"
                value={formData.mobile}
                onChange={handleInputChange}
                required
              />
            </div>

            <div className="form-group">
              <label>Gender</label>
              <select name="gender" value={formData.gender} onChange={handleInputChange}>
                <option value="">Select Gender</option>
                <option value="Male">Male</option>
                <option value="Female">Female</option>
                <option value="Other">Other</option>
              </select>
            </div>

            <div className="form-group">
              <label>Date of Birth</label>
              <input
                type="date"
                name="dob"
                value={formData.dob}
                onChange={handleInputChange}
              />
            </div>

            <div className="form-group">
              <label>Qualification</label>
              <input
                type="text"
                name="qualification"
                value={formData.qualification}
                onChange={handleInputChange}
              />
            </div>

            <div className="form-group">
              <label>Experience (Years)</label>
              <input
                type="number"
                name="experience"
                value={formData.experience}
                onChange={handleInputChange}
              />
            </div>

            <div className="form-group">
              <label>Department</label>
              <input
                type="text"
                name="department"
                value={formData.department}
                onChange={handleInputChange}
              />
            </div>

            <div className="form-group">
              <label>Subject</label>
              <input
                type="text"
                name="subject"
                value={formData.subject}
                onChange={handleInputChange}
              />
            </div>

            <div className="form-group">
              <label>Salary</label>
              <input
                type="number"
                name="salary"
                value={formData.salary}
                onChange={handleInputChange}
                step="0.01"
              />
            </div>

            <div className="form-group">
              <label>Aadhaar Number</label>
              <input
                type="text"
                name="aadhaarNumber"
                value={formData.aadhaarNumber}
                onChange={handleInputChange}
              />
            </div>

            <div className="form-group">
              <label>Address</label>
              <input
                type="text"
                name="address"
                value={formData.address}
                onChange={handleInputChange}
              />
            </div>

            <div className="form-group">
              <label>Joining Date</label>
              <input
                type="date"
                name="joiningDate"
                value={formData.joiningDate}
                onChange={handleInputChange}
              />
            </div>

            <div className="form-group">
              <label>Status</label>
              <select name="status" value={formData.status} onChange={handleInputChange}>
                <option value="ACTIVE">Active</option>
                <option value="INACTIVE">Inactive</option>
              </select>
            </div>
          </div>

          <button type="submit" className="btn btn-success" disabled={loading}>
            {loading ? 'Processing...' : editingId ? 'Update Teacher' : 'Register Teacher'}
          </button>
        </form>
      )}

      <div className="teachers-list">
        <h2>Teachers List ({teachers.length})</h2>
        {loading ? (
          <p>Loading...</p>
        ) : teachers.length === 0 ? (
          <p>No teachers found</p>
        ) : (
          <div className="table-responsive">
            <table>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Name</th>
                  <th>Email</th>
                  <th>Mobile</th>
                  <th>Department</th>
                  <th>Subject</th>
                  <th>Status</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {teachers.map((teacher) => (
                  <tr key={teacher.teacherId}>
                    <td>{teacher.teacherId}</td>
                    <td>{teacher.fullName}</td>
                    <td>{teacher.email}</td>
                    <td>{teacher.mobile}</td>
                    <td>{teacher.department}</td>
                    <td>{teacher.subject}</td>
                    <td>
                      <span className={`badge ${teacher.status === 'ACTIVE' ? 'badge-success' : 'badge-danger'}`}>
                        {teacher.status}
                      </span>
                    </td>
                    <td>
                      <button
                        className="btn btn-sm btn-info"
                        onClick={() => handleEdit(teacher.teacherId)}
                      >
                        Edit
                      </button>
                      <button
                        className="btn btn-sm btn-danger"
                        onClick={() => handleDelete(teacher.teacherId)}
                      >
                        Delete
                      </button>
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
