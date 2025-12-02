import { publicApi } from "../common/axiosInstance"
import { USER_PATH } from "./user.path"
import type { MeResponse, UserListResponse, UserProfileImageResponse, UserResponse } from "@/types/user/user.dto"
import type { ResponseDto } from "@/types/common/ResponseDto"

export const userApi = {
  me: async (): Promise<MeResponse> => {
    const res = await publicApi.get<ResponseDto<MeResponse>>(
      USER_PATH.ME
    )
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("프로필 조회를 위한 데이터가 올바르지 않습니다.");
    }
  },
  updateMe: async (): Promise<UserResponse> => {
    const res = await publicApi.put<ResponseDto<UserResponse>>(
      USER_PATH.ME
    )
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("당신의 id를 찾을 수 없습니다.");
    }
  },
  uploadProfile: async (): Promise<UserProfileImageResponse> => {
    const res = await publicApi.post<ResponseDto<UserProfileImageResponse>>(
      USER_PATH.PROFILE
    )
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("파일을 찾을 수 없습니다.");
    }
  },
  getUserList: async (): Promise<UserListResponse> => {
    const res = await publicApi.get<ResponseDto<UserListResponse>>(
      USER_PATH.ROOT
    )
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("사용자 목록을 불러올 수 없습니다.");
    }
  },
  getById: async (userId: number): Promise<UserResponse> => {
    const res = await publicApi.get<ResponseDto<UserResponse>>(
      USER_PATH.BY_ID(userId)
    )
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("해당 사용자의 목록을 불러올 수 없습니다.");
    }
  },
  adminUpdateUser: async (userId: number): Promise<UserResponse> => {
    const res = await publicApi.put<ResponseDto<UserResponse>>(
      USER_PATH.BY_ID(userId)
    )
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("해당 사용자의 정보를 수정 할 수 없습니다.");
    }
  },
}