interface Location {
  lat: number
  lng: number
  name: String
  address: String
}

interface NearByPlaceInfoDTO {
  name: string;
  xid: string;
  kinds: string[];
  wikidata: string;
  dist: number;
  lat: number;
  lon: number;
}

interface PlaceInfoDTO {
  name: string;
  kinds: string[];
  wikipedia: string;
  image: string;
  desc: string;
}
