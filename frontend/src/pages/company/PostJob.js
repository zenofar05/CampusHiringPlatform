import React, { useState } from "react";
import { useAuth, useNavigate } from "../context/AuthContext";
import { companyService } from "../api/studentService";
import "bootstrap/dist/bootstrap.min.css";

const PostJob = () => {
  const { role, user, token } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    if (!token || role !== "COMPANY") {
      navigate("/login");
    }
  }, [token, role, navigate]);

  const [jobData, setJobData] = useState({
    jobTitle: "",
    description: "",
    location: "",
    salary: "",
    eligibilityCgpa: "",
    deadline: "",
    packageAmount: "",
    skills: "",
  });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await companyService.postJob(jobData);
      if (response && response.jobId) {
        alert("Job posted successfully!");
        navigate("/company/dashboard");
      }
    } catch (error) {
      alert("Failed to post job. Please try again.");
    }
  };

  return (
    <div className="pt-4 max-w-md mx-auto">
      <h2>Post a New Job</h2>
      <div className="card p-4">
        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label htmlFor="jobTitle" className="form-label">
              Job Title
            </label>
            <input
              type="text"
              className="form-control"
              id="jobTitle"
              value={jobData.jobTitle}
              onChange={(e) =>
                setJobData({ ...jobData, jobTitle: e.target.value })
              }
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="description" className="form-label">
              Description
            </label>
            <textarea
              className="form-control"
              id="description"
              rows="3"
              value={jobData.description}
              onChange={(e) =>
                setJobData({ ...jobData, description: e.target.value })
              }
              required
            ></textarea>
          </div>
          <div className="mb-3">
            <label htmlFor="location" className="form-label">Location</label>
            <input
              type="text"
              className="form-control"
              id="location"
              value={jobData.location}
              onChange={(e) =>
                setJobData({ ...jobData, location: e.target.value })
              }
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="salary" className="form-label">Salary</label>
            <input
              type="text"
              className="form-control"
              id="salary"
              value={jobData.salary}
              onChange={(e) =>
                setJobData({ ...jobData, salary: e.target.value })
              }
            />
          </div>
          <div className="mb-3">
            <label htmlFor="eligibilityCgpa" className="form-label">
              Eligibility CGPA
            </label>
            <input
              type="number"
              className="form-control"
              id="eligibilityCgpa"
              value={jobData.eligibilityCgpa}
              onChange={(e) =>
                setJobData({ ...jobData, eligibilityCgpa: e.target.value })
              }
              min="0"
              max="10"
              step="0.1"
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="deadline" className="form-label">Deadline</label>
            <input
              type="date"
              className="form-control"
              id="deadline"
              value={jobData.deadline}
              onChange={(e) =>
                setJobData({ ...jobData, deadline: e.target.value })
              }
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="packageAmount" className="form-label">
              Package Amount
            </label>
            <input
              type="number"
              className="form-control"
              id="packageAmount"
              value={jobData.packageAmount}
              onChange={(e) =>
                setJobData({ ...jobData, packageAmount: e.target.value })
              }
              min="0"
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="skills" className="form-label">Skills</label>
            <textarea
              className="form-control"
              id="skills"
              rows="2"
              value={jobData.skills}
              onChange={(e) =>
                setJobData({ ...jobData, skills: e.target.value })
              }
            ></textarea>
          </div>
          <button type="submit" className="btn btn-primary w-100">
            Post Job
          </button>
        </form>
      </div>
    </div>
  );
};

export default PostJob;