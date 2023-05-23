package com.projects.praticandoAPI.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.projects.praticandoAPI.controller.dto.UsuarioDto;
import com.projects.praticandoAPI.controller.form.UsuarioForm;
import com.projects.praticandoAPI.modelo.Usuario;
import com.projects.praticandoAPI.repository.UsuarioRepository;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioControllerTest {

    @Autowired
    private UsuarioController usuarioController;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @BeforeAll
    public void setup() {
        UsuarioForm form = new UsuarioForm();
        form.setNome("João");
        form.setEmail("joao@teste.com");
        form.setSenha("123456");

        Usuario usuario = form.converter(usuarioRepository);
        usuario.setId(1L);

        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
    }

    @Test
    public void testCadastrar() {
        UsuarioForm form = new UsuarioForm();
        form.setNome("João");
        form.setEmail("joao@teste.com");
        form.setSenha("123456");

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();
        ResponseEntity<UsuarioDto> response = usuarioController.cadastrar(form, uriBuilder);

        assertEquals(201, response.getStatusCodeValue());

        URI location = response.getHeaders().getLocation();
        assertEquals("/usuarios/", location.getPath());

        UsuarioDto usuarioDto = response.getBody();
        assertEquals("João", usuarioDto.getNome());
        assertEquals("joao@teste.com", usuarioDto.getEmail());
    }
}
