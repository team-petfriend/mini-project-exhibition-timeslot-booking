import axios, { type InternalAxiosRequestConfig } from "axios";

const API_BASE =
  import.meta.env.VITE_API_BASE || "http://localhost:8080/api/v1";

export const publicApi = axios.create({
  baseURL: API_BASE,
  timeout: 10000,
  headers: {
    "Content-Type": "application/json",
    Accept: "application/json",
  },
});

export const privateApi = axios.create({
  baseURL: API_BASE,
  timeout: 10000,
  headers: {
    "Content-Type": "application/json",
    Accept: "application/json",
  },
  withCredentials: true,
});

// 요청(private)
privateApi.interceptors.request.use(
  (config: InternalAxiosRequestConfig) => {
    return config;
  },
  (e) => Promise.reject(e)
);

// 응답(private)
privateApi.interceptors.response.use(
  (response) => response,
  async (e) => {
    return Promise.reject(e);
  }
);
