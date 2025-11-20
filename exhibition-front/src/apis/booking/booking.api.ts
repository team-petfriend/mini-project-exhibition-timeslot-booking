import {
  type BookingCancelReqDto,
  type BookingCancelResDto,
  type BookingListResDto,
  type BookingListResponse,
  type BookingRefundReqDto,
  type BookingRefundResDto,
} from "@/types/booking/booking.dto";
import { privateApi, publicApi } from "../common/axiosInstance";
import { BOOKING_PATH } from "./booking.path";
import type { ResponseDto } from "@/types/common/ResponseDto";

export const bookingApi = {
  createBooking: async (): Promise<BookingListResponse> => {
    const res = await privateApi.post<ResponseDto<BookingListResponse>>(
      BOOKING_PATH.ROOT
    );
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("예매 생성 데이터가 만족하지 않습니다.");
    }
  },

  getBooking: async (): Promise<BookingListResponse> => {
    const res = await publicApi.get<ResponseDto<BookingListResponse>>(
      BOOKING_PATH.LIST
    );
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("불러올 예약목록이 없습니다.");
    }
  },

  getDetailBooking: async (bookingId: number): Promise<BookingListResDto> => {
    const res = await privateApi.get<ResponseDto<BookingListResDto>>(
      BOOKING_PATH.BY_ID(bookingId)
    );
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("해당 id의 얘매가 존재하지 않습니다..");
    }
  },

  cancelBooking: async (bookingId: number): Promise<BookingCancelResDto> => {
    const res = await privateApi.patch<ResponseDto<BookingCancelReqDto>>(
      BOOKING_PATH.BOOKING_CANCEL,
      bookingId
    );
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("해당id의 얘매를 찾을 수 없습니다. 취소 실패");
    }
  },

  refundBooking: async (bookingId: number): Promise<BookingRefundResDto> => {
    const res = await privateApi.patch<ResponseDto<BookingRefundReqDto>>(
      BOOKING_PATH.BOOKING_REFUND,
      bookingId
    );
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("해당 id의 얘매를 찾을 수 없습니다.: 환불 실패");
    }
  },
};
