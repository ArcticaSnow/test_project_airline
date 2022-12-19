package ru.kataproject.p_sm_airlines_1.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Accessors(chain = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "destination_from", nullable = false)
    private Destination destinationFrom;

    @ManyToOne(optional = false)
    @JoinColumn(name = "destination_to", nullable = false)
    private Destination destinationTo;

    @NonNull
    @NotEmpty
    private LocalDateTime departureDateTime;

    @NonNull
    @NotEmpty
    private LocalDateTime arrivalDateTime;

    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "aircraft_id", referencedColumnName = "id")
    private Aircraft aircraft;

    @NonNull
    @NotEmpty
    private FlightStatus flightStatus;

    @NonNull
    @NotEmpty
    //TODO
    private String inflightServices;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return id.equals(flight.id) && destinationFrom.equals(flight.destinationFrom)
                && destinationTo.equals(flight.destinationTo)
                && departureDateTime.equals(flight.departureDateTime)
                && arrivalDateTime.equals(flight.arrivalDateTime)
                && aircraft.equals(flight.aircraft)
                && flightStatus == flight.flightStatus
                && inflightServices.equals(flight.inflightServices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, destinationFrom,
                destinationTo, departureDateTime,
                arrivalDateTime, aircraft,
                flightStatus, inflightServices);
    }
}

