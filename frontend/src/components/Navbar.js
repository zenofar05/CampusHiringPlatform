import React from "react";
import { Link } from "react-router-dom";
import "bootstrap/dist/bootstrap.min.css";

const Navbar = ({ isAuthenticated, userRole }) => (
  <nav className="navbar navbar-expand-lg navbar-dark bg-primary sticky-top">
    <div className="container-fluid">
      <Link to="/" className="navbar-brand">
        <span className="text-warning">Campus</span>Hiring Platform
      </Link>
      <button
        className="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarNav"
      >
        <span className="navbar-toggler-icon"></span>
      </button>
      <div className="collapse navbar-collapse" id="navbarnav">
        <ul className="navbar-nav me-auto">
          <li className="nav-item">
            <Link to="/" className="nav-link">
              <i className="fas fa-home me-2"></i>Home
            </Link>
          </li>
        </ul>

        <ul className="navbar-nav">
          {isAuthenticated ? (
            <li className="nav-item dropdown">
              <a
                className="nav-link dropdown-toggle"
                href="#"
                role="button"
                data-bs-toggle="dropdown"
              >
                {userRole}
              </a>
              <ul className="dropdown-menu dropdown-menu-end">
                <li>
                  <Link to="/student/dashboard" className="dropdown-item">
                    <i className="fas fa-user me-2"></i>My Dashboard
                  </Link>
                </li>
                <li>
                  <Link to="/student/profile" className="dropdown-item">
                    <i className="fas fa-user-tie me-2"></i>My Profile
                  </Link>
                </li>
                <li>
                  <hr className="dropdown-divider" />
                </li>
                <li>
                  <button
                    className="dropdown-item text-danger"
                    onClick={() => {
                      window.dispatchEvent(new Event("authLogout"));
                      localStorage.removeItem("token");
                      setTimeout(() => window.location.href = "/login", 100);
                    }}
                  >
                    <i className="fas fa-sign-out-alt me-2"></i>Logout
                  </button>
                </li>
              </ul>
            </li>
          ) : (
            <>
              <li className="nav-item">
                <Link to="/login" className="nav-link">
                  <i className="fas fa-sign-in-alt me-2"></i>Login
                </Link>
              </li>
              <li className="nav-item">
                <Link to="/student/register" className="nav-link">
                  <i className="fas fa-user-plus me-2"></i>Register Student
                </Link>
              </li>
              <li className="nav-item">
                <Link to="/company/register" className="nav-link">
                  <i className="fas fa-building me-2"></i>Register Company
                </Link>
              </li>
            </>
          )}
        </ul>
      </div>
    </div>
  </nav>
);

export default Navbar;