package ru.kataproject.p_sm_airlines_1.util.mapper.mapStruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.kataproject.p_sm_airlines_1.entity.Dto.RouteDto;
import ru.kataproject.p_sm_airlines_1.entity.Route;
import ru.kataproject.p_sm_airlines_1.service.DestinationService;
import ru.kataproject.p_sm_airlines_1.service.RouteService;


/** Interface RouteMapper.
* Declares mapper between Route and RouteDto via Map Struct.
*
* @author Evgeny Khodov (Khodov1992@gmail.com)
* @since - 23.11.2022
*/

@Mapper(componentModel = "spring", uses = {DestinationService.class, RouteService.class})
public interface RouteMapper {
   /**
    * Maps RouteDto to Route
    *
    * @param routeDto RouteDto
    * @return Route
    */
   @Mapping(source = "destinationFrom",target = "destinationFrom") // поле destinationFrom Класса Route соответствует полю destinationFrom класса RouteDto
   @Mapping(source = "destinationTo",target = "destinationTo") // аналогично
   Route toModel(RouteDto routeDto);

   /**
    * Maps route to RouteDto
    *
    * @param route Route
    * @return RouteDto
    */
   @Mapping(source = "destinationFrom",target = "destinationFrom")
   @Mapping(source = "destinationTo",target = "destinationTo")
   RouteDto toDto(Route route);
}
