package com.softwarefoundation.parkingapi.entity.dto;

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

    private String username;
    private String password;

}
