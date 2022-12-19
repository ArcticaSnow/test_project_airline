package ru.kataproject.p_sm_airlines_1.entity.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NonNull;
import lombok.ToString;
import lombok.Value;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Class RouteDto.
 * Implements Route Response Dto.
 *
 * @author Evgeny Khodov (Khodov1992@mail.ru)
 * @since 24.11.2022
 */

@Value
@Schema(description = "DTO of route")
@ToString
public class RouteDto {

    /**
     * Id
     */
    @Schema(description = "id", example = "1", type = "Long")
    Long id;

    /**
     * DestinationDTO From
     */
    @NonNull
    @Schema(description = "destination From")
    DestinationDTO destinationFrom;

    /**
     * DestinationDTO To
     */
    @NonNull
    @Schema(description = "destination to")
    DestinationDTO destinationTo;

    /**
     * Departure Date
     */
    @NonNull
    @Schema(description = "departure date")
    LocalDate departureDate;

    /**
     * Arrival Date
     */
    @NonNull
    @Schema(description = "arrival date")
    LocalDate arrivalDate;

    /**
     * Number of Seats
     */
    @NonNull
    @Schema(description = "number of seats")
    Integer numberOfSeats;

    /**
     * Category
     */
    //TODO связать с Category
    @NonNull
    String category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RouteDto routeDto = (RouteDto) o;
        return Objects.equals(id, routeDto.id)
                && destinationFrom.equals(routeDto.destinationFrom)
                && destinationTo.equals(routeDto.destinationTo)
                && departureDate.equals(routeDto.departureDate)
                && arrivalDate.equals(routeDto.arrivalDate)
                && numberOfSeats.equals(routeDto.numberOfSeats)
                && category.equals(routeDto.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, destinationFrom,
                destinationTo, departureDate, arrivalDate,
                numberOfSeats, category);
    }
}
