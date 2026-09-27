import React, { useEffect, useState } from "react";
import { useAuth, useNavigate, useParams } from "../context/AuthContext";
import { companyService } from "../api/studentService";
import "bootstrap/dist/bootstrap.min.css";

const ViewApplicants = () => {
  const { role, user, token } = useAuth();
  const navigate = useNavigate();
  const { jobId } = useParams();

  useEffect(() => {
    if (!token || role !== "COMPANY") {
      navigate("/login");
    }
  }, [token, role, navigate]);

  const [applicants, setApplicants] = useState([]);
  const [jobTitle, setJobTitle] = useState("");

  useEffect(() => {
    const loadApplicants = async () => {
      try {
        const response = await companyService.getApplicants(jobId);
        setApplicants(response || []);
        if (response && response.length > 0) {
          setJobTitle(response[0].jobTitle || "Unknown Job");
        }
      } catch (error) {
        console.error("Error loading applicants:", error);
      }
    };
    loadApplicants();
  }, [token, jobId]);

  return (
    <div className="pt-4">
      <h2>View Applicants</h2>

      {jobTitle && (
        <h4>Job: {jobTitle}</h4>
      )}

      {!jobTitle && jobId ? (
        <p className="text-muted">Loading applicants...</p>
      ) : null}

      {applicants.length === 0 ? (
        <div className="alert alert-info">
          No applicants yet.
        </div>
      ) : (
        <div className="table-responsive">
          <table className="table table-striped">
            <thead>
              <tr>
                <th>Student Name</th>
                <th>Email</th>
                <th>Department</th>
                <th>CGPA</th>
                <th>Cover Letter</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {applicants.map((app) => (
                <tr key={app.applicationId}>
                  <td>{app.studentName || "N/A"}</td>
                  <td>{app.studentEmail || "N/A"}</td>
                  <td>{app.studentDepartment || "N/A"}</td>
                  <td>{app.studentCgpa || "N/A"}</td>
                  <td>{app.coverLetter ? "Yes" : "No"}</td>
                  <td>
                    <button
                      className="btn btn-sm btn-outline-primary"
                      onClick={() =>
                        window.open(app.applicationDetailsUrl || "#", "_blank")
                      }
                    >
                      View Details
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
};

export default ViewApplicants;