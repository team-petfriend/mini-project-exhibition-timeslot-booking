import { BASE } from "../common/base.path";

const REVIEW_PREFIX = `${BASE}/reviews`;

export const REVIEW_PATH = {
  BY_ID: ( reviewId: number ) => `${REVIEW_PREFIX}/${reviewId}`,
}