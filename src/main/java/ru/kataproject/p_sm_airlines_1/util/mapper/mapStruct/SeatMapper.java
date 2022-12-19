package ru.kataproject.p_sm_airlines_1.util.mapper.mapStruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.kataproject.p_sm_airlines_1.entity.Dto.SeatDto;
import ru.kataproject.p_sm_airlines_1.entity.Seat;
import ru.kataproject.p_sm_airlines_1.service.AircraftService;
import ru.kataproject.p_sm_airlines_1.service.SeatTypeService;


/**
 * Interface SeatMapper.
 * Declares mapper between Seat and SeatDto via MapStruct.
 *
 * @author Alexey Sen (alexey.sen@gmail.com)
 * @since - 01.11.2022
 */

@Mapper(componentModel = "spring", uses = {AircraftService.class, SeatTypeService.class})
public interface SeatMapper {
    /**
     * Method maps SeatDto to Seat
     *
     * @param seatDto SeatDto
     * @return Seat
     */
    @Mapping(source = "aircraft", target = "aircraft")
    @Mapping(source = "seatType", target = "seatType")
    Seat toModel(SeatDto seatDto);

    /**
     * Method maps Seat to SeatDto
     *
     * @param seat Seat
     * @return SeatDto
     */
    @Mapping(source = "aircraft.id", target = "aircraft")
    @Mapping(source = "seatType.id", target = "seatType")
    SeatDto toDto(Seat seat);


}
