import React, { useEffect } from "react";
import { useAuth, useNavigate } from "../context/AuthContext";
import { studentService } from "../api/studentService";
import "bootstrap/dist/bootstrap.min.css";

const Certificates = () => {
  const { role, user, token } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    if (!token || role !== "STUDENT") {
      navigate("/login");
    }
  }, [token, role, navigate]);

  const [certificates, setCertificates] = useState([]);

  useEffect(() => {
    const loadCertificates = async () => {
      try {
        const response = await studentService.getMyCertificates();
        setCertificates(response || []);
      } catch (error) {
        console.error("Error loading certificates:", error);
      }
    };
    loadCertificates();
  }, [token]);

  const createCertificate = async (certData) => {
    const response = await studentService.createCertificate(certData);
    if (response && response.certificateId) {
      alert("Certificate added successfully!");
      navigate("/student/certificates");
      loadCertificates();
    }
  };

  return (
    <div className="pt-4">
      <h2>Certificates</h2>

      <div className="card mb-3">
        <div className="card-header">Add Certificate</div>
        <div className="card-body">
          <form
            onSubmit={(e) => {
              e.preventDefault();
              const certData = {
                certificateName: e.target.certificateName.value,
                companyName: e.target.companyName.value,
                issueDate: e.target.issueDate.value,
                certificateUrl: e.target.certificateUrl.value,
                description: e.target.description.value,
                expiryDate: e.target.expiryDate.value,
              };
              createCertificate(certData);
            }}
          >
            <div className="mb-2">
              <label className="form-label">Certificate Name</label>
              <input type="text" className="form-control" name="certificateName" required />
            </div>
            <div className="mb-2">
              <label className="form-label">Company Name</label>
              <input type="text" className="form-control" name="companyName" required />
            </div>
            <div className="mb-2">
              <label className="form-label">Issue Date</label>
              <input type="date" className="form-control" name="issueDate" required />
            </div>
            <div className="mb-2">
              <label className="form-label">Certificate URL</label>
              <input type="text" className="form-control" name="certificateUrl" required />
            </div>
            <div className="mb-2">
              <label className="form-label">Description</label>
              <textarea className="form-control" name="description" rows="3"></textarea>
            </div>
            <div className="mb-2">
              <label className="form-label">Expiry Date</label>
              <input type="date" className="form-control" name="expiryDate" required />
            </div>
            <button type="submit" className="btn btn-primary">
              Add Certificate
            </button>
          </form>
        </div>
      </div>

      {certificates.length === 0 ? (
        <div className="alert alert-info">No certificates yet.</div>
      ) : (
        <div className="table-responsive">
          <table className="table table-striped">
            <thead>
              <tr>
                <th>Certificate Name</th>
                <th>Company</th>
                <th>Issue Date</th>
                <th>Expiry Date</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {certificates.map((cert) => (
                <tr key={cert.certificateId}>
                  <td>{cert.certificateName}</td>
                  <td>{cert.companyName || "N/A"}</td>
                  <td>{cert.issueDate}</td>
                  <td>{cert.expiryDate || "N/A"}</td>
                  <td>
                    <button
                      className="btn btn-sm btn-outline-danger"
                      onClick={() =>
                        studentService.createCertificate({
                          certificateName: cert.certificateName,
                          companyName: cert.companyName,
                          issueDate: new Date().toISOString().split("T")[0],
                          certificateUrl: cert.certificateUrl,
                          description: "Updated certificate",
                          expiryDate: new Date().toISOString().split("T")[0],
                        }).then(() => alert("Certificate updated!"))
                      }
                    >
                      Update
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

export default Certificates;