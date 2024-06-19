package com.microserviciovalidacion.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microserviciovalidacion.data.DataTest;
import com.microserviciovalidacion.dto.UsuarioDto;
import com.microserviciovalidacion.services.UsuarioService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UsuarioService usuarioService;

    @Test
    void geAllUser_return_ListUsuarioDto() throws Exception {
        when(usuarioService.getAllUsuario()).thenReturn(DataTest.listaUsuariosDto());

        mockMvc.perform(MockMvcRequestBuilders.get("/api")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].nombre").value("juan"));

        verify(usuarioService,atLeastOnce()).getAllUsuario();
    }
    @Test
    void geAllUser_return_ServerError() throws Exception {
        when(usuarioService.getAllUsuario()).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/api")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());

        verify(usuarioService,atLeastOnce()).getAllUsuario();
    }
    @Test
    void createUsuario_return_UsuarioDto() throws Exception {
        UsuarioDto usuarioRequest=UsuarioDto.builder().nombre("juan").build();
        when(usuarioService.createUser(any(UsuarioDto.class))).thenReturn(DataTest.oneUserDto());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuarioRequest)))

                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("juan"));

        verify(usuarioService).createUser(usuarioRequest);
    }

    @Test
    void getAllUserFeing_return_ListUsuarioDto() throws Exception {

        when(usuarioService.getAllUsuarioOpenFeing()).thenReturn(DataTest.listaUsuariosDto());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/feing")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].nombre").value("eduardo"));

        verify(usuarioService,atLeastOnce()).getAllUsuarioOpenFeing();
    }
    @Test
    void getAllUserFeing_return_serverError() throws Exception {

        when(usuarioService.getAllUsuarioOpenFeing()).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/feing")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());

        verify(usuarioService,atLeastOnce()).getAllUsuarioOpenFeing();
    }

    @Test
    void createUsuarioFeing_return_UsuarioDto() throws Exception {
        UsuarioDto usuarioRequest=UsuarioDto.builder().nombre("juan").build();

        when(usuarioService.createUserOpengFeing(any(UsuarioDto.class))).thenReturn(DataTest.oneUserDto());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/feing/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuarioRequest)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("juan"));

        verify(usuarioService).createUserOpengFeing(usuarioRequest);
    }
}