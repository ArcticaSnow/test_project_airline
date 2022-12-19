package ru.kataproject.p_sm_airlines_1.entity.Dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import ru.kataproject.p_sm_airlines_1.entity.DocumentType;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Class DocumentDto.
 * Implements Document Response Dto.
 *
 * @author Mishin Yura (mishin.inbox@gmail.com)
 * @since 07.10.2022
 */
@Value
@Accessors(chain = true)
@Schema(description = "Document DTO")
@ToString
public class DocumentDto {
    /**
     * Id.
     */
    @Schema(description = "Document Id", example = "1", type = "Long")
    @JsonProperty("id")
    Long id;

    /**
     * Document Type.
     */
    @Schema(description = "Document Type", example = "NATIONAL_PASSPORT", type = "Enum")
    @JsonProperty("type")
    DocumentType type;

    /**
     * Document number.
     */
    @Schema(description = "Document number", example = "A1B2", type = "String")
    @JsonProperty("number")
    String number;

    /**
     * Document expiry date.
     */
    @Schema(description = "Document expiry date", example = "2080-12-12", type = "LocalDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("expiry_date")
    LocalDate expiryDate;

    /**
     * Passenger id.
     */
    @Schema(description = "Passenger")
    @JsonProperty("passenger")
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    //Passenger passenger;
    Long passenger;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DocumentDto)) return false;
        DocumentDto that = (DocumentDto) o;
        return Objects.equals(getId(), that.getId())
                && getType() == that.getType()
                && getNumber().equals(that.getNumber())
                && Objects.equals(getExpiryDate(), that.getExpiryDate())
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType(), getNumber(), getExpiryDate()
        );
    }
}
