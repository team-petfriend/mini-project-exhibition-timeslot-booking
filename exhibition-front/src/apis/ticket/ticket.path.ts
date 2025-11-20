import { BASE } from "../common/base.path";
import { BOOKING_PATH } from "../booking/booking.path";

const TICKET_PREFIX = `${BASE}/tickets`;
const TICKET_BY_ID = (ticketId: number) => `${TICKET_PREFIX}/${ticketId}`;

export const TICKET_PATH = {
  ROOT: TICKET_PREFIX,
  LIST: TICKET_PREFIX,
  CREATE: TICKET_PREFIX,

  BY_ID: (ticketId: number) => `${TICKET_PREFIX}/${ticketId}`,

  TICKETS: `${BOOKING_PATH.BY_ID}/tickets`,

  TICKET_USE: `${TICKET_BY_ID}/use`,
  // TICKET_USE: (ticketId: number) => `${TICKET_PREFIX}/${ticketId}/use`,
  TICKET_VOID: `${TICKET_BY_ID}/void`,
  // TICKET_VOID: (ticketId: number) => `${TICKET_PREFIX}/${ticketId}/void`,
  TICKET_SCAN: `${TICKET_BY_ID}/scan`,
  // TICKET_SCAN: (ticketId: number) => `${TICKET_PREFIX}/${ticketId}/scan`,
};
