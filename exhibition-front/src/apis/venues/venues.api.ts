import type { VenuesCreateRequest, VenuesDetailResponse, VenuesListResponse, VenuesUpdateRequest } from "@/types/venues/venues.type";
import { publicApi } from "../common/axiosInstance";
import type { ResponseDto } from "@/types/common/ResponseDto";
import { VENUES_PATH } from "./venues.path";
import { VENUES_FILE_PATH } from "./venues.file";

export const venueApi = {
  // 생성
  createdVenue: async (req: VenuesCreateRequest): Promise<VenuesDetailResponse> => {
    const res = await publicApi.post<ResponseDto<VenuesDetailResponse>> (
      VENUES_PATH.CREATE,
      req,
    );
    if ( !res.data.data ) {
      throw new Error("서버에서 venue의 데이터가 반환되지 않았습니다.")
    }
    return res.data.data;
  },

  // 전체 조회
  getAllVenue: async (): Promise<VenuesListResponse> => {
    const res = await publicApi.get<ResponseDto<VenuesListResponse>> (
      VENUES_PATH.LIST,
    );
    if ( !res.data.data ) {
      throw new Error("venue 데이터를 불러오지 못해 정보를 들고 오는데 실패했습니다.")
    }
    return res.data.data;
  },

  // 단건 조회
  getByIdVenue: async (venueId: number): Promise<VenuesDetailResponse> => {
    const res = await publicApi.get<ResponseDto<VenuesDetailResponse>> (
      VENUES_PATH.VENUES_BY_ID(venueId),
    );
    if ( !res.data.data ) {
      throw new Error("venue 데이터를 불러오지 못해 정보를 들고 오는데 실패했습니다.")
    }
    return res.data.data;
  },

  // 수정 
  updatedVenue: async (venueId: number, req: VenuesUpdateRequest): Promise<VenuesDetailResponse> => {
    const res = await publicApi.put<ResponseDto<VenuesDetailResponse>> (
      VENUES_PATH.VENUES_BY_ID(venueId),
      req,
    );
    if ( !res.data.data ) {
      throw new Error("venue 데이터를 불러오지 못해 수정에 실패했습니다.")
    }
    return res.data.data;
  },

  // 삭제
  deletedVenue: async (venueId: number) : Promise<void> => {
    const res = await publicApi.delete<ResponseDto<void>> (
      VENUES_PATH.VENUES_BY_ID(venueId),
    );
    if ( !res.data) {
      throw new Error("venue 데이터를 불러오지 못해 삭제에 실패했습니다.")
    }
  },

  // venue 파일 생성
  uploadFile: async (venueId: number, formData: FormData ) : Promise<void> => {
    const res = await publicApi.post<ResponseDto<void>> (
      VENUES_FILE_PATH.VENUE_CREATE_FILE(venueId),
      formData
    );
    if( !res.data ) {
      throw new Error("file 생성에 실패했습니다.")
    }
  },

  // 파일 수정
  updatedFile: async (venueId: number, fileId: number, formData: FormData) : Promise<void> =>
  {
    const res = await publicApi.put<ResponseDto<void>> (
      VENUES_FILE_PATH.VENUE_FILE_ID(venueId, fileId),
      formData
    );
    if (!res.data) {
      throw new Error("파일 수정에 실패했습니다.");
    }
  },

  // 파일 삭제
  deletedFile: async (venueId: number, fileId: number) : Promise<void> =>
  {
    const res = await publicApi.delete<ResponseDto<void>> (
      VENUES_FILE_PATH.VENUE_FILE_ID(venueId, fileId),
    );
    if (!res.data) {
      throw new Error("파일 수정에 실패했습니다.");
    }
  }

}