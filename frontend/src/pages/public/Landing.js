import React from "react";
import { Link } from "react-router-dom";
import "bootstrap/dist/bootstrap.min.css";

const Landing = () => (
  <div className="jumbotron text-center py-5">
    <h1 className="display-4">
      <span className="text-warning">Campus</span>Hiring Platform
    </h1>
    <p className="lead">Connecting students with companies and internship opportunities</p>
    <hr className="my-4" />
    <div className="row g-2">
      <div className="col-12 col-md-4 mx-auto">
        <Link to="/student/register" className="btn btn-primary btn-lg w-100">
          <i className="fas fa-user-graduate me-2"></i>Student Signup
        </Link>
      </div>
      <div className="col-12 col-md-4 mx-auto">
        <Link to="/company/register" className="btn btn-success btn-lg w-100">
          <i className="fas fa-building me-2"></i>Company Signup
        </Link>
      </div>
    </div>
  </div>
);

export default Landing;