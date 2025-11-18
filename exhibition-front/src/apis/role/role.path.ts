import { BASE } from "../common/base.path";
import { USER_PATH, USER_PREFIX } from "../user/user.path";

const ROLE_PREFIX = `${BASE}/roles`;
const ROLE_DETAIL = `${USER_PATH.BY_ID}`;

export const ROLE_PATH = {
  ROOT: ROLE_PREFIX,

  DETAIL: `${ROLE_DETAIL}/roles`,
  DETAIL_BY_ID: ( roleName: String ) => `${ROLE_DETAIL}/roles/${roleName}`,
}