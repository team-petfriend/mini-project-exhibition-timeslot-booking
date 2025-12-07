import { venueApi } from "@/apis/venues/venues.api";
import type { PageResponseDto } from "@/types/common/utils/pageable/PageResponseDto";
import type { VenuePageRequest, VenueSummaryDto } from "@/types/venues/venues.type";
import { useQuery } from "@tanstack/react-query";

export const useVenueList = (params?: VenuePageRequest) => {
  return useQuery<PageResponseDto<VenueSummaryDto>>({
    queryKey: ["venues", "list", params],
    queryFn: () => venueApi.getAllVenues(params || {page: 1, size: 10}),
  });
};