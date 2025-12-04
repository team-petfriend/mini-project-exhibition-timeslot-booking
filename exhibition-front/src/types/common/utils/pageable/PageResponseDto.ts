export interface PageResponseDto<T> {
    content: T[];
    currentPage: number;
    totalPages: number;
    totalElements: number;
    first: boolean;
    last: boolean;
}