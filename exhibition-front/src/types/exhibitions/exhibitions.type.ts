export type STATUS = "SCHEDULED" | "OPEN" | "CLOSED" | "CANCELED";

export type CAPACITYPOLICY = "PER_DAY" | "PER_SLOT";

// 전시회 전체 조회
export interface ExhibitionSummaryDto {
  venueId : number;
  id: number;
  title : string;
  description : string;
  startDate: string;
  endDate: string;
  status: STATUS;
  capacityPolicy : CAPACITYPOLICY;
  exhibitionImgURL:  string[];
}

// 전시회 단건 조회
export interface ExhibitionDetailResponseDto {
  venueId : number;
  id: number;
  title: string;
  description : string;
  startDate: string;
  endDate : string;
  status: STATUS;
  capacityPolicy : CAPACITYPOLICY;
  createdAt : string;
  updatedAt : string;
  exhibitionImgURL:  string[];
  timeslots: string[];
}

// 전시회 생성
export interface ExhibitionsCreateRequestDto {
  venueId: number;
  title: string;
  description : string;
  startDate: string;
  endDate : string;
  status?: STATUS;
  capacityPolicy?: CAPACITYPOLICY;
}

// 전시회 수정 
export interface ExhibitionsUpdateRequestDto {
  venueId: number
  exhibitionId: number;
	title?: string;
	description?: string;
	startDate?: string;
  endDate?:string;
  capacityPolicy?: CAPACITYPOLICY;
}

// 전시회 상태 변경 
export interface ExhibitionsStatusUpdateRequestDto {
  venueId: number;
  exhibitionId: number;
	status: STATUS;
}

// 전시회 이미지 파일 생성
export interface ExhibitionsFileCreateRequestDto {
  files: File[];
}

// 전시회 이미지 파일 수정 
export interface ExhibitionsFileUpdateRequestDto {
  venueId: number;
  exhibitionId: number;
  fileId: number;
  newFiles?: File[];  
}