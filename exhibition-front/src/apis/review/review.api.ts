import type { ApiResponse } from "@/types/common/ApiResponse"
import { publicApi } from "../common/axiosInstance"
import { REVIEW_PATH } from "./review.path"
import type { ReviewFixResponse, ReviewRemoveResponse } from "@/types/review/review.dto"

export const reviewApi = {
  rvfix: async (reviewId: number) : Promise<ReviewFixResponse> => {
    const res = await publicApi.patch<ApiResponse<ReviewFixResponse>>(
      REVIEW_PATH.BY_ID(reviewId)
    )
    return res.data.data
  },
  rvremove: async (reviewId: number) : Promise<ReviewRemoveResponse> => {
    const res = await publicApi.delete<ApiResponse<ReviewRemoveResponse>>(
      REVIEW_PATH.BY_ID(reviewId)
    )
    return res.data.data
  },
}