import { BASE } from "../common/base.path";

const PAYMENT_PREFIX = `${BASE}/payments`;

export const PAYMENT_PATH = {
  ROOT: PAYMENT_PREFIX,
  LIST: PAYMENT_PREFIX,
  CREATE: PAYMENT_PREFIX,

  BY_ID: (paymentId: number) => `${PAYMENT_PREFIX}/${paymentId}`,
  REFUND_PAY: (paymentId: number) => `${PAYMENT_PREFIX}/${paymentId}/refund`,
  WEBHOOK_PAY: `$${PAYMENT_PREFIX}/webhook`,
};
