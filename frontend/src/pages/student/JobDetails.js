import React, { useEffect } from "react";
import { useAuth, useNavigate, useParams } from "../context/AuthContext";
import { studentService } from "../api/studentService";
import "bootstrap/dist/bootstrap.min.css";

const JobDetails = () => {
  const { role, user, token } = useAuth();
  const navigate = useNavigate();
  const { id } = useParams();

  useEffect(() => {
    if (!token || role !== "STUDENT") {
      navigate("/login");
    }
  }, [token, role, navigate]);

  const [job, setJob] = useState(null);

  useEffect(() => {
    const loadJob = async () => {
      try {
        const response = await studentService.getJobDetails(parseInt(id));
        setJob(response);
      } catch (error) {
        console.error("Error loading job details:", error);
        navigate("/student/jobs");
      }
    };
    loadJob();
  }, [token, id]);

  if (!job) {
    return <div>Loading job details...</div>;
  }

  return (
    <div className="pt-4">
      <h2>Job Details</h2>
      <div className="card mb-3">
        <div className="card-header">
          <h5>{job.jobTitle}</h5>
        </div>
        <div className="card-body">
          <p className="card-text"><strong>Description:</strong> {job.description}</p>
          <p className="card-text"><strong>Location:</strong> {job.location}</p>
          <p className="card-text"><strong>Salary:</strong> {job.salary || "Not specified"}</p>
          <p className="card-text"><strong>CGPA Eligibility:</strong> {job.eligibilityCgpa}</p>
          <p className="card-text"><strong>Package:</strong> {job.packageAmount || "Not specified"}</p>
          <p className="card-text"><strong>Deadline:</strong> {job.deadline}</p>
          <p className="card-text"><strong>Skills:</strong> {job.skills || "Not specified"}</p>
          <div className="mt-3">
            <button
              className="btn btn-primary"
              onClick={() =>
                studentService.applyJob({ jobId: job.jobId }).then(
                  (res) => {
                    if (res && res.applicationId) {
                      alert("Application submitted successfully!");
                      navigate("/student/applications");
                    }
                  }
                )
              }
            >
              Apply for this job
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default JobDetails;