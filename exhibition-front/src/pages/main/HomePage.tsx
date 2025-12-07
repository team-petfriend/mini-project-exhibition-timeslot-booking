/** @jsxImportSource @emotion/react */
import React, { useState } from 'react'
import mainImg from '@/assets/mainImg.png';
import { css } from '@emotion/react';
import VenueList from '@/components/venue/VenueList';

function HomePage() {
  const [selectedVenueId, setSelectedVenueId] = useState<number | null>(null);

  return (
    <div css={HomePageStyle} className='container'>
      <img src={mainImg} alt="Main" className='img'/>
      <VenueList onChange={(id) => setSelectedVenueId(id)} />
    </div>
  )
}
    
export default HomePage

const HomePageStyle = css`

  .container {
    height: var(--header-height);
    width: var(--container-width);
    padding: var(--container-padding);
    display: flex;
    align-items: center;
    justify-content: center; 
  }

   .img {
    width: 100%; 
    height: auto;
    display: block; 
  }
`;