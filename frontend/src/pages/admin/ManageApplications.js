import React, { useEffect, useState } from "react";
import { useAuth, useNavigate } from "../context/AuthContext";
import { adminService } from "../api/studentService";
import "bootstrap/dist/bootstrap.min.css";

const ManageApplications = () => {
  const { role, user, token } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    if (!token || role !== "ADMIN") {
      navigate("/login");
    }
  }, [token, role, navigate]);

  const [applications, setApplications] = useState([]);
  const [search, setSearch] = useState("");

  useEffect(() => {
    const loadApplications = async () => {
      try {
        const response = await adminService.getApplications();
        setApplications(response || []);
      } catch (error) {
        console.error("Error loading applications:", error);
      }
    };
    loadApplications();
  }, [token]);

  const filteredApplications = applications.filter(
    (a) =>
      (a.studentName || "").toLowerCase().includes(search.toLowerCase()) ||
      (a.jobTitle || "").toLowerCase().includes(search.toLowerCase()) ||
      (a.companyName || "").toLowerCase().includes(search.toLowerCase())
  );

  return (
    <div className="pt-4">
      <h2>Manage Applications</h2>

      <div className="card mb-3">
        <div className="card-body">
          <div className="row g-2">
            <div className="col-md-6">
              <input
                type="text"
                className="form-control"
                placeholder="Search applications..."
                value={search}
                onChange={(e) => setSearch(e.target.value)}
              />
            </div>
          </div>
        </div>
      </div>

      <div className="table-responsive">
        <table className="table table-striped">
          <thead>
            <tr>
              <th>Student</th>
              <th>Job/Internship</th>
              <th>Type</th>
              <th>Status</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {filteredApplications.map((app) => (
              <tr key={app.applicationId}>
                <td>{app.studentName || "N/A"}</td>
                <td>{app.jobTitle || app.internshipTitle || "N/A"}</td>
                <td>
                  {app.internshipTitle ? "Internship" : "Job"}
                </td>
                <td>
                  <span className="badge bg-info">
                    {app.status || "PENDING"}
                  </span>
                </td>
                <td>
                  <button className="btn btn-sm btn-outline-primary">
                    View Details
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default ManageApplications;