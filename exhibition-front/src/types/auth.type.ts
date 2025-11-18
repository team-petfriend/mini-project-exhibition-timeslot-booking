// auth.type.ts

export interface SignupRequest{}

export interface SignupResponse{}

export interface LoginRequest {
  username: string;
  pasword: string;
}

export interface LoginResponse {
  accessToken: string;
  expireTime: number;
}

export interface RefreshRequest {}

export interface RefreshResponse {}

export interface LogoutRequest {}

export interface LogoutResponse {}

