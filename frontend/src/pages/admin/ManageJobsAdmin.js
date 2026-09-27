import React, { useEffect, useState } from "react";
import { useAuth, useNavigate } from "../context/AuthContext";
import { adminService } from "../api/studentService";
import "bootstrap/dist/bootstrap.min.css";

const ManageJobsAdmin = () => {
  const { role, user, token } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    if (!token || role !== "ADMIN") {
      navigate("/login");
    }
  }, [token, role, navigate]);

  const [jobs, setJobs] = useState([]);
  const [search, setSearch] = useState("");

  useEffect(() => {
    const loadJobs = async () => {
      try {
        const response = await adminService.getJobs();
        setJobs(response || []);
      } catch (error) {
        console.error("Error loading jobs:", error);
      }
    };
    loadJobs();
  }, [token]);

  const filteredJobs = jobs.filter(
    (j) =>
      j.jobTitle.toLowerCase().includes(search.toLowerCase()) ||
      j.location.toLowerCase().includes(search.toLowerCase())
  );

  return (
    <div className="pt-4">
      <h2>Manage Jobs</h2>

      <div className="card mb-3">
        <div className="card-body">
          <div className="row g-2">
            <div className="col-md-6">
              <input
                type="text"
                className="form-control"
                placeholder="Search jobs..."
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
              <th>Job Title</th>
              <th>Company</th>
              <th>Eligibility CGPA</th>
              <th>Deadline</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {filteredJobs.map((job) => (
              <tr key={job.jobId}>
                <td>{job.jobTitle}</td>
                <td>{job.companyName || "N/A"}</td>
                <td>{job.eligibilityCgpa}</td>
                <td>{job.deadline}</td>
                <td>
                  <button className="btn btn-sm btn-outline-info">
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

export default ManageJobsAdmin;