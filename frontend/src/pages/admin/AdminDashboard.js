import React, { useEffect, useState } from "react";
import { useAuth, useNavigate } from "../context/AuthContext";
import { adminService } from "../api/studentService";
import "bootstrap/dist/bootstrap.min.css";

const AdminDashboard = () => {
  const { role, user, token } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    if (!token || role !== "ADMIN") {
      navigate("/login");
    }
  }, [token, role, navigate]);

  const [stats, setStats] = useState({
    totalStudents: 0,
    totalCompanies: 0,
    totalJobs: 0,
    totalApplications: 0,
  });

  useEffect(() => {
    const loadStats = async () => {
      try {
        const [
          studentsRes,
          companiesRes,
          jobsRes,
          appsRes,
        ] = await Promise.all(
          adminService.getStudents(),
          adminService.getCompanies(),
          adminService.getJobs(),
          adminService.getApplications()
        );
        setStats({
          totalStudents: studentsRes?.length || 0,
          totalCompanies: companiesRes?.length || 0,
          totalJobs: jobsRes?.length || 0,
          totalApplications: appsRes?.length || 0,
        });
      } catch (error) {
        console.error("Error loading admin stats:", error);
      }
    };
    loadStats();
  }, [token]);

  return (
    <div className="pt-4">
      <h2>Admin Dashboard</h2>

      <div className="row mb-4">
        <div className="col-md-3">
          <div className="card text-white bg-primary mb-3">
            <div className="card-header">Total Students</div>
            <div className="card-body">
              <h5 className="card-title">{stats.totalStudents}</h5>
            </div>
          </div>
        </div>
        <div className="col-md-3">
          <div className="card text-white bg-success mb-3">
            <div className="card-header">Total Companies</div>
            <div className="card-body">
              <h5 className="card-title">{stats.totalCompanies}</h5>
            </div>
          </div>
        </div>
        <div className="col-md-3">
          <div className="card text-white bg-info mb-3">
            <div className="card-header">Total Jobs</div>
            <div className="card-body">
              <h5 className="card-title">{stats.totalJobs}</h5>
            </div>
          </div>
        </div>
        <div className="col-md-3">
          <div className="card text-white bg-warning text-dark mb-3">
            <div className="card-header">Total Applications</div>
            <div className="card-body">
              <h5 className="card-title">{stats.totalApplications}</h5>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AdminDashboard;