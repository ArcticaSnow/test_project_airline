package ru.kataproject.p_sm_airlines_1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "destination")
public class Destination {

    /**
     * Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * City
     */
    @NotEmpty
    @Column(name = "city")
    private String city; //город

    /**
     * Country Code
     */
    @NotEmpty
    @Column(name = "country_code")
    private String countryCode; // Код страны

    /**
     * Country name
     */
    @NotEmpty
    @Column(name = "country_name")
    private String countryName; // Название страны

    /**
     * Airport Name
     */
    @NotEmpty
    @Column(name = "airport_name")
    private String airportName; // название аэропорта

    /**
     * Airport Code
     */
    @NotEmpty
    @Column(name = "airport_code")
    private String airportCode; // код аэропорта

    /**
     * Timezone
     */
    @NotEmpty
    @Column(name = "timezone")
    private int timezone; // часовой пояс

    /**
     * Connection of DestinationFrom with Route
     */
    @OneToMany(mappedBy = "destinationFrom", cascade = CascadeType.ALL) // список пунктов назначения из
    private List<Route> routes;

    /**
     * Connection of DestinationTo with Route
     */
    @OneToMany(mappedBy = "destinationTo", cascade = CascadeType.ALL) // список пунктов назначения в
    private List<Route> routeList;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Destination)) return false;
        Destination destination = (Destination) o;
        return Objects.equals(getId(), destination.getId())
                && Objects.equals(getCity(), destination.getCity())
                && Objects.equals(getCountryCode(), destination.getCountryCode())
                && Objects.equals(getCountryName(), destination.getCountryName())
                && Objects.equals(getAirportName(), destination.getAirportName())
                && Objects.equals(getAirportCode(), destination.getAirportCode())
                && Objects.equals(getTimezone(), destination.getTimezone())
                && getRoutes().containsAll(destination.getRoutes())
                && destination.getRoutes().containsAll(getRoutes())
                && getRouteList().containsAll(destination.getRouteList())
                && destination.getRouteList().containsAll(getRouteList());
    }

    @Override
    public int hashCode() {
       return Objects.hash(getId(), getCity(), getCountryCode(), getCountryCode(),
                getCountryName(), getAirportName(), getAirportCode(), getTimezone(), getRoutes(), getRouteList());
    }
}