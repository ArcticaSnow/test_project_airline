package ru.kataproject.p_sm_airlines_1.util.mapper.mapStruct;

import org.mapstruct.Mapper;
import ru.kataproject.p_sm_airlines_1.entity.Dto.FlightDto;
import ru.kataproject.p_sm_airlines_1.entity.Flight;

@Mapper(componentModel = "spring")
public interface FlightMapper {
    /**
     * Maps FlightDto to Flight
     * @param flightDto FlightDto
     * @return Flight
     */
    Flight toModel(FlightDto flightDto);

    /**
     * Maps flight to FlightDto
     * @param flight Flight
     * @return FlightDto
     */
    FlightDto toDto(Flight flight);
}