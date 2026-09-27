import React, { useEffect, useState } from "react";
import { useAuth, useNavigate } from "../context/AuthContext";
import { adminService } from "../api/studentService";
import "bootstrap/dist/bootstrap.min.css";

const ManageStudents = () => {
  const { role, user, token } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    if (!token || role !== "ADMIN") {
      navigate("/login");
    }
  }, [token, role, navigate]);

  const [students, setStudents] = useState([]);
  const [search, setSearch] = useState("");

  useEffect(() => {
    const loadStudents = async () => {
      try {
        const response = await adminService.getStudents();
        setStudents(response || []);
      } catch (error) {
        console.error("Error loading students:", error);
      }
    };
    loadStudents();
  }, [token]);

  const filteredStudents = students.filter(
    (s) =>
      s.name.toLowerCase().includes(search.toLowerCase()) ||
      s.email.toLowerCase().includes(search.toLowerCase())
  );

  return (
    <div className="pt-4">
      <h2>Manage Students</h2>

      <div className="card mb-3">
        <div className="card-body">
          <div className="row g-2">
            <div className="col-md-6">
              <input
                type="text"
                className="form-control"
                placeholder="Search students..."
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
              <th>Name</th>
              <th>Email</th>
              <th>Department</th>
              <th>CGPA</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {filteredStudents.map((student) => (
              <tr key={student.id}>
                <td>{student.name}</td>
                <td>{student.email}</td>
                <td>{student.department || "N/A"}</td>
                <td>{student.cgpa || "N/A"}</td>
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

export default ManageStudents;