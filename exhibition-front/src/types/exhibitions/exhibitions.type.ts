export type STATUS = "SCHEDULED" | "OPEN" | "CLOSED" | "CANCELED";

export type CAPACITYPOLICY = "PER_DAY" | "PER_SLOT";

// 전시회 전체 조회
export interface ExhibitionsListDto {
  venueId: number;
  exhibitionId: number;
  title : string;
  description : string;
  startDate: string;
  endDate: string;
  status: STATUS;
  capacityPolicy : CAPACITYPOLICY;
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
  status: STATUS;
  capacityPolicy : CAPACITYPOLICY;
  created_at : string;
  updated_at : string;
  imgUrl:  string;
}

// 전시회 생성
export interface ExhibitionsCreateRequest {
  venueId: number;
  title: string;
  description : string;
  startDate: string;
  endDate : string;
  status?: STATUS;
  capacityPolicy?: CAPACITYPOLICY;
}

// 전시회 수정 
export interface ExhibitionsUpdateRequest {
  venueId: number
  exhibitionId: number;
	title?: string;
	description?: string;
	startDate?: string;
  endDate?:string;
  capacityPolicy?: CAPACITYPOLICY;
}

// 전시회 상태 변경 
export interface ExhibitionsStatusUpdateRequest {
  venueId: number;
  exhibitionId: number;
	status: STATUS;
}

// 전시회 이미지 파일 생성
export interface ExhibitionsFileCreateRequest {
  files: File[];
}

// 전시회 이미지 파일 수정 
export interface ExhibitionsFileUpdateRequest {
  venueId: number;
  exhibitionId: number;
  fileId: number;
  newFiles?: File[];  
}