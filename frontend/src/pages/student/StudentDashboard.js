import React, { useEffect } from "react";
import { useAuth, useNavigate } from "../context/AuthContext";
import { studentService } from "../api/studentService";
import { companyService } from "../api/studentService";
import "bootstrap/dist/bootstrap.min.css";

const StudentDashboard = () => {
  const { role, user, token } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    if (!token || role !== "STUDENT") {
      navigate("/login");
    }
  }, [token, role, navigate]);

  const [jobs, setJobs] = useState([]);
  const [internships, setInternships] = useState([]);
  const [applications, setApplications] = useState([]);
  const [certificates, setCertificates] = useState([]);

  useEffect(() => {
    const loadData = async () => {
      try {
        const [jobsRes, internshipsRes, appsRes, certsRes] = await Promise.all(
          studentService.browseJobs(),
          studentService.getInternships(),
          studentService.getMyApplications(),
          studentService.getMyCertificates()
        );
        setJobs(jobsRes || []);
        setInternships(internshipsRes || []);
        setApplications(appsRes || []);
        setCertificates(certsRes || []);
      } catch (error) {
        console.error("Error loading dashboard data:", error);
      }
    };
    loadData();
  }, [token]);

  const handleApplyJob = async (jobId) => {
    const response = await studentService.applyJob({ jobId });
    if (response && response.applicationId) {
      alert("Application submitted successfully!");
      loadData();
    }
  };

  const handleApplyInternship = async (internshipId) => {
    const response = await studentService.applyInternship(internshipId);
    if (response && response.internshipApplicationId) {
      alert("Internship application submitted successfully!");
      loadData();
    }
  };

  return (
    <div className="pt-4">
      <h2>Student Dashboard</h2>
      <div className="row mb-4">
        <div className="col-md-4">
          <div className="card text-white bg-primary mb-3">
            <div className="card-header">Jobs</div>
            <div className="card-body">
              <h5 className="card-title">{jobs.length}</h5>
              <p className="card-text">Available opportunities</p>
            </div>
          </div>
        </div>
        <div className="col-md-4">
          <div className="card text-white bg-success mb-3">
            <div className="card-header">Internships</div>
            <div className="card-body">
              <h5 className="card-title">{internships.length}</h5>
              <p className="card-text">Opportunities available</p>
            </div>
          </div>
        </div>
        <div className="col-md-4">
          <div className="card text-white bg-info mb-3">
            <div className="card-header">Applications</div>
            <div className="card-body">
              <h5 className="card-title">{applications.length}</h5>
              <p className="card-text">Your applications</p>
            </div>
          </div>
        </div>
      </div>

      <div className="row">
        <div className="col-md-6">
          <h4>Available Jobs</h4>
          {jobs.map((job) => (
            <div key={job.jobId} className="card mb-3">
              <div className="card-body">
                <h5 className="card-title">{job.jobTitle}</h5>
                <p className="card-text">{job.description.substring(0, 100)}...</p>
                <p className="card-text"><small className="text-muted">
                  Location: {job.location} | CGPA: {job.eligibilityCgpa}
                </small></p>
                <button
                  className="btn btn-sm btn-primary w-100"
                  onClick={() => handleApplyJob(job.jobId)}
                >
                  Apply
                </button>
              </div>
            </div>
          ))}
        </div>

        <div className="col-md-6">
          <h4>Available Internships</h4>
          {internships.map((internship) => (
            <div key={internship.internshipId} className="card mb-3">
              <div className="card-body">
                <h5 className="card-title">{internship.title}</h5>
                <p className="card-text">{internship.description.substring(0, 100)}...</p>
                <p className="card-text"><small className="text-muted">
                  Stipend: {internship.stipend} | Duration: {internship.duration}
                </small></p>
                <button
                  className="btn btn-sm btn-success w-100"
                  onClick={() => handleApplyInternship(internship.internshipId)}
                >
                  Apply
                </button>
              </div>
            </div>
          ))}
        </div>
      </div>

      <div className="mt-4">
        <h4>My Applications</h4>
        {applications.length > 0 ? (
          applications.map((app) => (
            <div key={app.applicationId} className="card mb-2">
              <div className="card-body">
                <h5 className="card-title">
                  {app.jobTitle || app.internshipTitle || "Application"}
                </h5>
                <p className="card-text">
                  Status: <span className="badge bg-info">{app.status || "APPLIED"}</span>
                </p>
              </div>
            </div>
          ))
        ) : (
          <p>No applications yet.</p>
        )}
      </div>

      <div className="mt-4">
        <h4>My Certificates</h4>
        {certificates.length > 0 ? (
          certificates.map((cert) => (
            <div key={cert.certificateId} className="card mb-2">
              <div className="card-body">
                <h5 className="card-title">{cert.certificateName}</h5>
                <p className="card-text">
                  Company: {cert.companyName}
                </p>
              </div>
            </div>
          ))
        ) : (
          <p>No certificates yet.</p>
        )}
      </div>
    </div>
  );
};

export default StudentDashboard;