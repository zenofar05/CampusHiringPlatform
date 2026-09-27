import React from "react";
import { useLocation } from "react-router-dom";
import "bootstrap/dist/bootstrap.min.css";

const Sidebar = () => {
  const location = useLocation();
  const path = location.pathname;

  const menuItems = [
    { label: "Dashboard", path: "/dashboard", icon: "fas fa-tachometer-alt" },
    { label: "Profile", path: "/profile", icon: "fas fa-user" },
    { label: "Jobs", path: "/jobs", icon: "fas fa-briefcase" },
    { label: "Applications", path: "/applications", icon: "fas fa-list" },
    { label: "Internships", path: "/internships", icon: "fas fa-graduation-cap" },
    { label: "Certificates", path: "/certificates", icon: "fas fa-certificate" },
  ];

  return (
    <div className="col-md-2 sidebar bg-light vh-100 p-3">
      <h4>Menu</h4>
      <hr />
      {menuItems.map((item) => (
        <div
          key={item.path}
          className={`mb-2 ${
            path === item.path ? "active" : ""
          }`}
        >
          <a
            to={item.path}
            className="text-dark text-decoration-none stretched-link"
          >
            <i className={item.icon} me-2></i>{item.label}
          </a>
        </div>
      ))}
    </div>
  );
};

export default Sidebar;