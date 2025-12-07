//! global.tsx

import { css, Global } from "@emotion/react"

export const GlobalStyle = () => (
  <Global 
    styles={css`
    :root {
        --container-width: 1440px;
        --container-padding: 1rem;

        --primary: #4f46e5;
        --header-height: 80px;
        --footer-height: 80px;

        --color-primary: #000;
        --color-primary-hover: #6b7280;
        --color-bg: white;
        --color-text: #000;
        --color-text-hover:  #fff;
        --color-secondary-text: #999;

        --border-line-color: #999;

        --space-xs: 0.25rem;
        --space-sm: 0.5rem;
        --space-md: 1rem;
        --space-lg: 1.5rem;
        --space-xl: 2.5rem;

        --radius-sm: 4px;
        --radius-md: 8px;
        --radius-lg: 16px;

        --font-size-xs: 0.875rem;      /* 14px */
        --font-size-sm: 1rem;          /* 16px */
        --font-size-base: 1.125rem;    /* 18px */
        --font-size-lg: 1.375rem;      /* 22px */
        --font-size-xl: 1.75rem;       /* 28px */
        --font-size-2xl: 2.25rem;      /* 36px */
        --font-size-3xl: 3rem;         /* 48px */

        --font-weight-regular: 400;
        --font-weight-medium: 500;
        --font-weight-bols: 700;
      }

      /* 
        ! * 전체 선택자: 모든 요소 (실제 요소)
        > before, after - 해당 속성의 "가상 요소"는 해당 * 전체 선택자 속성에 포함되지 않음
      */
      *, *::before, *::after {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
      }

      html, body, #root {
        height: 100%;
      }

      body {
        width: 100%;
        font-family: "Noto Sans", sans-serif;
        background: #f8fafc;
        color: #000;
      }

      .container {
        width: 100%;
        max-width: var(--container-width);
        margin: 0 auto;
        padding: 0 var(--container-padding);
        box-sizing: border-box;
       }

      a {
        text-decoration: none;
        color: inherit;
      }

      ul {
        list-style: none;
      }

      
    `}
  />
);