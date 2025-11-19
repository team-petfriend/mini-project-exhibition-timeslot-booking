import { BASE } from "../common/base.path";

const VENUES_PREFIX = `${BASE}/venues`;

export const VENUES_EXHIBITIONS_PATH = {

  EXHIBITIONS: (venueId: number) => `${VENUES_PREFIX}/${venueId}/exhibitions`,

  EXHIBITIONS_BY_ID: (venueId: number, exhibitionId: number) => `${VENUES_PREFIX}/${venueId}/exhibitions/${exhibitionId}`,
  
  SEARCH: (venueId: number) => `${VENUES_PREFIX}/${venueId}/exhibitions/search`,

  PAGE:(venueId: number) => `${VENUES_PREFIX}/${venueId}/exhibitions/page`,

  STATUS: (
    venueId: number,
    exhibitionId: number
  ) => `${VENUES_PREFIX}/${venueId}/exhibitions/${exhibitionId}/status`,

  SLOTS: (
    venueId: number,
    exhibitionId: number
  ) => `${VENUES_PREFIX}/${venueId}/exhibitions/${exhibitionId}/slots`,
}
