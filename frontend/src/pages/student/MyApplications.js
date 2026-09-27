import React, { useEffect } from "react";
import { useAuth, useNavigate } from "../context/AuthContext";
import { studentService } from "../api/studentService";
import "bootstrap/dist/bootstrap.min.css";

const MyApplications = () => {
  const { role, user, token } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    if (!token || role !== "STUDENT") {
      navigate("/login");
    }
  }, [token, role, navigate]);

  const [applications, setApplications] = useState([]);

  useEffect(() => {
    const loadApplications = async () => {
      try {
        const response = await studentService.getMyApplications();
        setApplications(response || []);
      } catch (error) {
        console.error("Error loading applications:", error);
      }
    };
    loadApplications();
  }, [token]);

  return (
    <div className="pt-4">
      <h2>My Applications</h2>
      {applications.length === 0 ? (
        <div className="alert alert-info">No applications yet.</div>
      ) : (
        <div className="table-responsive">
          <table className="table table-striped">
            <thead>
              <tr>
                <th>Job/Internship</th>
                <th>Type</th>
                <th>Status</th>
                <th>Applied On</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {applications.map((app) => (
                <tr key={app.applicationId}>
                  <td>
                    {app.jobTitle || app.internshipTitle || "Unknown"}
                  </td>
                  <td>
                    {app.internshipTitle ? "Internship" : "Job"}
                  </td>
                  <td>
                    <span className="badge bg-info">
                      {app.status || "APPLIED"}
                    </span>
                  </td>
                  <td>{app.applicationDate || "N/A"}</td>
                  <td>
                    <button
                      className="btn btn-sm btn-outline-primary"
                      onClick={() =>
                        studentService.applyJob({ jobId: app.jobId })
                          .then(() => alert("Re-applied!"))
                      }
                    >
                      Re-apply
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

export default MyApplications;