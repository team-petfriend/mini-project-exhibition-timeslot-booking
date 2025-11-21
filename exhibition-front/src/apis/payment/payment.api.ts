// payment.api.ts

import type {
  PaymentApproveUserResDto,
  PaymentDetailResDto,
  PaymentListResponse,
  PaymentRefundResDto,
  PaymentWebhookResDto,
} from "@/types/payment/payment.dto";
import { privateApi, publicApi } from "../common/axiosInstance";
import type { ResponseDto } from "@/types/common/ResponseDto";
import { PAYMENT_PATH } from "./payment.path";

export const paymentApi = {
  getAllPayment: async (): Promise<PaymentListResponse> => {
    const res = await privateApi.get<ResponseDto<PaymentListResponse>>(
      PAYMENT_PATH.LIST
    );
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("결제 목록을 확인할 수 없습니다.");
    }
  },

  getPaymentById: async (paymentId: number): Promise<PaymentDetailResDto> => {
    const res = await publicApi.get<ResponseDto<PaymentDetailResDto>>(
      PAYMENT_PATH.BY_ID(paymentId)
    );
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("해당 id의 결제 상세 정보를 불러 올 수 없습니다.");
    }
  },

  approvePayment: async (): Promise<PaymentApproveUserResDto> => {
    const res = await privateApi.post<ResponseDto<void>>(PAYMENT_PATH.CREATE);
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("결제를 진행할 수 없습니다.");
    }
  },

  refundPayment: async (paymentId: number): Promise<PaymentRefundResDto> => {
    const res = await privateApi.post<ResponseDto<void>>(
      PAYMENT_PATH.REFUND_PAY(paymentId)
    );
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("결제 환불을 진행할 수 없습니다.");
    }
  },

  webhookPayment: async (): Promise<PaymentWebhookResDto> => {
    const res = await publicApi.post<ResponseDto<PaymentWebhookResDto>>(
      PAYMENT_PATH.WEBHOOK_PAY
    );
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("웹 훅을 수신할 수 없습니다.");
    }
  },
};
