import { BASE } from "../common/base.path";

export const USER_PREFIX = `${BASE}/users`;

export const USER_PATH = {
  ROOT: USER_PREFIX,

  BY_ID: ( userId: number ) => `${USER_PREFIX}/${userId}`,
  ME: `${USER_PREFIX}/me`,
  PROFILE: `${USER_PREFIX}/me/profile`,
}