export type RoleType = "USER" | "ADMIN" | "STAFF";
export interface RoleReqest {
  roles: RoleType[];
}

export interface RoleUser {
  loginId: string;
  name: string;
}

export interface RoleResponse {
  roleName: string;
  users: RoleUser[];
}