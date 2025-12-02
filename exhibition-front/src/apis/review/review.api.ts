import { publicApi } from "../common/axiosInstance"
import { REVIEW_PATH } from "./review.path"
import type { ReveiwResponse } from "@/types/review/review.dto"
import type { ResponseDto } from "@/types/common/ResponseDto"

export const reviewApi = {
  list: async () : Promise<ReveiwResponse> => {
    const res = await publicApi.get<ResponseDto<ReveiwResponse>>(
      REVIEW_PATH.ROOT
    )
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("리뷰 목록 응답 데이터가 올바르지 않습니다.");
    }
  },
  createReview: async () : Promise<ReveiwResponse> => {
    const res = await publicApi.get<ResponseDto<ReveiwResponse>>(
      REVIEW_PATH.ROOT
    )
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("리뷰 작성하기 위한 데이터가 올바르지 않습니다.");
    }
  },
  fileUpload: async () : Promise<void> => {
    const res = await publicApi.get<ResponseDto<void>>(
      REVIEW_PATH.ROOT
    )
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("리뷰 사진을 올리기 위한 데이터가 올바르지 않습니다.");
    }
  },
  reviewFix: async (reviewId: number) : Promise<ReveiwResponse> => {
    const res = await publicApi.put<ResponseDto<ReveiwResponse>>(
      REVIEW_PATH.BY_ID(reviewId)
    )
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("리뷰 수정을 위한 데이터가 올바르지 않습니다.");
    }
  },
  reviewRemove: async (reviewId: number) : Promise<void> => {
    const res = await publicApi.delete<ResponseDto<void>>(
      REVIEW_PATH.BY_ID(reviewId)
    )
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("리뷰 삭제를 위한 데이터가 올바르지 않습니다.");
    }
  },
}