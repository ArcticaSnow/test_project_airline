package ru.kataproject.p_sm_airlines_1.util.mapper.mapStruct;

import org.mapstruct.Mapper;
import ru.kataproject.p_sm_airlines_1.entity.Dto.PassengerDto;
import ru.kataproject.p_sm_airlines_1.entity.Passenger;

@Mapper(componentModel = "spring", uses = {PassengerMapper.class, DocumentMapper.class})
public interface PassengerMapper {
    /**
     * Maps Passenger to PassengerDto
     * @param passenger Passenger
     * @return PessengerDto
     */
    PassengerDto toDto(Passenger passenger);

    /**
     * Maps PassengerDto to Passenger
     * @param passengerDto PassengerDto
     * @return Passenger
     */
    Passenger toModel(PassengerDto passengerDto);
}
