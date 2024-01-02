package com.softwarefoundation.parkingapi.entity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioCreateDto extends AbstractDto {

    @NotBlank(message = "Informe o e-mail")
    @Email(message = "Formato do e-mail inválido", regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
    private String username;

    @NotBlank(message = "Informe a senha")
    @Size(min = 6, max = 6)
    private String password;

}
