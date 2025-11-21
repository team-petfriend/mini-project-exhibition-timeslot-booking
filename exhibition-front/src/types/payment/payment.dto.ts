// payment.dto.ts
export interface PaymentListResDto {}

export type PaymentListResponse = PaymentListResDto[];

export interface PaymentDetailResDto {}
export interface PaymentDetailReqDto {}

export interface PaymentApproveUserReqDto {}

export interface PaymentApproveUserResDto {}

export interface PaymentRefundReqDto {}

export interface PaymentRefundResDto {}

// +)선택사항
export interface PaymentWebhookReqDto {}

export interface PaymentWebhookResDto {}
