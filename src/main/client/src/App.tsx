import {useState} from 'react'
import {ResultsGroup, SearchElement} from "./components/SearchGroup.tsx";
import {LocationDTO, LocationInfoDTO, PlaceInfoDTO} from "./dto/dto.ts";
import {PlacesApiV1Url} from "./components/Consts.ts";
import axios, {AxiosError} from "axios";
import {DetailsWindow} from "./components/DetailsWIndow.tsx";

const SearchRadius = 1000;

class CustomError extends Error {
  constructor(message: string) {
    super("Error occurred while fetching places:" + message);
  }
}

/**
 * @returns {Promise<LocationDTO[]>}
 * @throws {CustomError}
 * @param location
 */
const FetchLocations = async (location: string): Promise<LocationDTO[]> => {
  try {
    const response = await axios.get<LocationDTO[]>(
      `${PlacesApiV1Url}/search_location?place=${location}`
    );
    console.info(response.data);
    return response.data;
  } catch (error: any) {
    if (error instanceof AxiosError) {
      if (!error.response) {
        throw new CustomError(`FetchLocations: network error or server is not reachable ${error}`)
      } else {
        throw new CustomError(`FetchLocations: network error status: ${error.status}`)
      }
    }
    throw new CustomError(`FetchLocations: error ${error}`)
  }
}

/**
 * @param {string}
 * @returns {Promise<LocationInfoDTO[]>}
 * @throws {CustomError}
 */
const FetchPlaces = async ({
                             lat, lng
                           }: LocationDTO): Promise<LocationInfoDTO[]> => {
  try {
    const response = await axios.get<LocationInfoDTO[]>(
      `${PlacesApiV1Url}/populated_places?lat=${lat}&lan=${lng}&r=${SearchRadius}`
    );
    console.info(response.data);
    return response.data;
  } catch (error: any) {
    if (error instanceof AxiosError) {
      if (!error.response) {
        throw new CustomError(`FetchPlaces: network error or server is not reachable ${error}`)
      } else {
        throw new CustomError(`FetchPlaces: network error status: ${error.status}`)
      }
    }
    throw new CustomError(`FetchPlaces: error ${error}`)
  }
}

/**
 * @param {LocationInfoDTO}
 * @returns {Promise<PlaceInfoDTO[]>}
 * @throws {CustomError}
 */
const FetchPlaceInfo = async ({id}: LocationInfoDTO): Promise<PlaceInfoDTO> => {
  try {
    const response = await axios.get<PlaceInfoDTO>(
      `${PlacesApiV1Url}/place_info?xid=${id}`
    );
    console.info(response.data);
    return response.data;
  } catch (error: any) {
    if (error instanceof AxiosError) {
      if (!error.response) {
        throw new CustomError(`FetchPlaceInfo: network error or server is not reachable ${error}`)
      } else {
        throw new CustomError(`FetchPlaceInfo: network error status: ${error.status}`)
      }
    }
    throw new CustomError(`FetchPlaceInfo: error ${error}`)
  }
}

const App = () => {
  const [locations, setLocations] = useState<LocationDTO[] | null>(null);
  const [place, setPlace] = useState<PlaceInfoDTO | null>(null)

  return (
    <>
      <div style={{display: 'flex'}}>
        <div>
          <SearchElement
            handleSearch={
              async (query: string) => setLocations(
                await FetchLocations(query)
              )
            }
          />
          <ResultsGroup locations={locations}
                        FetchPlaces={FetchPlaces}
                        FetchPlaceInfo={async (location: LocationInfoDTO) => {
                          try {
                            setPlace(await FetchPlaceInfo(location))
                          } catch (error: any) {
                          }
                        }}
          />
        </div>
        <DetailsWindow placeInfo={place}/>
      </div>
    </>
  )
}

export default App
