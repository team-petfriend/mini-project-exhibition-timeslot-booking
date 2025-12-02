import { BASE } from "../common/base.path";
import { USER_PATH } from "../user/user.path";

const ROLE_PREFIX = `${BASE}/roles`;
const ROLE_DETAIL = `${USER_PATH.BY_ID}`;

export const ROLE_PATH = {
  ROOT: ROLE_PREFIX,

  GRANT: `${ROLE_DETAIL}/roles`,
  COLLECT: ( roleName: String ) => `${ROLE_DETAIL}/roles/${roleName}`,
}