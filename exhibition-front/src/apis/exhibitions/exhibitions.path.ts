import { BASE } from "../common/base.path";

const VENUES_EXHIBITIONS_PREFIX = `${BASE}/venues`;

export const VENUES_EXHIBITIONS_PATH = {
  // 전체 조회
  EXHIBITIONS: (venueId: number) => `${VENUES_EXHIBITIONS_PREFIX}/${venueId}/exhibitions`,

  // 단건 조회 / 수정 / 삭제 
  EXHIBITIONS_BY_ID: (venueId: number, exhibitionId: number) => `${VENUES_EXHIBITIONS_PREFIX}/${venueId}/exhibitions/${exhibitionId}`,
  
  // 검색
  SEARCH: (venueId: number) => `${VENUES_EXHIBITIONS_PREFIX}/${venueId}/exhibitions/search`,

  // 페이지
  PAGE:(venueId: number) => `${VENUES_EXHIBITIONS_PREFIX}/${venueId}/exhibitions/page`,

 // 상태
  STATUS: (
    venueId: number,
    exhibitionId: number
  ) => `${VENUES_EXHIBITIONS_PREFIX}/${venueId}/exhibitions/${exhibitionId}/status`,

  // 타입 슬롯 생성
  SLOTS: (
    venueId: number,
    exhibitionId: number
  ) => `${VENUES_EXHIBITIONS_PREFIX}/${venueId}/exhibitions/${exhibitionId}/slots`,
}
