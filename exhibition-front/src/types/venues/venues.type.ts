// 전체 조회
export interface VenuesListDto {
  venueId: number;
  name: string;
	address:string;
	imgUrl: string;
}

// 전제 조회 배열 반환 
export type VenuesListResponse = VenuesListDto[];

// 단건 조회
export interface VenuesDetailResponse {
  venueId: number;
  name: string;
  address?:string;
  imgUrl: string;
  latitude?: number;
  longitude?: number;
}

// 생성
export interface VenuesCreateRequest {
  name: string;
  address?: string;
  latitude?:  number;
  longitude?: number;
}

// 수정
export interface VenuesUpdateRequest {
  venueId: number;  
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

// 파일 수정
export interface VenuesFileUpdateRequest {
  venueId: number;
  fileId: number;
  newFile?: File;
}

