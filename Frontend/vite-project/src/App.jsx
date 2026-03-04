import { useState } from 'react';
import Dashboard from './components/Dashboard';
import Teachers from './components/Teachers';
import Attendance from './components/Attendance';
import Admin from './components/Admin';
import './App.css';

function App() {
  const [currentPage, setCurrentPage] = useState('dashboard');

  const renderPage = () => {
    switch (currentPage) {
      case 'dashboard':
        return <Dashboard />;
      case 'teachers':
        return <Teachers />;
      case 'attendance':
        return <Attendance />;
      case 'admin':
        return <Admin />;
      default:
        return <Dashboard />;
    }
  };

  return (
    <div className="app">
      <nav className="navbar">
        <div className="navbar-brand">
          <h2>🎓 College Management System</h2>
        </div>
        <ul className="nav-menu">
          <li>
            <button
              className={`nav-link ${currentPage === 'dashboard' ? 'active' : ''}`}
              onClick={() => setCurrentPage('dashboard')}
            >
              Dashboard
            </button>
          </li>
          <li>
            <button
              className={`nav-link ${currentPage === 'teachers' ? 'active' : ''}`}
              onClick={() => setCurrentPage('teachers')}
            >
              Teachers
            </button>
          </li>
          <li>
            <button
              className={`nav-link ${currentPage === 'attendance' ? 'active' : ''}`}
              onClick={() => setCurrentPage('attendance')}
            >
              Attendance
            </button>
          </li>
          <li>
            <button
              className={`nav-link ${currentPage === 'admin' ? 'active' : ''}`}
              onClick={() => setCurrentPage('admin')}
            >
              Admin
            </button>
          </li>
        </ul>
      </nav>

      <main className="main-content">
        {renderPage()}
      </main>

      <footer className="footer">
        <p>&copy; 2026 College Management System. All rights reserved. | Built with React + Spring Boot</p>
      </footer>
    </div>
  );
}

export default App;
