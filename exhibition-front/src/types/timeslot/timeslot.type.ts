export type TIMESLOTSTATUS = 'OPEN' | 'CLOSED' | 'CANCELED';

// 타임 슬롯 생성
export interface TimeslotDetailResponseDto {
  venueId: number;
  exhibitionId: number;
  startTime: string;
  endTime: string;
  capacity: number;
  status: TIMESLOTSTATUS;
}

export interface TimeslotCreateRequestDto {
  venueId: number;
  exhibitionId: number;
  startTime: string;
  endTime: string;
  capacity: number;
  status?: TIMESLOTSTATUS;
}

// 타임 슬롯 상태변경
export interface TimeslotStatusChangeRequestDto {
  venueId: number;
  exhibitionId: number;
  slotId: number;
  status?: TIMESLOTSTATUS;
}
