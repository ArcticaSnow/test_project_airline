package ru.kataproject.p_sm_airlines_1.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Сущность Route базы данных
 *
 * @author Toboe512
 */
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "route")
public class Route {

    /**
     * Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /**
     * Destination From
     */
    @NonNull
    @ManyToOne
    @JoinColumn(name = "destination_from", referencedColumnName = "id")
    Destination destinationFrom; // пункт назначения из

    /**
     * Destination To
     */
    @ManyToOne
    @JoinColumn(name = "destination_to", referencedColumnName = "id")
    Destination destinationTo; // пункт назначения в

    /**
     * Departure Date
     */
    @NonNull
    @NotEmpty
    @Column(name = "departure_date")
    LocalDate departureDate; // дата отправления

    /**
     * Arrival Date
     */
    @NonNull
    @NotEmpty
    @Column(name = "arrival_date")
    LocalDate arrivalDate; // дата прибытия

    /**
     * Numbers of Seats
     */
    @NonNull
    @NotEmpty
    @Column(name = "number_of_seats")
    Integer numberOfSeats; // число посадочных мест

    /**
     * Category
     */
    //TODO связать с Category
    @NonNull
    @NotEmpty
    @Column(name = "category")
    String category; // категория

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return id.equals(route.id) && destinationFrom.equals(route.destinationFrom)
                && destinationTo.equals(route.destinationTo)
                && departureDate.equals(route.departureDate)
                && arrivalDate.equals(route.arrivalDate)
                && numberOfSeats.equals(route.numberOfSeats)
                && category.equals(route.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, destinationFrom, destinationTo,
                departureDate, arrivalDate, numberOfSeats, category);
    }
}
