import React, { useEffect, useState } from "react";
import { useAuth, useNavigate } from "../context/AuthContext";
import { adminService } from "../api/studentService";
import "bootstrap/dist/bootstrap.min.css";

const ManageCompanies = () => {
  const { role, user, token } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    if (!token || role !== "ADMIN") {
      navigate("/login");
    }
  }, [token, role, navigate]);

  const [companies, setCompanies] = useState([]);
  const [search, setSearch] = useState("");

  useEffect(() => {
    const loadCompanies = async () => {
      try {
        const response = await adminService.getCompanies();
        setCompanies(response || []);
      } catch (error) {
        console.error("Error loading companies:", error);
      }
    };
    loadCompanies();
  }, [token]);

  const filteredCompanies = companies.filter(
    (c) =>
      c.companyName.toLowerCase().includes(search.toLowerCase()) ||
      c.email.toLowerCase().includes(search.toLowerCase())
  );

  return (
    <div className="pt-4">
      <h2>Manage Companies</h2>

      <div className="card mb-3">
        <div className="card-body">
          <div className="row g-2">
            <div className="col-md-6">
              <input
                type="text"
                className="form-control"
                placeholder="Search companies..."
                value={search}
                onChange={(e) => setSearch(e.target.value)}
              />
            </div>
          </div>
        </div>
      </div>

      <div className="table-responsive">
        <table className="table table-striped">
          <thead>
            <tr>
              <th>Company Name</th>
              <th>Email</th>
              <th>Location</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {filteredCompanies.map((company) => (
              <tr key={company.id}>
                <td>{company.companyName}</td>
                <td>{company.email}</td>
                <td>{company.location || "N/A"}</td>
                <td>
                  <button className="btn btn-sm btn-outline-info">
                    View Details
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default ManageCompanies;