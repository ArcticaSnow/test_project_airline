package ru.kataproject.p_sm_airlines_1.entity.Dto;

import lombok.Value;

import javax.validation.constraints.NotEmpty;

/**
 *Сущность для аутентификации пользователя по логину и паролю
 *
 * @author Toboe512
 */
@Value
public class AuthRequestDto {
    @NotEmpty
    String username;
    @NotEmpty
    String password;
}
