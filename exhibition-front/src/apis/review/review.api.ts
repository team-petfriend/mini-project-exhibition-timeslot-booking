import { publicApi } from "../common/axiosInstance"
import { REVIEW_PATH } from "./review.path"
import type { ReveiwCreateResponse, ReveiwFileUploadResponse, ReveiwListResponse, ReviewFixResponse, ReviewRemoveResponse } from "@/types/review/review.dto"
import type { ResponseDto } from "@/types/common/ResponseDto"

export const reviewApi = {
  list: async () : Promise<ReveiwListResponse> => {
    const res = await publicApi.get<ResponseDto<ReveiwListResponse>>(
      REVIEW_PATH.ROOT
    )
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("리뷰 목록 응답 데이터가 올바르지 않습니다.");
    }
  },
  createReview: async () : Promise<ReveiwCreateResponse> => {
    const res = await publicApi.get<ResponseDto<ReveiwCreateResponse>>(
      REVIEW_PATH.ROOT
    )
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("리뷰 작성하기 위한 데이터가 올바르지 않습니다.");
    }
  },
  fileUpload: async () : Promise<ReveiwFileUploadResponse> => {
    const res = await publicApi.get<ResponseDto<ReveiwFileUploadResponse>>(
      REVIEW_PATH.ROOT
    )
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("리뷰 사진을 올리기 위한 데이터가 올바르지 않습니다.");
    }
  },
  reviewFix: async (reviewId: number) : Promise<ReviewFixResponse> => {
    const res = await publicApi.put<ResponseDto<ReviewFixResponse>>(
      REVIEW_PATH.BY_ID(reviewId)
    )
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("리뷰 수정을 위한 데이터가 올바르지 않습니다.");
    }
  },
  reviewRemove: async (reviewId: number) : Promise<ReviewRemoveResponse> => {
    const res = await publicApi.delete<ResponseDto<ReviewRemoveResponse>>(
      REVIEW_PATH.BY_ID(reviewId)
    )
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("리뷰 삭제를 위한 데이터가 올바르지 않습니다.");
    }
  },
}