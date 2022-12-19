package ru.kataproject.p_sm_airlines_1.entity;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Class BookingRef.
 * Implements BookingRef Entity.
 *
 * @author Alexey Sen (alexey.sen@gmail.com)
 * @since 31.10.2022
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Accessors(chain = true)
@Table(name = "book_refs",
        uniqueConstraints = {
                @UniqueConstraint(name = "UniqueRefNumber", columnNames = {"ref_number"})
        }
)
public class BookingRef {
    /**
     * Id (internal number).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    private Long id;

    /**
     * Human-readable booking number.
     */
    @Size(message = "Booking reference must have from 6 to 8 characters", min = 6, max = 8)
    @NotBlank(message = "Booking reference can't be empty")
    @Column(name = "ref_number", nullable = false, length = 8)
    private String refNumber;

    /**
     * Version
     */
    @Setter(AccessLevel.NONE)
    @Version
    @Column(name = "version", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Long version = 1L;
}

