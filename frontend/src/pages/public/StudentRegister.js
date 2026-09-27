import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { registerStudent } from "../api/studentService";
import "bootstrap/dist/bootstrap.min.css";

const StudentRegister = () => {
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    department: "",
    cgpa: "",
    phone: "",
  });
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    const response = await registerStudent(formData);
    if (response && response.token) {
      navigate("/student/dashboard");
    } else {
      alert("Registration failed: " + (response.message || ""));
    }
  };

  return (
    <div className="min-vh-100 d-flex align-items-center justify-content-center bg-light">
      <div className="col-md-7 card p-4">
        <h3 className="text-center mb-3">Student Registration</h3>
        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label htmlFor="name" className="form-label">
              Full Name
            </label>
            <input
              type="text"
              className="form-control"
              id="name"
              placeholder="Enter your full name"
              value={formData.name}
              onChange={(e) =>
                setFormData({ ...formData, name: e.target.value })
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
            <label htmlFor="department" className="form-label">
              Department
            </label>
            <input
              type="text"
              className="form-control"
              id="department"
              placeholder="Enter department"
              value={formData.department}
              onChange={(e) =>
                setFormData({ ...formData, department: e.target.value })
              }
            />
          </div>
          <div className="mb-3">
            <label htmlFor="cgpa" className="form-label">CGPA</label>
            <input
              type="number"
              className="form-control"
              id="cgpa"
              placeholder="Enter CGPA (0-10)"
              value={formData.cgpa}
              onChange={(e) =>
                setFormData({ ...formData, cgpa: e.target.value })
              }
              min="0"
              max="10"
              step="0.1"
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="phone" className="form-label">Phone</label>
            <input
              type="text"
              className="form-control"
              id="phone"
              placeholder="Enter phone number"
              value={formData.phone}
              onChange={(e) =>
                setFormData({ ...formData, phone: e.target.value })
              }
            />
          </div>
          <button type="submit" className="btn btn-primary w-100">
            Register
          </button>
        </form>
      </div>
    </div>
  );
};

export default StudentRegister;