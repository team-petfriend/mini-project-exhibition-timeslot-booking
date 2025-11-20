import { BASE } from "../common/base.path";

const VENUES_EXHIBITIONS_TIMESLOT_PREFIX = `${BASE}/venues`;

export const VENUES_EXHIBITIONS_PREFIX = (venueId: number, exhibitionId: number) => `
  ${VENUES_EXHIBITIONS_TIMESLOT_PREFIX}/${venueId}/exhibitions/${exhibitionId}
`;

export const VENUES_EXHIBITIONS_TIMESLOTS_PATH = {
  TIMESLOTS:(venueId: number, exhibitionId: number) => 
    `${VENUES_EXHIBITIONS_PREFIX(venueId, exhibitionId)}/slots`,

  TIMESLOTS_BY_ID: (venueId: number, exhibitionId: number, slotId: number) => 
    `${VENUES_EXHIBITIONS_PREFIX(venueId, exhibitionId)}/slots/${slotId}`,

  STATUS: (venueId: number, exhibitionId: number, slotId: number) => 
    `${VENUES_EXHIBITIONS_PREFIX(venueId, exhibitionId)}/slots/${slotId}/status`,
}