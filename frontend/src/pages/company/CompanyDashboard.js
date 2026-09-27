import React, { useEffect } from "react";
import { useAuth, useNavigate } from "../context/AuthContext";
import { companyService } from "../api/studentService";
import "bootstrap/dist/bootstrap.min.css";

const CompanyDashboard = () => {
  const { role, user, token } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    if (!token || role !== "COMPANY") {
      navigate("/login");
    }
  }, [token, role, navigate]);

  const [jobs, setJobs] = useState([]);
  const [internships, setInternships] = useState([]);

  useEffect(() => {
    const loadData = async () => {
      try {
        const [jobsRes, internshipsRes] = await Promise.all(
          companyService.getMyJobs(),
          companyService.getInternships()
        );
        setJobs(jobsRes || []);
        setInternships(internshipsRes || []);
      } catch (error) {
        console.error("Error loading company dashboard:", error);
      }
    };
    loadData();
  }, [token]);

  return (
    <div className="pt-4">
      <h2>Company Dashboard</h2>
      <div className="row mb-4">
        <div className="col-md-6">
          <div className="card text-white bg-primary mb-3">
            <div className="card-header">Jobs</div>
            <div className="card-body">
              <h5 className="card-title">{jobs.length}</h5>
              <p className="card-text">Active job postings</p>
            </div>
          </div>
        </div>
        <div className="col-md-6">
          <div className="card text-white bg-success mb-3">
            <div className="card-header">Internships</div>
            <div className="card-body">
              <h5 className="card-title">{internships.length}</h5>
              <p className="card-text">Active internship postings</p>
            </div>
          </div>
        </div>
      </div>

      <div className="row">
        <div className="col-md-6">
          <h4>My Jobs</h4>
          {jobs.length === 0 ? (
            <p>No jobs posted yet.</p>
          ) : (
            <table className="table table-striped">
              <thead>
                <tr>
                  <th>Job Title</th>
                  <th>Location</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {jobs.map((job) => (
                  <tr key={job.jobId}>
                    <td>{job.jobTitle}</td>
                    <td>{job.location}</td>
                    <td>
                      <button
                        className="btn btn-sm btn-outline-info"
                        onClick={() => navigate(`/company/manage-jobs/${job.jobId}`)}
                      >
                        Manage
                      </button>
                    </button>
                  </tr>
                ))}
              </tbody>
            </table>
          )}
        </div>

        <div className="col-md-6">
          <h4>My Internships</h4>
          {internships.length === 0 ? (
            <p>No internships posted yet.</p>
          ) : (
            <table className="table table-striped">
              <thead>
                <tr>
                  <th>Internship Title</th>
                  <th>Duration</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {internships.map((internship) => (
                  <tr key={internship.internshipId}>
                    <td>{internship.title}</td>
                    <td>{internship.duration}</td>
                    <td>
                      <button
                        className="btn btn-sm btn-outline-info"
                        onClick={() => navigate(`/company/manage-internships/${internship.internshipId}`)}
                      >
                        Manage
                      </button>
                    </button>
                  </tr>
                ))}
              </tbody>
            </table>
          )}
        </div>
      </div>
    </div>
  );
};

export default CompanyDashboard;