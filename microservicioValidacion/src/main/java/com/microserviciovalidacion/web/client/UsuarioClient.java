package com.microserviciovalidacion.web.client;

import com.microserviciovalidacion.dto.UsuarioDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "msvc-usuario",url = "http://localhost:9090/api/usuario")
public interface UsuarioClient {

    @GetMapping("/lista")
    List<UsuarioDto> getAllUsuarios();
    @PostMapping("/create")
    UsuarioDto createUsuarios(@RequestBody UsuarioDto usuarioDto);
}
