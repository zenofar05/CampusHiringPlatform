import React from "react";
import Navbar from "../components/Navbar";

const PublicLayout = ({ children }) => {
  return <Navbar isAuthenticated={false} userRole={null} />;
};

export default PublicLayout;