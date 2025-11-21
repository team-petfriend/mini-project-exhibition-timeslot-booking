// 타임 슬롯 생성
export interface TimeslotDetailResponse {
  venueId: number;
  exhibitionId: number;
  startTime: string;
  endTime: string;
  capacity: number;
  status: string;
}

export interface TimeslotCreateRequest {
  venueId: number;
  exhibitionId: number;
  startTime: string;
  endTime: string;
  capacity: number;
  status?: string;
}

// 타임 슬롯 상태변경
export interface TimeslotStatusChangeRequest {
  venueId: number;
  exhibitionId: number;
  status?: string;
}
