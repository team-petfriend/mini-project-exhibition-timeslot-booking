import type { ApiResponse } from "@/types/common/ApiResponse";
import { publicApi } from "../common/axiosInstance"
import { ROLE_PATH } from "./role.path"
import type { RoleAddResponse, RoleRemoveResponse, RoleResponse } from "@/types/role/role.dto";

export const roleApi = {
  getRole: async () : Promise<RoleResponse> => {
    const res = await publicApi.get<ApiResponse<RoleResponse>>(
      ROLE_PATH.ROOT
    )
    return res.data.data;
  },
  add: async () : Promise<RoleAddResponse> => {
    const res = await publicApi.post<ApiResponse<RoleAddResponse>>(
      ROLE_PATH.ADD
    )
    return res.data.data;
  },
  remove: async (roleName: string) : Promise<RoleRemoveResponse> => {
    const res = await publicApi.delete<ApiResponse<RoleRemoveResponse>>(
      ROLE_PATH.BY_NAME(roleName)
    )
    return res.data.data;
  },
}