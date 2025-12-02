export type AuthProvider = "LOCAL" | "GOOGLE" | "KAKAO" | "NAVER";

export interface SignupRequest{
  name: string;
  loginId: string;
  password: string;
  email: string;
  provider: AuthProvider;
}

export interface SignupResponse{
  name: string;
  loginId: string;
  email: string;
}

export interface LoginRequest {
  loginId: string;
  password: string;
}

export interface LoginResponse {
  accessToken: string;
  expireTime: number;
}

export interface RefreshRequest {
  refreshToken: string;
}

export interface LogoutRequest {
  refreshToken: string;
}
