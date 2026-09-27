import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { registerCompany } from "../api/studentService";
import "bootstrap/dist/bootstrap.min.css";

const CompanyRegister = () => {
  const [formData, setFormData] = useState({
    companyName: "",
    email: "",
    location: "",
    packageInfo: "",
  });
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    const response = await registerCompany(formData);
    if (response && response.token) {
      navigate("/company/dashboard");
    } else {
      alert("Registration failed: " + (response.message || ""));
    }
  };

  return (
    <div className="min-vh-100 d-flex align-items-center justify-content-center bg-light">
      <div className="col-md-7 card p-4">
        <h3 className="text-center mb-3">Company Registration</h3>
        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label htmlFor="companyName" className="form-label">
              Company Name
            </label>
            <input
              type="text"
              className="form-control"
              id="companyName"
              placeholder="Enter company name"
              value={formData.companyName}
              onChange={(e) =>
                setFormData({ ...formData, companyName: e.target.value })
              }
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="email" className="form-label">Email</label>
            <input
              type="email"
              className="form-control"
              id="email"
              placeholder="Enter email"
              value={formData.email}
              onChange={(e) =>
                setFormData({ ...formData, email: e.target.value })
              }
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="location" className="form-label">Location</label>
            <input
              type="text"
              className="form-control"
              id="location"
              placeholder="Enter location"
              value={formData.location}
              onChange={(e) =>
                setFormData({ ...formData, location: e.target.value })
              }
            />
          </div>
          <div className="mb-3">
            <label htmlFor="packageInfo" className="form-label">
              Package Info
            </label>
            <input
              type="text"
              className="form-control"
              id="packageInfo"
              placeholder="Enter package information"
              value={formData.packageInfo}
              onChange={(e) =>
                setFormData({ ...formData, packageInfo: e.target.value })
              }
            />
          </div>
          <button type="submit" className="btn btn-success w-100">
            Register
          </button>
        </form>
      </div>
    </div>
  );
};

export default CompanyRegister;