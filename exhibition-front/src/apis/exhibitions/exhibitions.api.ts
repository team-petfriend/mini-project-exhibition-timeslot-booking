import type { ExhibitionsCreateRequestDto, ExhibitionDetailResponseDto, ExhibitionsStatusUpdateRequestDto, ExhibitionsUpdateRequestDto, ExhibitionSummaryDto, ExhibitionsPageRequest, ExhibitionsSearchRequest } from "@/types/exhibitions/exhibitions.type";
import { publicApi } from "../common/axiosInstance";
import type { ResponseDto } from "@/types/common/ResponseDto";
import { VENUES_EXHIBITIONS_PATH } from "./exhibitions.path";
import { EXHIBITIONS_FILE_PATH } from "./exhibitions.file";
import type { PageResponseDto } from "@/types/common/utils/pageable/PageResponseDto";

export const exhibitionApi = {

  // 생성
  createdExhibition: async (venueId: number,  req: ExhibitionsCreateRequestDto): Promise<ExhibitionDetailResponseDto> => {
    const res = await publicApi.post<ResponseDto<ExhibitionDetailResponseDto>> (
      VENUES_EXHIBITIONS_PATH.EXHIBITIONS(venueId),
      req
    );

    if (!res.data.data) {
      throw new Error("전시회를 생성하지 못했습니다.")
    }

    return res.data.data;
  },

  // 전체 조회
  getAllExhibition: async (venueId: number, params: ExhibitionsPageRequest) : Promise<PageResponseDto<ExhibitionSummaryDto>> => {
    const res = await publicApi.get<ResponseDto<PageResponseDto<ExhibitionSummaryDto>>> (
      VENUES_EXHIBITIONS_PATH.EXHIBITIONS(venueId),
      {
        params
      }
    );

    if (!res.data.data) {
      throw new Error("전시회를 불러오지 못했습니다.")
    }
    return res.data.data;
  },

  getByIdExhibition: async (venueId: number, exhibitionId: number) : Promise<ExhibitionDetailResponseDto> => {
    const res = await publicApi.get<ResponseDto<ExhibitionDetailResponseDto>> (
      VENUES_EXHIBITIONS_PATH.EXHIBITIONS_BY_ID(venueId, exhibitionId),
    ); 
    if (!res.data.data) {
      throw new Error("전시회를 불러오지 못했습니다.");
    }
    
    return res.data.data;
  },

  // 수정 
  updatedExhibition: async (venueId:number, exhibitionId: number, req: ExhibitionsUpdateRequestDto): Promise<ExhibitionDetailResponseDto> => {
    const res = await publicApi.put<ResponseDto<ExhibitionDetailResponseDto>> (
      VENUES_EXHIBITIONS_PATH.EXHIBITIONS_BY_ID(venueId, exhibitionId),
      req
    );
    if (!res.data.data) {
      throw new Error("수정에 실패했습니다.")
    }
    return res.data.data;
  },

  // 삭제
  deletedExhibition: async (venueId:number, exhibitionId: number): Promise<void> => {
    const res = await publicApi.delete<ResponseDto<void>> (
      VENUES_EXHIBITIONS_PATH.EXHIBITIONS_BY_ID(venueId, exhibitionId)
    );

    if (!res.data) {
      throw new Error("삭제에 실패했습니다.");
    }
  },
   // 상태
  changeStatusExhibition: async (venueId:number, exhibitionId: number, req: ExhibitionsStatusUpdateRequestDto): Promise<ExhibitionDetailResponseDto> => {
    const res = await publicApi.put<ResponseDto<ExhibitionDetailResponseDto>> (
      VENUES_EXHIBITIONS_PATH.STATUS(venueId, exhibitionId),
      req
    );
    if (!res.data.data) {
      throw new Error("상태 수정에 실패했습니다.");
    }
    return res.data.data;
  },

  // 검색
  searchExhibition: async (venueId:number, params: ExhibitionsSearchRequest): Promise<PageResponseDto<ExhibitionSummaryDto>> => {
    const res = await publicApi.get<ResponseDto<PageResponseDto<ExhibitionSummaryDto>>> (
      VENUES_EXHIBITIONS_PATH.SEARCH(venueId,),
      {
        params
      }
    );
    
    if (!res.data.data) {
      throw new Error("존재하는 검색어가 없습니다.");
    }

    return res.data.data;
  },

  // 파일 생성
  uploadFileExhibition: async (venueId:number, exhibitionId:number, formData: FormData) : Promise<void> => {
    const res = await publicApi.post<ResponseDto<void>>(
      EXHIBITIONS_FILE_PATH.EXHIBITIONS_FILE(venueId, exhibitionId),
      formData,
      { headers: {"Content-Type" : "multipart/form-data"}}
    ); 

    if (!res.data.data) {
      throw new Error ("파일을 생성하는데 실패했습니다.")
    }
    return res.data.data;
  },

  // 파일 삭제
  deletedFileExhibition: async (venueId: number, exhibitionId: number, fileId: number): Promise<void> => {
    const res = await publicApi.delete<ResponseDto<void>>(
      EXHIBITIONS_FILE_PATH.EXHIBITIONS_FILE_ID(venueId, exhibitionId, fileId)
    );
    if(!res.data) {
      throw new Error("파일 삭제를 실패했습니다.");
    }
  },


  // 파일 수정
  updatedFileExhibition: async (venueId: number, exhibitionId: number, fileId: number, formData: FormData): Promise<void> => {
    const res = await publicApi.put<ResponseDto<void>> (
      EXHIBITIONS_FILE_PATH.EXHIBITIONS_FILE_ID(venueId, exhibitionId, fileId),
      formData,
      { headers: {"Content-Type" : "multipart/form-data"}}
    );
    if (!res.data.data) {
      throw new Error("파일 수정에 실패했습니다.");
    }
    return res.data.data;
  }
}