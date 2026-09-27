import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080",
  headers: {
    "Content-Type": "application/json",
  },
});

api.interceptors.requestConfig = {
  headers: {
    Authorization: `Bearer ${localStorage.getItem("token")}`,
  },
};

api.interceptors.response.use(
  (response) => response.data,
  (error) => {
    if (error.response && error.response.status === 401) {
      localStorage.removeItem("token");
      window.dispatchEvent(new Event("authLogout"));
    }
    return Promise.reject(error);
  }
);

export default api;