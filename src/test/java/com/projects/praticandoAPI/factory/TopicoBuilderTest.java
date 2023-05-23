package com.projects.praticandoAPI.factory;

import com.projects.praticandoAPI.modelo.Curso;
import com.projects.praticandoAPI.modelo.Resposta;
import com.projects.praticandoAPI.modelo.Topico;
import com.projects.praticandoAPI.modelo.Usuario;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TopicoBuilderTest {

    @Test
    public void testTopicoBuilder() {

        Usuario autor = new Usuario();
        Curso curso = new Curso();
        Resposta resposta = new Resposta();
        List<Resposta> respostas = new ArrayList<>();
        respostas.add(resposta);

        Topico topico = new TopicoBuilder()
                .comTitulo("titulo")
                .comMensagem("mensagem")
                .comAutor(autor)
                .comCurso(curso)
                .comRespostas(respostas)
                .build();

        assertEquals("titulo", topico.getTitulo());
        assertEquals("mensagem", topico.getMensagem());
        assertEquals(autor, topico.getAutor());
        assertEquals(curso, topico.getCurso());
        assertEquals(respostas, topico.getRespostas());
    }
}
