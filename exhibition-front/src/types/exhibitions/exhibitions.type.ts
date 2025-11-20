// 전시회 전체 조회
export interface ExhibitionsListDto {
  venueId: number;
  exhibitionId: number;
  title : string;
  description : string;
  startDate: string;
  endDate: string;
  status: string;
  capacityPolicy : string;
  imgUrl:  string;
}

// 전시회 전체 배열 반환
export type ExhibitionsListResponse = ExhibitionsListDto[];

// 전시회 단건 조회
export interface ExhibitionsDetailResponse {
  venueId: number;
  exhibitionId: number;
  title: string;
  description : string;
  startDate: string;
  endDate : string;
  status: string;
  capacityPolicy : string;
  created_at : string;
  updated_at : string;
  imgUrl:  string;
}

// 전시회 생성
export interface exhibitionsCreateRequest {
  venueId: number;
  title: string;
  description : string;
  startDate: string;
  endDate : string;
  status?: string;
  capacityPolicy?: string;
}

// 전시회 수정 
export interface exhibitionsUpdateRequest {
  venueId: number
  exhibitionId: number;
	title?: string;
	description?: string;
	startDate?: string;
  endDate?:string;
  capacityPolicy?: string;
}

// 전시회 상태 변경 
export interface exhibitionsStatusUpdateRequest {
  venueId: number;
  exhibitionId: number;
	status: string;
}

// 전시회 이미지 파일 생성
export interface exhibitionsFileCreateRequest {
  files: File[];
}

// 전시회 이미지 파일 수정 
export interface exhibitionsFileUpdateRequest {
  venueId: number;
  exhibitionId: number;
  fileId: number;
  newFiles?: File[];  
}