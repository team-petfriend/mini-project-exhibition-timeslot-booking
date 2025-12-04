import { BASE } from "../common/base.path";

const VENUES_PREFIX = `${BASE}/venues`;

export const VENUES_PATH = {
  // 경로 명시
  ROOT: VENUES_PREFIX,
  
  // 생성
  CREATE: VENUES_PREFIX,
  
  // 전체 조회
  LIST: VENUES_PREFIX, 
 
  // 검색
  SEARCH: `${VENUES_PREFIX}/search`,

  // 단건조회, 수정, 삭제
  VENUES_BY_ID: (venueId: number) => `${VENUES_PREFIX}/${venueId}`,
}