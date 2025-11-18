import { BASE } from "../common/base.path";

const AUTH_PREFIX = `${BASE}/auth`;

export const AUTH_PATH = {
  SIGNUP: `${AUTH_PREFIX}/signup`,
  LOGIN: `${AUTH_PREFIX}/login`,
  REFRESH: `${AUTH_PREFIX}/refresh`,
  LOGOUT: `${AUTH_PREFIX}/logout`,
}