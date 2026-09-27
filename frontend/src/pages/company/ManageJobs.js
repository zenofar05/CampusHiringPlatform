import React, { useEffect, useState } from "react";
import { useAuth, useNavigate, useParams } from "../context/AuthContext";
import { companyService } from "../api/studentService";
import "bootstrap/dist/bootstrap.min.css";

const ManageJobs = () => {
  const { role, user, token } = useAuth();
  const navigate = useNavigate();
  const { jobId } = useParams();

  useEffect(() => {
    if (!token || role !== "COMPANY") {
      navigate("/login");
    }
  }, [token, role, navigate]);

  const [jobs, setJobs] = useState([]);
  const [job, setJob] = useState(null);

  useEffect(() => {
    const loadJobs = async () => {
      try {
        const response = await companyService.getMyJobs();
        setJobs(response || []);
        if (jobId && parseInt(jobId) > 0) {
          const selectedJob = jobs.find((j) => j.jobId === parseInt(jobId));
          setJob(selectedJob);
        }
      } catch (error) {
        console.error("Error loading jobs:", error);
      }
    };
    loadJobs();
  }, [token, jobId]);

  if (!jobs && !job) {
    return <div>Loading jobs...</div>;
  }

  return (
    <div className="pt-4">
      <h2>Manage Jobs</h2>

      <div className="card mb-3">
        <div className="card-header">
          <h5>Post New Job</h5>
        </div>
        <div className="card-body">
          <PostJob />
        </div>
      </div>

      {jobId && parseInt(jobId) > 0 && job ? (
        <div className="card mb-3">
          <div className="card-header">
            <h5>{job.jobTitle}</h5>
          </div>
          <div className="card-body">
            <p><strong>Description:</strong> {job.description}</p>
            <p><strong>Location:</strong> {job.location}</p>
            <p><strong>Eligibility CGPA:</strong> {job.eligibilityCgpa}</p>
            <p><strong>Package:</strong> {job.packageAmount}</p>
            <p><strong>Deadline:</strong> {job.deadline}</p>
            <p><strong>Skills:</strong> {job.skills || "Not specified"}</p>
          </div>
        </div>
      ) : (
        <div className="alert alert-info">
          No jobs found.
        </div>
      )}

      <div className="mt-4">
        <h4>Your Posted Jobs</h4>
        {jobs.length === 0 ? (
          <p>No jobs posted yet.</p>
        ) : (
          <table className="table table-striped">
            <thead>
              <tr>
                <th>Job Title</th>
                <th>Location</th>
                <th>Eligibility CGPA</th>
                <th>Deadline</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {jobs.map((j) => (
                <tr key={j.jobId}>
                  <td>{j.jobTitle}</td>
                  <td>{j.location}</td>
                  <td>{j.eligibilityCgpa}</td>
                  <td>{j.deadline}</td>
                  <td>
                    <button
                      className="btn btn-sm btn-outline-info"
                      onClick={() => navigate(`/company/manage-jobs/${j.jobId}`)}
                    >
                      Manage
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </div>
  );
};

export default ManageJobs;