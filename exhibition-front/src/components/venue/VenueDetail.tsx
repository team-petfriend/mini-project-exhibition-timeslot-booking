/** @jsxImportSource @emotion/react */
import { useVenueDetail } from "@/hooks/venue/useDetail";
import React from "react";
import { useParams } from "react-router-dom";

const VenueDetail: React.FC = () => {
  const { venueId } = useParams<{ venueId: string }>();

  if (!venueId) return <p>잘못된 접근입니다.</p>;

  const { data: venue, isLoading, isError } = useVenueDetail(Number(venueId));

  if (isLoading) return <p>Loading...</p>;
  if (isError || !venue) return <p>Error</p>;

  return (
    <div>
      <h1>이름 :{venue.name}</h1>
      <img src={venue.venueImgURL} alt={venue.name} />
      <p>{venue.name}</p>
    </div>
  );
};

export default VenueDetail;
