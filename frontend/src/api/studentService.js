import api from "./apiConfig";

export const authService = {
  loginStudent: (data) => api.post("/api/auth/student/login", data),
  loginCompany: (data) => api.post("/api/auth/company/login", data),
  loginAdmin: (data) => api.post("/api/auth/admin/login", data),
  registerStudent: (data) => api.post("/api/auth/student/register", data),
  registerCompany: (data) => api.post("/api/auth/company/register", data),
  registerAdmin: (data) => api.post("/api/auth/admin/register", data),
  logout: () => api.post("/api/auth/logout"),
};

export const studentService = {
  getProfile: () => api.get("/api/students/me"),
  updateProfile: (data) => api.put("/api/students/me", data),
  browseJobs: () => api.get("/api/jobs"),
  getJobDetails: (id) => api.get(`/api/jobs/${id}`),
  applyJob: (data) => api.post("/api/applications", data),
  getMyApplications: () => api.get("/api/applications"),
  getInternships: () => api.get("/api/internships"),
  getInternshipDetails: (id) => api.get(`/api/internships/${id}`),
  applyInternship: (id) => api.post(`/api/internship-applications`, { internshipId: id }),
  getMyInternshipApplications: () => api.get("/api/internship-applications"),
  createCertificate: (data) => api.post("/api/certificates", data),
  getMyCertificates: () => api.get("/api/certificates"),
  getPlacements: () => api.get("/api/placements/me"),
  markNotificationRead: (id) => api.put(`/api/notifications/${id}/read`),
};

export const companyService = {
  getProfile: () => api.get("/api/companies/me"),
  updateProfile: (data) => api.put("/api/companies/me", data),
  postJob: (data) => api.post("/api/jobs", data),
  getMyJobs: () => api.get("/api/jobs/my"),
  updateJob: (id, data) => api.put(`/api/jobs/${id}`, data),
  deleteJob: (id) => api.delete(`/api/jobs/${id}`),
  getApplicants: (jobId) => api.get(`/api/applications/company?jobId=${jobId}`),
  getInternships: () => api.get("/api/internships/my"),
  postInternship: (data) => api.post("/api/internships", data),
  updateInternship: (id, data) => api.put(`/api/internships/${id}`, data),
  deleteInternship: (id) => api.delete(`/api/internships/${id}`),
  getInternshipApplicants: (internshipId) =>
    api.get(`/api/internship-applications/company?internshipId=${internshipId}`),
};

export const adminService = {
  getStudents: () => api.get("/api/users/students"),
  getCompanies: () => api.get("/api/users/companies"),
  getJobs: () => api.get("/api/jobs/admin"),
  getApplications: () => api.get("/api/applications/admin"),
  getStudentsDetail: (id) => api.get(`/api/users/students/${id}`),
  getCompaniesDetail: (id) => api.get(`/api/users/companies/${id}`),
};