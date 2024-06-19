package com.microserviciovalidacion.data;

import com.microserviciovalidacion.dto.UsuarioDto;

import java.util.Arrays;
import java.util.List;

public class DataTest {

    public static List<UsuarioDto> listaUsuariosDto(){
        return Arrays.asList(
                 UsuarioDto.builder()
                        .usuarioId(1L)
                        .nombre("juan")
                        .build()
                ,
                 UsuarioDto.builder()
                        .usuarioId(2L)
                        .nombre("eduardo")
                        .build());
    }
    public static UsuarioDto[] usuarioDtos() {
        return new UsuarioDto[]{
                UsuarioDto.builder()
                        .usuarioId(1L)
                        .nombre("juan")
                        .build(),
                UsuarioDto.builder()
                        .usuarioId(2L)
                        .nombre("eduardo")
                        .build()
        };
    }

    public static UsuarioDto oneUserDto(){
        return UsuarioDto.builder()
                .usuarioId(1L)
                .nombre("juan")
                .build();
    }
}
