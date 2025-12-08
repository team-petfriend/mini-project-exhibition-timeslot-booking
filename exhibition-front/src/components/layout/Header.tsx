/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import React from "react";

function Header() {
  return (
    <header css={headerStyle}>
      <div className="container">
        <div className="left">
          <p className="logo">ARTMARK</p>
        </div>
        <div className="mid">
          <p>HOME</p>
          <p>VENUE</p>
          <p>EXHIBITION</p>
          <p>EVENT</p>
        </div>
        <div className="right">
          <p>로그인</p>
          <p>회원가입</p>
        </div>
      </div>
    </header>
  );
}

export default Header;

const headerStyle = css`
  padding: 0 var(--space-md);
  border-bottom: 1px solid #e5e7eb;

  .container {
    height: var(--header-height);
    width: var(--container-width);
    padding: var(--container-padding);
    display: flex;
    align-items: center;
    justify-content: space-between;
  }

  .left,
  .mid,
  .right {
    display: flex;
    align-items: center;
    gap: var(--space-sm);
    height: var(--header-height);
    cursor: pointer;
  }

  .left {
    gap: var(--space-xl);
  }

  .logo {
    font-family: "Bungee", sans-serif;
    font-size: var(--font-size-xl);
  }

  .mid {
    font-size: var(--font-size-base);
    display: flex;
    gap: var(--space-xl);
  }

  .mid p {
    &:hover {
      color: var(--color-primary-hover);
    }
  }
`;
