package ru.kataproject.p_sm_airlines_1.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Сущность TelegramMailings.
 * @author Ekaterina Kuchmistova (katy.shamina@yandex.ru)
 * @since 14.12.2022
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="telegram_mailings")
@Entity
public class TelegramMailings {
    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * textMailing.
     */
    @Column(name = "text_mailing")
    private String textMailing;

    /**
     * mailing comment.
     */
    @Column(name = "comment")
    private String comment;
}
