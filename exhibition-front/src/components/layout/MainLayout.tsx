/** @jsxImportSource @emotion/react */
import { css } from '@emotion/react';
import React, { useState } from 'react'
import Header from './Header';
import Footer from './Footer';

function MainLayout({ children }: { children: React.ReactNode }) {

  return (
    <div css={layoutStyle}>
      <Header />

      <div css={contentStyle}>
        <main css={mainStyle}>{children}</main>
      </div>

      <Footer />
    </div>
)
}

export default MainLayout

const layoutStyle = css`
  display: flex;
  flex-direction: column;
  height: 100vh;
  background: var(--color-bg);
`;

const contentStyle = css`
  flex:1;
  display: flex;
  overflow: hidden;
  transition: all 0.25s ease;
`;

const mainStyle = css`
  flex: 1;
  padding: 1.5rem;
  overflow-y: auto;
  `;