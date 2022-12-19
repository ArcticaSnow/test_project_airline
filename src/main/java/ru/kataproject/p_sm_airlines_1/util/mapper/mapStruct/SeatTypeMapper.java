package ru.kataproject.p_sm_airlines_1.util.mapper.mapStruct;

import org.mapstruct.Mapper;
import ru.kataproject.p_sm_airlines_1.entity.Dto.SeatTypeDTO;
import ru.kataproject.p_sm_airlines_1.entity.SeatType;

@Mapper(componentModel = "spring")
public interface SeatTypeMapper {
    SeatType toModel(SeatTypeDTO seatTypeDTO);

    SeatTypeDTO toDTO(SeatType seatType);
}
