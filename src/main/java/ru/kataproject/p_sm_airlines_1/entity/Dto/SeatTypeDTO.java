package ru.kataproject.p_sm_airlines_1.entity.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;
import ru.kataproject.p_sm_airlines_1.entity.SeatCategory;

@Value
@Schema(description = "Seat's DTO")
public class SeatTypeDTO {

    @Schema(description = "SeatType ID", example = "1", type = "Long")
    Long id;

    @Schema(description = "SeatCategory", example = "ECONOMY", type = "SeatCategory")
    SeatCategory category;

    @Schema(description = "Has Window", example = "true", type = "Boolean")
    boolean hasWindow;

    @Schema(description = "Has Additional Place", example = "true", type = "Boolean")
    boolean hasAdditPlace;

    @Schema(description = "Has TV", example = "true", type = "Boolean")
    boolean hasTv;

}
