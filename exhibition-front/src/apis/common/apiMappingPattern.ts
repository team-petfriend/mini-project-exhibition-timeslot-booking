// apiMappingPattern.ts

const AUTH_API = '/auth';
export const LOGIN = AUTH_API + '/login';

export const API_ROUTES = {
  AUTH: {
    LOGIN: '/auth/login',
    LOGOUT: '/auth/logout'
  },
  USERS: {
    DETAIL: (userId: number) => `/user/${userId}`,
    LIST: '/users',
  
  },
  ROLES: {
    LIST : '/roles',
    GRANT: (userId: number) => `/user/${userId}/roles`,
    COLLECT: (userId: number, roles: string) => `/user/${userId}/roles/${roles}`,
  },
  REVIEWS: {
    LIST : (exhibitionsId: number) => `/exhibitions/${exhibitionsId}/reviews`,
    DETAIL: (exhibitionsId: number, reviewId: number) => `/exhibitions/${exhibitionsId}/reviews/${reviewId}`
  },
}