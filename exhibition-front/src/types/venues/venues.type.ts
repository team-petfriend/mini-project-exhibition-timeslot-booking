import type { VenueSort } from "../common/utils/pageable/SortFields";

// 전체 조회
export interface VenueSummaryDto {
  venueId: number;
  name: string;
	address:string;
	venueImgURL?: string;
}

// 단건 조회
export interface VenueDetailResponseDto {
  id: number;
  name: string;
  address?:string;
  venueImgURL?: string;
  latitude?: number;
  longitude?: number;
  createdAt: string;
  updatedAt: string;
}

// 생성
export interface VenuesCreateRequestDto {
  name: string;
  address?: string;
  latitude?:  number;
  longitude?: number;
}

// 수정
export interface VenuesUpdateRequestDto {
  id: number;  
  name?: string;
  address?: string;
  latitude?: number;
  longitude?: number;
}

// 파일 생성
export interface VenuesFileCreateRequest {
  venueId: number;
  File: File;
}

export interface VenuesSearchRequest {
  keyword: string;
  searchType: "name" | "address" | "all";
  page?: number;
  size?: number;
  sort?: VenueSort[];
}

export interface VenuePageRequest { 
  page?: number;
  size?: number;
  sort?: VenueSort[];
}