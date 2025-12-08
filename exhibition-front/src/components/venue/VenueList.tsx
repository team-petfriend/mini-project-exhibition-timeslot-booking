/** @jsxImportSource @emotion/react */
import { useVenueList } from "@/hooks/venue/useVenueList";
import { css } from "@emotion/react";
import { useNavigate } from "react-router-dom";

function VenueList() {
  const { data, isLoading, isError } = useVenueList({ page: 1, size: 10 });
  const navigate = useNavigate();

  if (isLoading) return <p>Loading...</p>;
  if (isError) return <p>데이터를 불러오지 못했습니다.</p>;

  return (
    <div css={VenueListStyle}>
      <div className="header-content">
        <span>NEW VENUE</span>
        <p>새로운 전시장을 만나보세요!</p>
      </div>
      <div className="main-content">
        {data?.content.map((venue) => (
          <div
            key={venue.venueId}
            className="item"
            onClick={() => navigate(`/venue/${venue.venueId}`)}
          >
            <img src={venue.venueImgURL} alt={venue.name} />
            <p>{venue.name}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default VenueList;

const VenueListStyle = css`
  .header-content {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;

    span {
      margin-top: 30px;
      font-size: var(--font-size-xl);
      font-weight: var(--font-weight-bols);
    }

    p {
      color: var(--color-secondary-text);
    }
  }

  .main-content {
    margin-top: 20px;
    display: grid;
    grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
    grid-template-rows: 300px auto;
    gap: 15px;
  }

  .main-content .item {
    border: 1px solid var(--color-secondary-text);
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 10px;
  }

  .main-content .item img {
    width: 100%;
    height: 300px;
    object-fit: cover;
    margin-bottom: 10px;
  }
`;
