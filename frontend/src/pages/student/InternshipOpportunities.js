import React, { useEffect } from "react";
import { useAuth, useNavigate } from "../context/AuthContext";
import { studentService } from "../api/studentService";
import "bootstrap/dist/bootstrap.min.css";

const InternshipOpportunities = () => {
  const { role, user, token } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    if (!token || role !== "STUDENT") {
      navigate("/login");
    }
  }, [token, role, navigate]);

  const [internships, setInternships] = useState([]);

  useEffect(() => {
    const loadInternships = async () => {
      try {
        const response = await studentService.getInternships();
        setInternships(response || []);
      } catch (error) {
        console.error("Error loading internships:", error);
      }
    };
    loadInternships();
  }, [token]);

  const applyInternship = async (internshipId) => {
    const response = await studentService.applyInternship(internshipId);
    if (response && response.internshipApplicationId) {
      alert("Internship application submitted successfully!");
      navigate("/student/internship-opportunities");
    }
  };

  return (
    <div className="pt-4">
      <h2>Internship Opportunities</h2>
      {internships.length === 0 ? (
        <div className="alert alert-info">No internships available.</div>
      ) : (
        <div className="table-responsive">
          <table className="table table-striped">
            <thead>
              <tr>
                <th>Title</th>
                <th>Company</th>
                <th>Stipend</th>
                <th>Duration</th>
                <th>Eligibility CGPA</th>
                <th>Deadline</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {internships.map((internship) => (
                <tr key={internship.internshipId}>
                  <td>{internship.title}</td>
                  <td>{internship.companyName || "Not specified"}</td>
                  <td>{internship.stipend || "Not specified"}</td>
                  <td>{internship.duration || "Not specified"}</td>
                  <td>{internship.eligibilityCgpa || "Not specified"}</td>
                  <td>{internship.deadline || "Not specified"}</td>
                  <td>
                    <button
                      className="btn btn-success btn-sm"
                      onClick={() => applyInternship(internship.internshipId)}
                    >
                      Apply
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

export default InternshipOpportunities;