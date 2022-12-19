package ru.kataproject.p_sm_airlines_1.entity.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Seat DTO
 *
 * @author Romanov Leonid (romanovsparta@ya.ru)
 * @since 12.10.2022
 */
@Value
@Schema(description = "Seat's DTO")
public class SeatDto {
    @Schema(description = "Seat id", example = "1", type = "Long")
    Long id;

    @NotNull
    @NotEmpty
    @Schema(description = "Alpha-numeric seat number", example = "4A", required = true, type = "String")
    String seatNumber;

    @NotNull
    @NotEmpty
    @Schema(description = "Seat Type", example = "1", required = true, type = "Long")
    Long seatType;

    @NotNull
    @NotEmpty
    @Schema(description = "Aircraft ID", type = "Long")
    Long aircraft;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeatDto seatDto = (SeatDto) o;
        return id.equals(seatDto.id) && aircraft.equals(seatDto.aircraft) && seatNumber.equals(seatDto.seatNumber) && seatType.equals(seatDto.seatType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, aircraft, seatNumber, seatType);
    }
}
