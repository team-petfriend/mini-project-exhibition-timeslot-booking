import type { ApiResponse } from "@/types/common/ApiResponse"
import { publicApi } from "../common/axiosInstance"
import { USER_PATH } from "./user.path"
import type { UserDetailResponse, UserListItemResponse, UserMeResponse } from "@/types/user/user.dto"

export const userApi = {
  me: async (): Promise<UserMeResponse> => {
    const res = await publicApi.get<ApiResponse<UserMeResponse>>(
      USER_PATH.ME
    )
    return res.data.data;
  },
  patchMe: async (): Promise<UserMeResponse> => {
    const res = await publicApi.patch<ApiResponse<UserMeResponse>>(
      USER_PATH.ME
    )
    return res.data.data;
  },
  users: async (): Promise<UserListItemResponse> => {
    const res = await publicApi.get<ApiResponse<UserListItemResponse>>(
      USER_PATH.LIST
    )
    return res.data.data;
  },
  getUser: async (userId: number): Promise<UserDetailResponse> => {
    const res = await publicApi.get<ApiResponse<UserDetailResponse>>(
      USER_PATH.BY_ID(userId)
    )
    return res.data.data;
  },
  patchUser: async (userId: number): Promise<UserDetailResponse> => {
    const res = await publicApi.patch<ApiResponse<UserDetailResponse>>(
      USER_PATH.BY_ID(userId)
    )
    return res.data.data;
  },
}