// API Service for all backend calls
const API_BASE_URL = 'http://localhost:8080/api';

// ============ TEACHER API CALLS ============

export const teacherAPI = {
  // Register a new teacher
  register: async (teacherData) => {
    const response = await fetch(`${API_BASE_URL}/teachers/register`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(teacherData),
    });
    if (!response.ok) throw new Error(await response.text());
    return response.json();
  },

  // Get all teachers
  getAll: async () => {
    const response = await fetch(`${API_BASE_URL}/teachers/all`);
    if (!response.ok) throw new Error(await response.text());
    return response.json();
  },

  // Get single teacher
  getById: async (id) => {
    const response = await fetch(`${API_BASE_URL}/teachers/${id}`);
    if (!response.ok) throw new Error(await response.text());
    return response.json();
  },

  // Update teacher
  update: async (id, teacherData) => {
    const response = await fetch(`${API_BASE_URL}/teachers/update/${id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(teacherData),
    });
    if (!response.ok) throw new Error(await response.text());
    return response.json();
  },

  // Delete teacher
  delete: async (id) => {
    const response = await fetch(`${API_BASE_URL}/teachers/delete/${id}`, {
      method: 'DELETE',
    });
    if (!response.ok) throw new Error(await response.text());
    return response.text();
  },
};

// ============ ATTENDANCE API CALLS ============

export const attendanceAPI = {
  // Mark attendance
  mark: async (attendanceData) => {
    const response = await fetch(`${API_BASE_URL}/attendance/mark`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(attendanceData),
    });
    if (!response.ok) throw new Error(await response.text());
    return response.json();
  },

  // Check out
  checkOut: async (id) => {
    const response = await fetch(`${API_BASE_URL}/attendance/checkout/${id}`, {
      method: 'PUT',
    });
    if (!response.ok) throw new Error(await response.text());
    return response.json();
  },

  // Get all attendance
  getAll: async () => {
    const response = await fetch(`${API_BASE_URL}/attendance/all`);
    if (!response.ok) throw new Error(await response.text());
    return response.json();
  },

  // Get student attendance
  getStudentAttendance: async (studentId) => {
    const response = await fetch(`${API_BASE_URL}/attendance/student/${studentId}`);
    if (!response.ok) throw new Error(await response.text());
    return response.json();
  },

  // Get teacher attendance
  getTeacherAttendance: async (teacherId) => {
    const response = await fetch(`${API_BASE_URL}/attendance/teacher/${teacherId}`);
    if (!response.ok) throw new Error(await response.text());
    return response.json();
  },
};
