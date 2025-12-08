import { venueApi } from "@/apis/venues/venues.api"
import type { ResponseDto } from "@/types/common/ResponseDto"
import type { VenueDetailResponseDto } from "@/types/venues/venues.type"
import { useQuery } from "@tanstack/react-query"

export const useVenueDetail = (venueId: number) => {
  // ResponseDto에 포장지를 벗겨내서 작성 
  return useQuery<VenueDetailResponseDto>({
    queryKey: ["venues", "detail", venueId],
    queryFn: () => venueApi.getByIdVenue(venueId),
    enabled: !!venueId,
  });
};