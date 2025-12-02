export interface ReviewCreateReqest {
  exhibitionId: number;
  rating: BigInteger;
  content: string;
  reivewFileId: number;
}

export interface ReviewUpdateReqest {
  rating: BigInteger;
  content: number;
}

export interface ReveiwFileListResponse {
  fileId: number;
  originalName: string;
  storedName: string;
  contentType: string;
  fileSize: number;
  downloadUrl: string;
}

export interface ReveiwListResponse {
  id: number;
  rating: BigInteger;
  content: string;
}

export interface ReveiwResponse {
  id: number;
  exhibitionId: number;
  userId: number;
  rating: BigInteger;
  content: string;
  reviewFileId: number;
}