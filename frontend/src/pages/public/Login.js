import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { loginStudent, loginCompany, loginAdmin } from "../api/studentService";
import "bootstrap/dist/bootstrap.min.css";

const Login = () => {
  const [loginType, setLoginType] = useState("student");
  const [credentials, setCredentials] = useState({ email: "", password: "" });
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    let response;
    if (loginType === "student") {
      response = await loginStudent(credentials);
    } else if (loginType === "company") {
      response = await loginCompany(credentials);
    } else {
      response = await loginAdmin(credentials);
    }

    if (response && response.token) {
      navigate(`/${loginType === "student" ? "student" : loginType === "company" ? "company" : "admin"}/dashboard`);
    } else {
      alert("Login failed. Please check your credentials.");
    }
  };

  return (
    <div className="min-vh-100 d-flex align-items-center justify-content-center bg-light">
      <div className="col-md-5 card p-4">
        <h3 className="text-center mb-3">Login</h3>
        <div className="form-check mb-3">
          <input
            className="form-check-input"
            type="radio"
            name="loginType"
            checked={loginType === "student"}
            onChange={() => setLoginType("student")}
            id="studentLogin"
          />
          <label className="form-check-label" htmlFor="studentLogin">
            Student
          </label>
        </div>
        <div className="form-check mb-3">
          <input
            className="form-check-input"
            type="radio"
            name="loginType"
            checked={loginType === "company"}
            onChange={() => setLoginType("company")}
            id="companyLogin"
          />
          <label className="form-check-label" htmlFor="companyLogin">
            Company
          </label>
        </div>
        <div className="form-check mb-3">
          <input
            className="form-check-input"
            type="radio"
            name="loginType"
            checked={loginType === "admin"}
            onChange={() => setLoginType("admin")}
            id="adminLogin"
          />
          <label className="form-check-label" htmlFor="adminLogin">
            Admin
          </label>
        </div>
        <form onSubmit={handleLogin}>
          <div className="mb-3">
            <label htmlFor="email" className="form-label">Email</label>
            <input
              type="email"
              className="form-control"
              id="email"
              placeholder="Enter email"
              value={credentials.email}
              onChange={(e) =>
                setCredentials({ ...credentials, email: e.target.value })
              }
            />
          </div>
          <div className="mb-3">
            <label htmlFor="password" className="form-label">Password</label>
            <input
              type="password"
              className="form-control"
              id="password"
              placeholder="Enter password"
              value={credentials.password}
              onChange={(e) =>
                setCredentials({ ...credentials, password: e.target.value })
              }
            />
          </div>
          <button type="submit" className="btn btn-primary w-100">
            Login
          </button>
        </form>
        <div className="mt-3 text-center">
          <small>
            Don't have an account?
            <Link to={loginType === "student" ? "/student/register" : loginType === "company" ? "/company/register" : "/admin/register"}>
              {loginType === "student" ? "Register Student" : loginType === "company" ? "Register Company" : "Register Admin"}
            </Link>
          </small>
        </div>
      </div>
    </div>
  );
};

export default Login;