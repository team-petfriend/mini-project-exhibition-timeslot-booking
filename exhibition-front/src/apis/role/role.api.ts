import { publicApi } from "../common/axiosInstance"
import { ROLE_PATH } from "./role.path"
import type { RoleResponse } from "@/types/role/role.dto";
import type { ResponseDto } from "@/types/common/ResponseDto";

export const roleApi = {
  getAllRoles: async () : Promise<RoleResponse> => {
    const res = await publicApi.get<ResponseDto<RoleResponse>>(
      ROLE_PATH.ROOT
    )
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("권한 목록을 불러 올 수 없습니다.");
    }
  },
  addRoleToUser: async () : Promise<void> => {
    const res = await publicApi.post<ResponseDto<void>>(
      ROLE_PATH.GRANT
    )
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("권한을 부여할 수 없습니다.");
    }
  },
  removeRoleFromUser: async (roleName: string) : Promise<void> => {
    const res = await publicApi.delete<ResponseDto<void>>(
      ROLE_PATH.COLLECT(roleName)
    )
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("권한을 회수할 수 없습니다.");
    }
  },
}