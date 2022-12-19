package ru.kataproject.p_sm_airlines_1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Class Document.
 * Implements Document Entity.
 *
 * @author Mishin Yura (mishin.inbox@gmail.com)
 * @since 07.10.2022
 */
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "documents")
@ToString
public class Document {
    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    private Long id;

    /**
     * Document Type.
     */
    @Column(name = "type", length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private DocumentType type;

    /**
     * Document number.
     */
    @Column(name = "number", length = 50, nullable = false)
    private String number;

    /**
     * Document expiry date.
     */
    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    /**
     * Passenger (owner of the document).
     */
    // TODO: 13.10.2022 Добавить Passenger
//    private Long passengerId;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "passenger_id")
    @JsonIgnore
    @ToString.Exclude
    private Passenger passenger;

    /**
     * Created at.
     */
    @Setter(AccessLevel.NONE)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    /**
     * Updated at.
     */
    @Setter(AccessLevel.NONE)
    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    /**
     * Version.
     */
    @Setter(AccessLevel.NONE)
    @Version
    @Column(name = "version", nullable = false)
    @ToString.Exclude
    private Long version = 1L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Document)) return false;
        Document document = (Document) o;
        return Objects.equals(getId(), document.getId()) && getType() == document.getType()
                && Objects.equals(getNumber(), document.getNumber())
                && Objects.equals(getExpiryDate(), document.getExpiryDate());

    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType(), getNumber(), getExpiryDate()
        );
    }
}
