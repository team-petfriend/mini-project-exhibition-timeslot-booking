import { BASE } from "../common/base.path";
import { BOOKING_PATH } from "../booking/booking.path";

const TICKET_PREFIX = `${BASE}/tickets`;

export const TICKET_PATH = {
  ROOT: TICKET_PREFIX,
  CREATE: TICKET_PREFIX,

  BY_ID: (ticketId: number) => `${TICKET_PREFIX}/${ticketId}`,

  TICKET_LIST: `${BOOKING_PATH.BY_ID}/tickets`,
  TICKET_USE: (ticketId: number) => `${TICKET_PREFIX}/${ticketId}/use`,
  TICKET_VOID: (ticketId: number) => `${TICKET_PREFIX}/${ticketId}/void`,
  TICKET_SCAN: `${TICKET_PREFIX}/scan`,
};
