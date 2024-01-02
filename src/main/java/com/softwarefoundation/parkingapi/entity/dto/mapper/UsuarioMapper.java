package com.softwarefoundation.parkingapi.entity.dto.mapper;

import com.softwarefoundation.parkingapi.entity.Usuario;
import com.softwarefoundation.parkingapi.entity.dto.UsuarioCreateDto;
import com.softwarefoundation.parkingapi.entity.dto.UsuarioResponseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

    public static Usuario toUsuario(UsuarioCreateDto usuarioCreateDto) {
        return new ModelMapper().map(usuarioCreateDto, Usuario.class);
    }

    public static UsuarioResponseDto toDto(Usuario usuario) {
        String role = usuario.getRole().name().replace("ROLE_", "");
        PropertyMap<Usuario, UsuarioResponseDto> props = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setRole(role);
            }
        };
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);
        return mapper.map(usuario, UsuarioResponseDto.class);
    }


    public static List<UsuarioResponseDto> toLisDto(List<Usuario> usuarios) {
        return usuarios.stream().map(UsuarioMapper::toDto).collect(Collectors.toList());
    }

}
