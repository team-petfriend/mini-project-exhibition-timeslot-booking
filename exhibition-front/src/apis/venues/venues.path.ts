import { BASE } from "../common/base.path";

const VENUES_PREFIX = `${BASE}/venues`;

export const VENUES_PATH = {
  ROOT: VENUES_PREFIX,
  CREATE: VENUES_PREFIX,
  LIST: VENUES_PREFIX, 
  COUNT: `${VENUES_PREFIX}/count`,
  SEARCH: `${VENUES_PREFIX}/search`,
  PAGE: `${VENUES_PREFIX}/page`,

  VENUES_BY_ID: (venueId: number) => `${VENUES_PREFIX}/${venueId}`
}