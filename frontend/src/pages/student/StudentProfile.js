import React, { useEffect } from "react";
import { useAuth, useNavigate } from "../context/AuthContext";
import { studentService } from "../api/studentService";
import "bootstrap/dist/bootstrap.min.css";

const StudentProfile = () => {
  const { role, user, token } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    if (!token || role !== "STUDENT") {
      navigate("/login");
    }
  }, [token, role, navigate]);

  const [profile, setProfile] = useState(null);

  useEffect(() => {
    const loadProfile = async () => {
      try {
        const response = await studentService.getProfile();
        setProfile(response);
      } catch (error) {
        console.error("Error loading profile:", error);
      }
    };
    loadProfile();
  }, [token]);

  if (!profile) {
    return <div>Loading profile...</div>;
  }

  return (
    <div className="pt-4">
      <h2>Student Profile</h2>
      <div className="card mb-3">
        <div className="card-header">Profile Information</div>
        <div className="card-body">
          <div className="row">
            <div className="col-md-6">
              <p><strong>Name:</strong> {profile.name}</p>
              <p><strong>Email:</strong> {profile.email}</p>
              <p><strong>Department:</strong> {profile.department || "Not specified"}</p>
            </div>
            <div className="col-md-6">
              <p><strong>CGPA:</strong> {profile.cgpa || "Not specified"}</p>
              <p><strong>Phone:</strong> {profile.phone || "Not specified"}</p>
              <p><strong>Resume:</strong> {profile.resumeUrl || "Not uploaded"}</p>
            </div>
          </div>
        </div>
      </div>

      <div className="card">
        <div className="card-header">Update Profile</div>
        <div className="card-body">
          <form>
            <div className="mb-3">
              <label htmlFor="name" className="form-label">Full Name</label>
              <input
                type="text"
                className="form-control"
                id="name"
                value={profile.name}
                onChange={(e) =>
                  setProfile({ ...profile, name: e.target.value })
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
                value={profile.department || ""}
                onChange={(e) =>
                  setProfile({ ...profile, department: e.target.value })
                }
              />
            </div>
            <div className="mb-3">
              <label htmlFor="cgpa" className="form-label">CGPA</label>
              <input
                type="number"
                className="form-control"
                id="cgpa"
                value={profile.cgpa || ""}
                onChange={(e) =>
                  setProfile({ ...profile, cgpa: parseFloat(e.target.value) })
                }
                min="0"
                max="10"
                step="0.1"
              />
            </div>
            <div className="mb-3">
              <label htmlFor="phone" className="form-label">Phone</label>
              <input
                type="text"
                className="form-control"
                id="phone"
                value={profile.phone || ""}
                onChange={(e) =>
                  setProfile({ ...profile, phone: e.target.value })
                }
              />
            </div>
            <div className="mb-3">
              <label htmlFor="resumeUrl" className="form-label">Resume URL</label>
              <input
                type="text"
                className="form-control"
                id="resumeUrl"
                value={profile.resumeUrl || ""}
                onChange={(e) =>
                  setProfile({ ...profile, resumeUrl: e.target.value })
                }
                placeholder="Enter resume URL"
              />
            </div>
            <button type="submit" className="btn btn-primary">
              Update Profile
            </button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default StudentProfile;