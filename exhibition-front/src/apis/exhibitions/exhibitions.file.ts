import { BASE } from "../common/base.path";

const VENUES_EXHIBITIONS_PREFIX = `${BASE}/venues`;

export const EXHIBITIONS_FILE_PATH = {
  // 이미지 파일
  EXHIBITIONS_FILE :(venueId: number, exhibitionId: number) => `${VENUES_EXHIBITIONS_PREFIX}/${venueId}/exhibitions/${exhibitionId}/files`,

  // 이미지 파일 아이디
  EXHIBITIONS_FILE_ID :  (venueId:number, exhibitionId: number, fileId: number) => `${VENUES_EXHIBITIONS_PREFIX}/${venueId}/exhibitions/${exhibitionId}/files/${fileId}`,   
}