package ru.kataproject.p_sm_airlines_1.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Сущность TelegramSubscriber.
 * @author Ekaterina Kuchmistova (katy.shamina@yandex.ru)
 * @since 14.12.2022
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="telegram_subscribers")
@Entity
public class TelegramSubscriber {
    /**
     * Id.
     */
    @Id
    private Long chatId;

    /**
     * firstName.
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * lastName.
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * username.
     */
    @Column(name = "username")
    private String username;

    /**
     * registeredAt.
     */
    @Column(name = "registered_at")
    private Timestamp registeredAt;

    //TODO связать с сущностью контакт, когда Contact и остальные сущности, необходимые для корректной реализации рассылок, будут доработаны
//    private Contact contact;
}
