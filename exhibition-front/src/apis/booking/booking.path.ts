import { BASE } from "../common/base.path";

const BOOKING_PREFIX = `${BASE}/bookings`;

export const BOOKING_PATH = {
  ROOT: BOOKING_PREFIX,
  LIST: BOOKING_PREFIX,
  CREATE: BOOKING_PREFIX,
  BY_ID: (bookingId: number) => `${BOOKING_PREFIX}/${bookingId}`,
  BOOKING_CANCEL: (bookingId: number) =>
    `${BOOKING_PREFIX}/${bookingId}/cancel`,
  BOOKING_REFUND: (bookingId: number) =>
    `${BOOKING_PREFIX}/${bookingId}/refund`,
};
