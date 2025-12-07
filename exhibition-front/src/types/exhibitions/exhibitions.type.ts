import type { ExhibitionSort } from "../common/utils/pageable/SortFields";

export type ExhibitionStatus = "SCHEDULED" | "OPEN" | "CLOSED" | "CANCELED";

export type CapacityPolicy = "PER_DAY" | "PER_SLOT";

// 전시회 생성
export interface ExhibitionsCreateRequestDto {
  venueId: number;
  title: string;
  description : string;
  startDate: string;
  endDate : string;
  status?: ExhibitionStatus;
  capacityPolicy?: CapacityPolicy;
  fileIds?: number[];
}

// 전시회 수정 
export interface ExhibitionsUpdateRequestDto {
  venueId: number
  exhibitionId: number;
	title?: string;
	description?: string;
	startDate?: string;
  endDate?:string;
  capacityPolicy?: CapacityPolicy;
}

// 전시회 상태 변경 
export interface ExhibitionsStatusUpdateRequestDto {
  venueId: number;
  exhibitionId: number;
	status: ExhibitionStatus;
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


// 전시회 전체 조회
export interface ExhibitionSummaryDto {
  venueId : number;
  id: number;
  title : string;
  description : string;
  startDate: string;
  endDate: string;
  status: ExhibitionStatus;
  capacityPolicy : CapacityPolicy;
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
  status: ExhibitionStatus;
  capacityPolicy : CapacityPolicy;
  createdAt : string;
  updatedAt : string;
  exhibitionImgURL:  string[];
  timeslots: string[];
}

export interface ExhibitionsSearchRequest {
  keyword: string;
  searchType: "name" | "address" | "all";
  page?: number;
  size?: number;
  sort?: ExhibitionSort[];
}

export interface ExhibitionsPageRequest { 
  page?: number;
  size?: number;
  sort?: ExhibitionSort[];
}