package com.projects.praticandoAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projects.praticandoAPI.controller.dto.TopicoDto;
import com.projects.praticandoAPI.controller.form.TopicoForm;
import com.projects.praticandoAPI.modelo.Curso;
import com.projects.praticandoAPI.modelo.Topico;
import com.projects.praticandoAPI.repository.CursoRepository;
import com.projects.praticandoAPI.repository.TopicoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebMvcTest(TopicosJPAController.class)
public class TopicosJPAControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TopicoRepository topicoRepository;

    @MockBean
    private CursoRepository cursoRepository;

    @InjectMocks
    private TopicosJPAController topicosController;

    @Test
    public void testLista() throws Exception {
        List<Topico> topicos = new ArrayList<>();
        topicos.add(new Topico("Tópico 1", "Descrição do Tópico 1", new Curso("Curso 1", "Descrição do Curso 1")));
        topicos.add(new Topico("Tópico 2", "Descrição do Tópico 2", new Curso("Curso 2", "Descrição do Curso 2")));
        when(topicoRepository.findAll()).thenReturn(topicos);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/topicos").accept(MediaType.APPLICATION_JSON))
                .andReturn();

        String expectedJson = new ObjectMapper().writeValueAsString(TopicoDto.converter(topicos));
        String actualJson = result.getResponse().getContentAsString();
        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void testCadastrar() throws Exception {
        TopicoForm form = new TopicoForm();
        form.setTitulo("Tópico 1");
        form.setMensagem("Descrição do Tópico 1");
        form.setNomeCurso("Curso 1");

        Topico topico = form.converter(cursoRepository);
        when(topicoRepository.save(topico)).thenReturn(topico);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/topicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(form))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        String expectedJson = new ObjectMapper().writeValueAsString(new TopicoDto(topico));
        String actualJson = result.getResponse().getContentAsString();
        assertEquals(expectedJson, actualJson);
    }
}

