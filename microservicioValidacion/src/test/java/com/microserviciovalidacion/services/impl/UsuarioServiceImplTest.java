package com.microserviciovalidacion.services.impl;

import com.microserviciovalidacion.data.DataTest;
import com.microserviciovalidacion.dto.UsuarioDto;
import com.microserviciovalidacion.services.UsuarioService;
import com.microserviciovalidacion.web.client.UsuarioClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UsuarioServiceImplTest {

    @Mock
    UsuarioClient usuarioClient;
    @Mock
    RestTemplate restTemplate;
    @InjectMocks
    UsuarioServiceImpl usuarioService;


    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(usuarioService, "url", "http://localhost:9090/api/usuario");
    }

    @Test
    void geAllUsuario_Return_ListUsuarioDto(){
        // Arrange
        UsuarioDto[] usuarioDtos = DataTest.usuarioDtos();
        when(restTemplate.getForObject("http://localhost:9090/api/usuario/lista", UsuarioDto[].class)).thenReturn(usuarioDtos);
        // Act
        List<UsuarioDto> result = usuarioService.getAllUsuario();
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("juan", result.get(0).getNombre());
    }
    @Test
    void geAllUsuario_Return_null(){

        when(restTemplate.getForObject("http://localhost:9090/api/usuario/lista", UsuarioDto[].class)).thenReturn(null);
        // Act
        List<UsuarioDto> result = usuarioService.getAllUsuario();
        // Assert
        assertNull(result);
    }
    @Test
    void createUser_return_UsuarioDto(){
        UsuarioDto usuarioDto=DataTest.oneUserDto();
        when(restTemplate.postForObject(anyString(), any(UsuarioDto.class), eq(UsuarioDto.class)))
                .thenReturn(usuarioDto);
        UsuarioDto result = usuarioService.createUser(usuarioDto);

        // Verificar los resultados
        assertNotNull(result);
        assertEquals("juan", result.getNombre());

        verify(restTemplate).postForObject(anyString(), any(UsuarioDto.class), eq(UsuarioDto.class));
    }

    @Test
    void getAllUserFeing_return_ListUsuarioDto(){
        when(usuarioClient.getAllUsuarios()).thenReturn(DataTest.listaUsuariosDto());
        List<UsuarioDto> result = usuarioService.getAllUsuarioOpenFeing();
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("juan", result.get(0).getNombre());
    }
    @Test
    void createUserFeing_return_UsuarioDto(){
        UsuarioDto usuarioRequest=UsuarioDto.builder()
                .nombre("manuel")
                .build();
        when(usuarioClient.createUsuarios(any(UsuarioDto.class))).thenReturn(DataTest.oneUserDto());

        UsuarioDto response=usuarioService.createUserOpengFeing(usuarioRequest);
        assertNotNull(response);
    }
}