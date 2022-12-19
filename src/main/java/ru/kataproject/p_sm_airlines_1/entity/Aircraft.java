package ru.kataproject.p_sm_airlines_1.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;
import java.util.Set;

/**
 * Сущность Aircraft
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "aircraft")
@ToString
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    @Column(name = "on_board_number")
    private String onBoardNumber;  // бортовой номер

    @NotEmpty
    @Column(name = "stamp")
    private String stamp; // марка

    @NotEmpty
    @Column(name = "model", nullable = false)
    private String model;

    @NotEmpty
    @Column(name = "year_of_release")
    private int yearOfRelease; // год выпуска

    @NotEmpty
    @OneToMany(mappedBy = "aircraft", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    @ToString.Exclude
    private Set<Seat> seats;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aircraft aircraft = (Aircraft) o;
        return id == aircraft.id && Objects.equals(onBoardNumber, aircraft.onBoardNumber)
                && Objects.equals(stamp, aircraft.stamp) && Objects.equals(model, aircraft.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, onBoardNumber, stamp, model);
    }
}

