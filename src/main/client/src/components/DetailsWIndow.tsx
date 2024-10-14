import {PlaceInfoDTO} from "../dto/dto.ts";
import React from "react";

interface Props {
  placeInfo: PlaceInfoDTO | null;
}

export const DetailsWindow: React.FC<Props> = ({placeInfo}: Props) => {
  return (placeInfo &&
      <div style={{flex: 1, marginLeft: '20px'}}>
          <h2>Информация о месте</h2>
          <h3>{placeInfo.name}</h3>
          <p>{placeInfo.description}</p>
          <p>Адрес: {placeInfo.address}</p>
        {placeInfo.tags.length > 0 && (
          <div>
            <h4>Теги:</h4>
            <div>
              {placeInfo.tags.map((tag, index) => (
                <span key={index} style={{marginRight: '10px'}}>{tag}</span>
              ))}
            </div>
          </div>
        )}
        {placeInfo.images.length > 0 && (
          <div style={{
            display: 'flex',
            overflowX: 'auto',
            whiteSpace: 'nowrap',
            gap: '10px'
          }}>
            {placeInfo.images.map((image, index) => (
              <img
                key={index}
                src={image}
                alt={`Изображение ${index + 1}`}
                style={{
                  width: 'auto',
                  height: '200px'
                }}
              />
            ))}
          </div>
        )}
      </div>
  )
}