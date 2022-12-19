package ru.kataproject.p_sm_airlines_1.util.mapper.mapStruct;

import org.mapstruct.Mapper;
import ru.kataproject.p_sm_airlines_1.entity.Destination;
import ru.kataproject.p_sm_airlines_1.entity.Dto.DestinationDTO;

/**
 * Interface DestinationMapper.
 * Declares mapper between Destination and DestinationDTO via Map Struct.
 *
 * @author Evgeny Khodov (Khodov1992@gmail.com)
 * @since - 23.11.2022
*/

@Mapper(componentModel = "spring", uses = DestinationMapper.class)
public interface DestinationMapper {
    /**
     * Method maps DestinationDTO to Destination
     *
     * @param destinationDTO DestinationDTO to map from.
     * @return Destination
     */
    Destination toModel(DestinationDTO destinationDTO);

    /**
     * Method maps Destination to DestinationDTO
     *
     * @param destination Destination to map from.
     * @return DestinationDTO
     */
    DestinationDTO toDto(Destination destination);
}
