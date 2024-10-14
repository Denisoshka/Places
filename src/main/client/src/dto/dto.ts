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
  name: string;
  shortName: string;
  shortDescription: string;
  description: string;
  address: string;
  url: string;
  phone: string[];
  images: string[];
  categories: string[];
  tags: string[];
  isClosed: boolean;
}