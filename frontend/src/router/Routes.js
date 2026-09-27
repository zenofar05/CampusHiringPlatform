import React, { useEffect } from "react";
import { BrowserRouter as Router, Routes, Route, useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import Navbar from "../components/Navbar";
import PublicLayout from "../layouts/PublicLayout";
import StudentLayout from "../layouts/StudentLayout";
import CompanyLayout from "../layouts/CompanyLayout";
import AdminLayout from "../layouts/AdminLayout";
import Landing from "../pages/public/Landing";
import Login from "../pages/public/Login";
import StudentRegister from "../pages/public/StudentRegister";
import CompanyRegister from "../pages/public/CompanyRegister";
import StudentDashboard from "../pages/student/StudentDashboard";
import StudentProfile from "../pages/student/StudentProfile";
import BrowseJobs from "../pages/student/BrowseJobs";
import JobDetails from "../pages/student/JobDetails";
import MyApplications from "../pages/student/MyApplications";
import InternshipOpportunities from "../pages/student/InternshipOpportunities";
import Certificates from "../pages/student/Certificates";
import CompanyDashboard from "../pages/company/CompanyDashboard";
import CompanyProfile from "../pages/company/CompanyProfile";
import PostJob from "../pages/company/PostJob";
import ManageJobs from "../pages/company/ManageJobs";
import ViewApplicants from "../pages/company/ViewApplicants";
import ApplicationDetails from "../pages/company/ApplicationDetails";
import AdminDashboard from "../pages/admin/AdminDashboard";
import ManageStudents from "../pages/admin/ManageStudents";
import ManageCompanies from "../pages/admin/ManageCompanies";
import ManageJobsAdmin from "../pages/admin/ManageJobs";
import ManageApplications from "../pages/admin/ManageApplications";

const ProtectedRoute = ({ children, allowedRoles }) => {
  const { role, token } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    if (!token) {
      navigate("/login");
    }
  }, [token, navigate]);

  if (!allowedRoles || allowedRoles.includes(role)) {
    return children;
  }

  navigate("/login");
  return null;
};

const RoleGuard = ({ allowedRoles, children }) => {
  const { role } = useAuth();

  if (!role) {
    return null;
  }

  if (allowedRoles && !allowedRoles.includes(role)) {
    return null;
  }

  return children;
};

export default function RoutesApp() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={
          <PublicLayout>
            <Route index element={<Landing />} />
            <Route path="login" element={<Login />} />
            <Route path="student/register" element={<StudentRegister />} />
            <Route path="company/register" element={<CompanyRegister />} />
          </PublicLayout>
        } />

        <Route path="/student" element={
          <StudentLayout>
            <Route path="dashboard" element={<StudentDashboard />} />
            <Route path="profile" element={<StudentProfile />} />
            <Route path="jobs" element={<BrowseJobs />} />
            <Route path="jobs/:id" element={<JobDetails />} />
            <Route path="applications" element={<MyApplications />} />
            <Route path="internships" element={<InternshipOpportunities />} />
            <Route path="certificates" element={<Certificates />} />
          </StudentLayout>
        } />

        <Route path="/company" element={
          <CompanyLayout>
            <Route path="dashboard" element={<CompanyDashboard />} />
            <Route path="profile" element={<CompanyProfile />} />
            <Route path="post-job" element={<PostJob />} />
            <Route path="manage-jobs" element={<ManageJobs />} />
            <Route path="applicants/:jobId" element={<ViewApplicants />} />
          </CompanyLayout>
        } />

        <Route path="/admin" element={
          <AdminLayout>
            <Route path="dashboard" element={<AdminDashboard />} />
            <Route path="students" element={<ManageStudents />} />
            <Route path="companies" element={<ManageCompanies />} />
            <Route path="jobs" element={<ManageJobsAdmin />} />
            <Route path="applications" element={<ManageApplications />} />
          </AdminLayout>
        } />
      </Routes>
    </Router>
  );
}