import {PlaceInfoDTO} from "../dto/dto.ts";
import React from "react";

interface Props {
  placeInfo: PlaceInfoDTO;
}

const DetailsWindow: React.FC<Props> = ({placeInfo}: Props) => {
  return (
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
          display: 'flex',           // В одну строку
          overflowX: 'auto',         // Прокрутка по горизонтали
          whiteSpace: 'nowrap',      // Запрещаем перенос элементов на новую строку
          gap: '10px'                // Отступы между изображениями
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