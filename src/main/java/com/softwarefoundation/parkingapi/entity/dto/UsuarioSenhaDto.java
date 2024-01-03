package com.softwarefoundation.parkingapi.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioSenhaDto {

    @NotBlank(message = "Informe a senha atual")
    @Size(min = 6, max = 6)
    private String senhaAtual;

    @NotBlank(message = "Informe a nova senha")
    @Size(min = 6, max = 6)
    private String novaSenha;

    @NotBlank(message = "Informe a confirmação de senha")
    @Size(min = 6, max = 6)
    private String confirmaSenha;

}
