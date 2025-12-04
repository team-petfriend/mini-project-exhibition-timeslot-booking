export type VenueSortType = "id" | "name" | "createdAt" | "updatedAt";
export type ExhibitionSortType = "id" | "title" | "createdAt" | "updatedAt";

export type SortDirection = "desc" | "asc";

export type VenueSort = `${VenueSortType},${SortDirection}`;

export type ExhibitionSort = `${ExhibitionSortType},${SortDirection}`;
