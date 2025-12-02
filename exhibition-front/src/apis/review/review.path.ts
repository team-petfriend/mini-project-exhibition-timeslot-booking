import { BASE } from "../common/base.path";
import { VENUES_EXHIBITIONS_PATH } from "../exhibitions/exhibitions.path";

const EXHIBITIONS_PREFIX = `${VENUES_EXHIBITIONS_PATH.EXHIBITIONS_BY_ID}/reviews`

const REVIEW_PREFIX = `${BASE}/reviews`;

export const REVIEW_PATH = {
  ROOT: `${EXHIBITIONS_PREFIX}`,
  REVIEW_FILES: `${EXHIBITIONS_PREFIX}/files`,
  BY_ID: ( reviewId: number ) => `${REVIEW_PREFIX}/${reviewId}`,
}