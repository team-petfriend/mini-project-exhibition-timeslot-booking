import { publicApi } from "../common/axiosInstance"
import { USER_PATH } from "./user.path"
import type { MyProfileResponse, UserDetailResponse, UserListItemResponse, UserMeResponse } from "@/types/user/user.dto"
import type { ResponseDto } from "@/types/common/ResponseDto"

export const userApi = {
  me: async (): Promise<UserMeResponse> => {
    const res = await publicApi.get<ResponseDto<UserMeResponse>>(
      USER_PATH.ME
    )
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("프로필 조회를 위한 데이터가 올바르지 않습니다.");
    }
  },
  fixMe: async (): Promise<UserMeResponse> => {
    const res = await publicApi.put<ResponseDto<UserMeResponse>>(
      USER_PATH.ME
    )
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("당신의 id를 찾을 수 없습니다.");
    }
  },
  myProfile: async (): Promise<MyProfileResponse> => {
    const res = await publicApi.post<ResponseDto<MyProfileResponse>>(
      USER_PATH.PROFILE
    )
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("파일을 찾을 수 없습니다.");
    }
  },
  users: async (): Promise<UserListItemResponse> => {
    const res = await publicApi.get<ResponseDto<UserListItemResponse>>(
      USER_PATH.LIST
    )
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("사용자 목록을 불러올 수 없습니다.");
    }
  },
  getUser: async (userId: number): Promise<UserDetailResponse> => {
    const res = await publicApi.get<ResponseDto<UserDetailResponse>>(
      USER_PATH.BY_ID(userId)
    )
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("해당 사용자의 목록을 불러올 수 없습니다.");
    }
  },
  fixUser: async (userId: number): Promise<UserDetailResponse> => {
    const res = await publicApi.put<ResponseDto<UserDetailResponse>>(
      USER_PATH.BY_ID(userId)
    )
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("해당 사용자의 정보를 수정 할 수 없습니다.");
    }
  },
}