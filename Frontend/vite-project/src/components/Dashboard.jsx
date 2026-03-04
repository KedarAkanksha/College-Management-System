import '../styles/Dashboard.css';

export default function Dashboard() {
  return (
    <div className="dashboard-container">
      <div className="dashboard-header">
        <h1>College Management System</h1>
        <p>Manage Teachers and Attendance Efficiently</p>
      </div>

      <div className="dashboard-cards">
        <div className="card">
          <div className="card-icon">👨‍🏫</div>
          <div className="card-content">
            <h3>Teachers Management</h3>
            <p>Register, update, and manage teacher information</p>
            <ul>
              <li>Register new teachers</li>
              <li>Update teacher details</li>
              <li>View all teachers</li>
              <li>Delete teachers</li>
            </ul>
          </div>
        </div>

        <div className="card">
          <div className="card-icon">✅</div>
          <div className="card-content">
            <h3>Attendance Management</h3>
            <p>Track attendance records and manage check-in/check-out</p>
            <ul>
              <li>Mark attendance</li>
              <li>Check in and check out</li>
              <li>Filter by student or teacher</li>
              <li>View detailed records</li>
            </ul>
          </div>
        </div>
      </div>

      <div className="features">
        <h2>Key Features</h2>
        <div className="features-grid">
          <div className="feature">
            <h4>Real-time Data</h4>
            <p>All data is synced in real-time with the backend</p>
          </div>
          <div className="feature">
            <h4>Easy Navigation</h4>
            <p>Navigate between teachers and attendance management with ease</p>
          </div>
          <div className="feature">
            <h4>Quick Actions</h4>
            <p>Edit, delete, and update records quickly</p>
          </div>
          <div className="feature">
            <h4>Filtering</h4>
            <p>Filter attendance records by student or teacher</p>
          </div>
        </div>
      </div>
    </div>
  );
}
