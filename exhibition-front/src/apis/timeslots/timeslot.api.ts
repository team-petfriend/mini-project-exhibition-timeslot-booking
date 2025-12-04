import type { TimeslotCreateRequestDto, TimeslotDetailResponseDto, TimeslotStatusChangeRequestDto } from "@/types/timeslot/timeslot.type";
import { publicApi } from "../common/axiosInstance";
import type { ResponseDto } from "@/types/common/ResponseDto";
import { VENUES_EXHIBITIONS_TIMESLOTS_PATH } from "./timeslots.path";
import { VENUES_EXHIBITIONS_PATH } from "../exhibitions/exhibitions.path";

export const timeslotApi = {

    // 타입 슬롯 생성
  createdTimeSlot: async (venueId: number, exhibitionId: number, req: TimeslotCreateRequestDto): Promise<TimeslotDetailResponseDto> => {
    const res = await publicApi.post<ResponseDto<TimeslotDetailResponseDto>> (
      VENUES_EXHIBITIONS_PATH.SLOTS(venueId, exhibitionId),
      req
    );
    if (!res.data.data) {
      throw new Error("타임슬롯 생성에 실패했습니다.");
    }
    return res.data.data;
  },

  // 전시별 슬롯 목록 
  getByIdExhibitionTimeSlot: async (venueId: number, exhibitionId: number): Promise<TimeslotDetailResponseDto> => {
    const res = await publicApi.get<ResponseDto<TimeslotDetailResponseDto>> (
      VENUES_EXHIBITIONS_TIMESLOTS_PATH.TIMESLOTS(venueId, exhibitionId),
    );
    if (!res.data.data) {
      throw new Error("슬롯의 정보를 불러오지 못했습니다.")
    }
    return res.data.data;
  },

  // 슬롯 상세
  getByIdtimeSlot: async (venueId: number, exhibitionId: number, slotId: number): Promise<TimeslotDetailResponseDto> => {
    const res = await publicApi.get<ResponseDto<TimeslotDetailResponseDto>> (
      VENUES_EXHIBITIONS_TIMESLOTS_PATH.TIMESLOTS_BY_ID(venueId, exhibitionId, slotId),
    );
    
    if (!res.data.data) {
      throw new Error("슬롯의 정보를 불러오지 못했습니다.")
    }
    return res.data.data;
  },

  // 슬롯 삭제
  deletedTimeSlot: async (venueId: number, exhibitionId: number, slotId: number): Promise<void> => {
      const res = await publicApi.delete<ResponseDto<void>> (
        VENUES_EXHIBITIONS_TIMESLOTS_PATH.TIMESLOTS_BY_ID(venueId, exhibitionId, slotId),
      );
      if (!res.data) {
        throw new Error("슬롯 삭제를 실패했습니다.")
      }
    },
    
  // 슬롯 상태변경
  changedStatusTimeSlot: async (venueId:number, exhibitionId: number, slotId: number, req: TimeslotStatusChangeRequestDto): Promise<TimeslotDetailResponseDto> => {
    const res = await publicApi.put<ResponseDto<TimeslotDetailResponseDto>> (
      VENUES_EXHIBITIONS_TIMESLOTS_PATH.STATUS(venueId, exhibitionId, slotId),
      req
    );
    if (!res.data.data) {
      throw new Error("상태 변경에 실패했습니다.")
    }
    return res.data.data;
  }
}