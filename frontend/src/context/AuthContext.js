import React, { createContext, useContext, useState, useEffect } from "react";

const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

export const AuthProvider = ({ children }) => {
  const [token, setToken] = useState(() => localStorage.getItem("token"));
  const [user, setUser] = useState(null);
  const [role, setRole] = useState(null);

  useEffect(() => {
    if (token) {
      try {
        const payload = JSON.parse(atob(token.split(".")[1]));
        setRole(payload.role);
        setUser({ id: payload.sub, email: payload.email, name: payload.name });
      } catch (e) {
        localStorage.removeItem("token");
        setToken(null);
        setRole(null);
        setUser(null);
      }
    }
  }, [token]);

  const login = (jwtToken) => {
    setToken(jwtToken);
    localStorage.setItem("token", jwtToken);
  };

  const logout = () => {
    setToken(null);
    localStorage.removeItem("token");
    setUser(null);
    setRole(null);
    window.dispatchEvent(new Event("authLogout"));
  };

  const value = { token, user, role, login, logout };

  return createContext(value);
};