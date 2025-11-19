import { BASE } from "../common/base.path";

export const USER_PREFIX = `${BASE}/users`;

export const USER_PATH = {
  ROOT: USER_PREFIX,

  LIST: USER_PREFIX,

  ME: `${USER_PREFIX}/me`,
  BY_ID: ( userId: number ) => `${USER_PREFIX}/${userId}`,
}