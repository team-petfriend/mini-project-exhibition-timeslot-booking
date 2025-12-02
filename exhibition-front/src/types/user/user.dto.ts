export type RoleType = "USER" | "ADMIN" | "STAFF";

export interface AdminUserUpdateRequest {
  name: string;
  email: string;
  roles: RoleType[];
}

export interface UserMeUpdateRequest {
  name: string;
  email: string;
}

export interface UserProfileUpdateRequest {
  name: string;
}

export interface MeResponse {
  id: number;
  name: string;
  email: string;
  profileImageUrl: string;
  roles: RoleType[];
  provider: string;
}

export interface UserProfileImageResponse {
  profileImageUrl: string;
}

export interface UserResponse {
  id: number;
  name: string;
  email: string;
}

export type UserListResponse = UserResponse[];