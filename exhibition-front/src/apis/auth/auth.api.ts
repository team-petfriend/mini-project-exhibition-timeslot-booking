import { publicApi } from "../common/axiosInstance";
import type { ApiResponse } from "@/types/common/ApiResponse";
import { AUTH_PATH } from "./auth.path";
import type { LoginRequest, LoginResponse, LogoutRequest, LogoutResponse, RefreshRequest, RefreshResponse, SignupRequest, SignupResponse } from "@/types/auth/auth.dto";

export const authApi = {
  signup: async (req: SignupRequest): Promise<SignupResponse> => {
    const res = await publicApi.post<ApiResponse<SignupResponse>>(
      AUTH_PATH.SIGNUP,
      req
    )
    return res.data.data;
  },
  login: async (req: LoginRequest): Promise<LoginResponse> => {
    const res = await publicApi.post<ApiResponse<LoginResponse>>(
      AUTH_PATH.LOGIN,
      req
    );
    return res.data.data;
  },

  refresh: async (req: RefreshRequest): Promise<RefreshResponse> => {
    const res = await publicApi.post<ApiResponse<RefreshResponse>>(
      AUTH_PATH.REFRESH,
      req
    )
    return res.data.data
  },

  logout: async (req: LogoutRequest): Promise<LogoutResponse> => {
    const res = await publicApi.post<ApiResponse<LogoutResponse>>(
      AUTH_PATH.LOGOUT,
      req
    );
    return res.data.data;
  },
};
