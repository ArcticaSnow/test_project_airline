package ru.kataproject.p_sm_airlines_1.util.mapper.mapStruct;

import org.mapstruct.Mapper;
import ru.kataproject.p_sm_airlines_1.entity.Aircraft;
import ru.kataproject.p_sm_airlines_1.entity.Dto.AircraftDto;

/**
 * Interface AircraftMapper.
 * Declares mapper between Aircraft and AircraftDto via MapStruct.
 *
 * @author Alexey Sen (alexey.sen@gmail.com)
 * @since - 01.11.2022
 */
@Mapper(componentModel = "spring")
public interface AircraftMapper {
    /**
     * Method maps AircraftDto to Aircraft
     *
     * @param aircraftDto AircraftDto to map from.
     * @return Aircraft
     */
    Aircraft toModel(AircraftDto aircraftDto);

    /**
     * Method maps Aircraft to AircraftDto
     *
     * @param aircraft Aircraft to map from.
     * @return AircraftDto
     */

    AircraftDto toDto(Aircraft aircraft);
}
