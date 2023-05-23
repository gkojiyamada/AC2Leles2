package com.projects.praticandoAPI.factory;

import com.projects.praticandoAPI.modelo.Curso;
import com.projects.praticandoAPI.modelo.Resposta;
import com.projects.praticandoAPI.modelo.Topico;
import com.projects.praticandoAPI.modelo.Usuario;

import java.util.List;

public class TopicoBuilder {

    private Topico topico;

    public TopicoBuilder() {
        topico = new Topico();
    }
    public TopicoBuilder comTitulo(String titulo) {
        topico.setTitulo(titulo);
        return this;
    }
    public TopicoBuilder comMensagem(String mensagem) {
        topico.setMensagem(mensagem);
        return this;
    }
    public TopicoBuilder comAutor(Usuario autor) {
        topico.setAutor(autor);
        return this;
    }
    public TopicoBuilder comCurso(Curso curso) {
        topico.setCurso(curso);
        return this;
    }
    public TopicoBuilder comRespostas(List<Resposta> respostas) {
        topico.setRespostas(respostas);
        return this;
    }
    public Topico build() {
        return topico;
    }
}
