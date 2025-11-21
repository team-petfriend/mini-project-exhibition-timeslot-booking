export interface SignupRequest{
  name: string;
  loginId: string;
  password: string;
  email: string;
}

export interface SignupResponse{
  name: string;
  loginId: string;
  email: string;
}

export interface LoginRequest {
  loginId: string;
  pasword: string;
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
