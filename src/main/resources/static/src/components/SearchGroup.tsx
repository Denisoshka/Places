import {LocationDTO, LocationInfoDTO} from "../dto/dto.ts";
import React, {useState} from "react";

interface SearchProps {
  handleSearch: (query: string) => void;
}

export const SearchElement = ({handleSearch}: SearchProps) => {
  const [query, setQuery] = useState<string>("")
  return (
    <form onSubmit={() => handleSearch(query)}>
      <input
        type="text"
        value={query}
        onChange={(e) => setQuery(e.target.value)}
        placeholder="Введите локацию"
      />
      <button type="submit">Поиск</button>
    </form>
  );
}

interface ResultsProps {
  locations: LocationDTO[] | null;
  searchSubResults: (location: LocationDTO) => LocationInfoDTO[]
  onLocationChosen: (location: LocationInfoDTO) => void
}

export const ResultsGroup: React.FC<ResultsProps> = ({
                                                       locations,
                                                       searchSubResults,
                                                       onLocationChosen
                                                     }: ResultsProps) => {
  const [locationInfo, setLocationInfo] = useState<LocationInfoDTO[] | null>(null);
  return (
    <div style={{display: 'flex'}}>
      <div>
        <ul className="list-group">
          {locations && locations.map((value, index) => (
            <li className="list-group-item" key={index}
                onClick={() => setLocationInfo(searchSubResults(value))}
            >
              <h3>{value.name}</h3>
              <p>Адрес: {value.address}</p>
            </li>
          ))}
        </ul>
      </div>
      {locationInfo && locationInfo.map((value, index) => (
        <div className="list-group-item-info" key={index}
             onClick={() => onLocationChosen(value)}
        >
          <h3>{value.name}</h3>
          <p>{value.description}</p>
          <p>Адрес: {value.address}</p>
          <p>{value.isClosed ? "Закрыто" : "Открыто"}</p>
        </div>
      ))}
    </div>
  );
}