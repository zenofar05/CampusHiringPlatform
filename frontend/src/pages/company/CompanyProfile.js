import React, { useEffect } from "react";
import { useAuth, useNavigate } from "../context/AuthContext";
import { companyService } from "../api/studentService";
import "bootstrap/dist/bootstrap.min.css";

const CompanyProfile = () => {
  const { role, user, token } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    if (!token || role !== "COMPANY") {
      navigate("/login");
    }
  }, [token, role, navigate]);

  const [profile, setProfile] = useState(null);

  useEffect(() => {
    const loadProfile = async () => {
      try {
        const response = await companyService.getProfile();
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
      <h2>Company Profile</h2>
      <div className="card mb-3">
        <div className="card-header">Company Information</div>
        <div className="card-body">
          <div className="row">
            <div className="col-md-6">
              <p><strong>Company Name:</strong> {profile.companyName}</p>
              <p><strong>Email:</strong> {profile.email}</p>
            </div>
            <div className="col-md-6">
              <p><strong>Location:</strong> {profile.location}</p>
              <p><strong>Website:</strong> {profile.websiteUrl || "Not provided"}</p>
            </div>
          </div>
          <p><strong>Package Info:</strong> {profile.packageInfo || "Not specified"}</p>
          <p><strong>Eligibility CGPA:</strong> {profile.eligibilityCgpa || "Not specified"}</p>
          <p><strong>Description:</strong> {profile.description || "No description"}</p>
        </div>
      </div>

      <div className="card">
        <div className="card-header">Update Profile</div>
        <div className="card-body">
          <form>
            <div className="mb-3">
              <label htmlFor="companyName" className="form-label">
                Company Name
              </label>
              <input
                type="text"
                className="form-control"
                id="companyName"
                value={profile.companyName}
                onChange={(e) =>
                  setProfile({ ...profile, companyName: e.target.value })
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
                value={profile.email}
                disabled
              />
            </div>
            <div className="mb-3">
              <label htmlFor="location" className="form-label">Location</label>
              <input
                type="text"
                className="form-control"
                id="location"
                value={profile.location || ""}
                onChange={(e) =>
                  setProfile({ ...profile, location: e.target.value })
                }
              />
            </div>
            <div className="mb-3">
              <label htmlFor="websiteUrl" className="form-label">Website URL</label>
              <input
                type="text"
                className="form-control"
                id="websiteUrl"
                value={profile.websiteUrl || ""}
                onChange={(e) =>
                  setProfile({ ...profile, websiteUrl: e.target.value })
                }
                placeholder="Enter website URL"
              />
            </div>
            <div className="mb-3">
              <label htmlFor="packageInfo" className="form-label">Package Info</label>
              <input
                type="text"
                className="form-control"
                id="packageInfo"
                value={profile.packageInfo || ""}
                onChange={(e) =>
                  setProfile({ ...profile, packageInfo: e.target.value })
                }
              />
            </div>
            <div className="mb-3">
              <label htmlFor="eligibilityCgpa" className="form-label">
                Eligibility CGPA
              </label>
              <input
                type="number"
                className="form-control"
                id="eligibilityCgpa"
                value={profile.eligibilityCgpa || ""}
                onChange={(e) =>
                  setProfile({ ...profile, eligibilityCgpa: parseFloat(e.target.value) })
                }
                min="0"
                max="10"
                step="0.1"
              />
            </div>
            <div className="mb-3">
              <label htmlFor="description" className="form-label">Description</label>
              <textarea
                className="form-control"
                id="description"
                rows="3"
                value={profile.description || ""}
                onChange={(e) =>
                  setProfile({ ...profile, description: e.target.value })
                }
                placeholder="Enter company description"
              ></textarea>
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

export default CompanyProfile;