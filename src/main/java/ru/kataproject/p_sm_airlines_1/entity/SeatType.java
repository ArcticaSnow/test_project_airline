package ru.kataproject.p_sm_airlines_1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SeatType {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private SeatCategory category;

    private boolean hasWindow;

    private boolean hasAdditPlace;

    private boolean hasTv;

    @OneToMany(mappedBy = "seatType", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Seat> seats;
}
