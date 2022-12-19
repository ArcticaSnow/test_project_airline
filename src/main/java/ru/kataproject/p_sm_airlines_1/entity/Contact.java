package ru.kataproject.p_sm_airlines_1.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Сущность Contact.
 * @author Alexey Sen (alexey.sen@gmail.com)
 * @since 21.11.2022
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "contact",
        uniqueConstraints = {
                @UniqueConstraint(name = "UniqueContactAddress", columnNames = {"type", "value"})
        })
public class Contact {
    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Contact Type.
     */
    @Column(name = "type", length = 32, nullable = false)
    @Enumerated(EnumType.STRING)
    private ContactType type;

    /**
     * Contact Value.
     */
    @Column(name = "value")
    private String value;

    /**
     * Preferred Contact.
     */
    @Column(name = "preferred_contact")
    private Boolean preferredContact;
}
