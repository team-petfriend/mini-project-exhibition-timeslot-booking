import type {
  TicketListResponse,
  TicketResDto,
  TicketUseResDto,
  TicketVoidResDto,
} from "@/types/ticket/ticket.dto";
import { privateApi, publicApi } from "../common/axiosInstance";
import type { ResponseDto } from "@/types/common/ResponseDto";
import { TICKET_PATH } from "./ticket.path";

export const ticketApi = {
  getAllTicket: async (): Promise<TicketListResponse> => {
    const res = await publicApi.get<ResponseDto<TicketListResponse>>(
      TICKET_PATH.TICKET_LIST
    );
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("해당 예매의 티켓 목록을 찾을 수 없습니다.");
    }
  },

  useTicket: async (ticketId: number): Promise<TicketUseResDto> => {
    const res = await privateApi.post<ResponseDto<TicketUseResDto>>(
      TICKET_PATH.TICKET_USE(ticketId)
    );
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("해당 id의 티켓을 사용할 수 없습니다.");
    }
  },

  voidTicket: async (ticketId: number): Promise<TicketVoidResDto> => {
    const res = await privateApi.post<ResponseDto<TicketVoidResDto>>(
      TICKET_PATH.TICKET_VOID(ticketId)
    );
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("해당 id의 티켓을 무효할 수 없습니다.");
    }
  },

  scanTicket: async (): Promise<TicketResDto> => {
    const res = await privateApi.get<ResponseDto<TicketResDto>>(
      TICKET_PATH.TICKET_SCAN
    );
    if (res.data.data) {
      return res.data.data;
    } else {
      throw new Error("티켓 스캔을 할 수 없습니다.");
    }
  },
};
