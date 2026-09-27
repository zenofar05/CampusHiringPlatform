import React from "react";
import { useNavigate } from "react-router-dom";
import Sidebar from "../components/Sidebar";
import Navbar from "../components/Navbar";
import { useAuth } from "../context/AuthContext";

const CompanyLayout = ({ children }) => {
  const { role, token } = useAuth();
  const navigate = useNavigate();

  if (!token || role !== "COMPANY") {
    navigate("/login");
    return null;
  }

  return (
    <div className="container-fluid">
      <Navbar isAuthenticated={true} userRole="COMPANY" />
      <Sidebar />
      <div className="col-md-9 p-4">{children}</div>
    </div>
  );
};

export default CompanyLayout;