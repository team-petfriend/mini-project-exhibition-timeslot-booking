import type { VenuesCreateRequestDto, VenueDetailResponseDto, VenuesUpdateRequestDto, VenueSummaryDto, VenuesSearchRequest, VenuePageRequest } from "@/types/venues/venues.type";
import { publicApi } from "../common/axiosInstance";
import type { ResponseDto } from "@/types/common/ResponseDto";
import { VENUES_PATH } from "./venues.path";
import { VENUES_FILE_PATH } from "./venues.file";
import type { PageResponseDto } from "@/types/common/utils/pageable/PageResponseDto";

export const venueApi = {
  // 생성
  createVenue: async (req: VenuesCreateRequestDto): Promise<VenueDetailResponseDto> => {
    const res = await publicApi.post<ResponseDto<VenueDetailResponseDto>> (
      VENUES_PATH.CREATE,
      req,
    );
    if ( !res.data.data ) {
      throw new Error("서버에서 venue의 데이터가 반환되지 않았습니다.")
    }
    return res.data.data;
  },

  // 전체 조회
  getAllVenues: async (params: VenuePageRequest): Promise<PageResponseDto<VenueSummaryDto>> => {
    const res = await publicApi.get<ResponseDto<PageResponseDto<VenueSummaryDto>>> (
      VENUES_PATH.LIST,
    {
      params
    }  
    );
    if ( !res.data.data ) {
      throw new Error("venue 데이터를 불러오지 못해 정보를 들고 오는데 실패했습니다.")
    }
    return res.data.data;
  },

  // 단건 조회
  getByIdVenue: async (id: number): Promise<VenueDetailResponseDto> => {
    const res = await publicApi.get<ResponseDto<VenueDetailResponseDto>> (
      VENUES_PATH.VENUES_BY_ID(id),
    );
    if ( !res.data.data ) {
      throw new Error("venue 데이터를 불러오지 못해 정보를 들고 오는데 실패했습니다.")
    }
    return res.data.data;
  },

  // 수정 
  updateVenue: async (id: number, req: VenuesUpdateRequestDto): Promise<VenueDetailResponseDto> => {
    const res = await publicApi.put<ResponseDto<VenueDetailResponseDto>> (
      VENUES_PATH.VENUES_BY_ID(id),
      req,
    );
    if ( !res.data.data ) {
      throw new Error("venue 데이터를 불러오지 못해 수정에 실패했습니다.")
    }
    return res.data.data;
  },

  // 삭제
  deleteVenue: async (id: number) : Promise<void> => {
    const res = await publicApi.delete<ResponseDto<void>> (
      VENUES_PATH.VENUES_BY_ID(id),
    );
    if ( !res.data) {
      throw new Error("venue 데이터를 불러오지 못해 삭제에 실패했습니다.")
    }
  },

  searchVenuesByName: async (params: VenuesSearchRequest): Promise<PageResponseDto<VenueSummaryDto>> => {
    const res = await publicApi.get<ResponseDto<PageResponseDto<VenueSummaryDto>>> (
      VENUES_PATH.SEARCH,
      {
        params
      }
    );
    if(!res.data.data) {
      throw new Error("venue의 데이터를 불러오지 못했습니다.")
    }
    return res.data.data;
  },

  // venue 파일 생성
  uploadFile: async (id: number, formData: FormData ) : Promise<void> => {
    const res = await publicApi.post<ResponseDto<void>> (
      VENUES_FILE_PATH.VENUE_CREATE_FILE(id),
      formData
    );
    if( !res.data ) {
      throw new Error("file 생성에 실패했습니다.")
    }
  }
}