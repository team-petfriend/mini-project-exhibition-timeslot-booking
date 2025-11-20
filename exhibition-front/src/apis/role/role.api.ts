import { publicApi } from "../common/axiosInstance"
import { ROLE_PATH } from "./role.path"
import type { RoleAddResponse, RoleRemoveResponse, RoleResponse } from "@/types/role/role.dto";
import type { ResponseDto } from "@/types/common/ResponseDto";

export const roleApi = {
  getRole: async () : Promise<RoleResponse> => {
    const res = await publicApi.get<ResponseDto<RoleResponse>>(
      ROLE_PATH.ROOT
    )
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("권한 목록을 불러 올 수 없습니다.");
    }
  },
  add: async () : Promise<RoleAddResponse> => {
    const res = await publicApi.post<ResponseDto<RoleAddResponse>>(
      ROLE_PATH.ADD
    )
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("권한을 부여할 수 없습니다.");
    }
  },
  remove: async (roleName: string) : Promise<RoleRemoveResponse> => {
    const res = await publicApi.delete<ResponseDto<RoleRemoveResponse>>(
      ROLE_PATH.BY_NAME(roleName)
    )
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("권한을 회수할 수 없습니다.");
    }
  },
}