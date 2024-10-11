import {useState} from 'react'
import {ResultsGroup, SearchElement} from "./components/SearchGroup.tsx";
import {LocationDTO} from "./dto/dto.ts";

function App() {
  const [locations, setLocations] = useState<LocationDTO[] | null>(null)
  return (
    <>
      <div style={{display: 'flex'}}>
        <div>
          <SearchElement handleSearch={}/>
          <ResultsGroup locations={locations}
                        searchSubResults={}
                        onLocationChosen={}
          />
        </div>

      </div>
    </>
  )
}

export default App
