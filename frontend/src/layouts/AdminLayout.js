import React from "react";
import { useNavigate } from "react-router-dom";
import Sidebar from "../components/Sidebar";
import Navbar from "../components/Navbar";
import { useAuth } from "../context/AuthContext";

const AdminLayout = ({ children }) => {
  const { role, token } = useAuth();
  const navigate = useNavigate();

  if (!token || role !== "ADMIN") {
    navigate("/login");
    return null;
  }

  return (
    <div className="container-fluid">
      <Navbar isAuthenticated={true} userRole="ADMIN" />
      <Sidebar />
      <div className="col-md-9 p-4">{children}</div>
    </div>
  );
};

export default AdminLayout;