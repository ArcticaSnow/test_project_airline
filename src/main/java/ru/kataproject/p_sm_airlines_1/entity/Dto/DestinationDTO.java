package ru.kataproject.p_sm_airlines_1.entity.Dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kataproject.p_sm_airlines_1.entity.Route;

import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO of destination")
public class DestinationDTO {

    /**
     * Id
     */
    @NotNull
    @Schema(description = "ID of destination")
    private Long id;

    /**
     * City Of Destination
     */
    @NotNull
    @Schema(description = "city of destination")
    private String city;

    /**
     * Country Code Of Destination
     */
    @NotNull
    @Schema(description = "country code of destination")
    private String countryCode;

    /**
     * Country Name Of Destination
     */
    @NotNull
    @Schema(description = "country name of destination")
    private String countryName;

    /**
     * Airport Name Of Destination
     */
    @NotNull
    @Schema(description = "airport name of destination")
    private String airportName;

    /**
     * Airport Code Of Destination
     */
    @NotNull
    @Schema(description = "airport code of destination")
    private String airportCode;

    /**
     * Timezone Of Destination
     */
    @NotNull
    @Schema(description = "timezone of destination")
    private int timezone;

    /**
     * Route Of Destination
     */
    @Schema(description = "route of destination")
    private List<Route> routes = new LinkedList<>();

    /**
     * Route Of Destination
     */
    @Schema(description = "route of destination")
    private List<Route> routeList = new LinkedList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DestinationDTO)) return false;
        DestinationDTO that = (DestinationDTO) o;
        return Objects.equals(getId(), that.getId())
                && Objects.equals(getCity(), that.getCity())
                && Objects.equals(getAirportCode(), that.getCountryCode())
                && Objects.equals(getCountryName(), that.getCountryName())
                && Objects.equals(getAirportName(), that.getAirportName())
                && Objects.equals(getAirportCode(), that.getAirportCode())
                && Objects.equals(getTimezone(), that.getTimezone())
                && (getRoutes().size() == that.getRoutes().size())
                && that.getRoutes().containsAll(getRoutes())
                && (getRouteList().size() == that.getRouteList().size())
                && that.getRouteList().containsAll(getRouteList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCity(), getCountryCode(),
                getCountryName(), getAirportName(), getAirportCode(),
                getTimezone(), getRoutes(), getRouteList());
    }
}