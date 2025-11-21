import { BASE } from "../common/base.path";

const VENUES_PREFIX = `${BASE}/venues`;

export const VENUES_FILE_PATH = {
  VENUE_CREATE_FILE: (venueId: number) => `${VENUES_PREFIX}/${venueId}/file`,

  // 파일 수정
  VENUE_FILE_ID: (venueId:number, fileId: number) => `${VENUES_PREFIX}/${venueId}/file/${fileId}`
}