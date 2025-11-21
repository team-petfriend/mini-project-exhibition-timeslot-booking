import { publicApi } from "../common/axiosInstance";
import { AUTH_PATH } from "./auth.path";
import type {
  LoginRequest,
  LoginResponse,
  LogoutRequest,
  RefreshRequest,
  SignupRequest,
  SignupResponse,
} from "@/types/auth/auth.dto";
import type { ResponseDto } from "@/types/common/ResponseDto";

export const authApi = {
  signup: async (req: SignupRequest): Promise<SignupResponse> => {
    const res = await publicApi.post<ResponseDto<SignupResponse>>(
      AUTH_PATH.SIGNUP,
      req
    );
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("회원가입 응답 데이터가 존재하지 않습니다.");
    }
  },
  login: async (req: LoginRequest): Promise<LoginResponse> => {
    const res = await publicApi.post<ResponseDto<LoginResponse>>(
      AUTH_PATH.LOGIN,
      req
    );
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("로그인 응답 데이터가 존재하지 않습니다.");
    }
  },

  refresh: async (req: RefreshRequest): Promise<void> => {
    const res = await publicApi.post<ResponseDto<void>>(AUTH_PATH.REFRESH, req);
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("토큰 회수 응답 데이터가 존재하지 않습니다.");
    }
  },

  logout: async (req: LogoutRequest): Promise<void> => {
    const res = await publicApi.post<ResponseDto<void>>(AUTH_PATH.LOGOUT, req);
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("로그아웃 응답 데이터가 존재하지 않습니다.");
    }
  },
};
