import type { ApiResponse } from "@/types/common/ApiResponse";
import type { UserDetail } from "@/types/user.type";

export async function fetchUserById(userId: number): Promise<UserDetail> {
  const res = await publicApi.get<ApiResponse<UserDetail>>(
    API_ROUTES.USERS.DETAIL(userId)
  );
  return res.data.data;
}
