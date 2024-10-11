export interface LocationDTO {
  lat: number;
  lng: number;
  name: string;
  address: string;
}

export interface LocationInfoDTO {
  id: string;
  name: string;
  shortName: string;
  address: string;
  description: string;
  tags: string[];
  images: string[];
  isClosed: boolean;
  lat: number;
  lon: number;
}

export interface PlaceInfoDTO {
  id: string;
  name: string;
  description: string;
  address: string;
  tags: string[];
  images: string[];
  lat: number;
  lon: number;
}