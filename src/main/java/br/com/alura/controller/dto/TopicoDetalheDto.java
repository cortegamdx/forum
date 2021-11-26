package br.com.alura.controller.dto;

import br.com.alura.modelo.Resposta;
import br.com.alura.modelo.StatusTopico;
import br.com.alura.modelo.Topico;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TopicoDetalheDto {

    private Long id;
    private String titulo;
    private String mensagem;
    private String dataCriacao;
    private String nomeAutor;
    private StatusTopico status;
    private List<RespostaDto> respostas;
    private List<LocalDateTime> datasDasRespostas;

    public TopicoDetalheDto(Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.nomeAutor = topico.getAutor().getNome();
        this.status = topico.getStatus();
        this.respostas = new ArrayList<>();
        this.respostas.addAll(topico.getRespostas().stream().map(RespostaDto::new).collect(Collectors.toList()));
        this.datasDasRespostas = new ArrayList<>();
        this.datasDasRespostas.addAll(topico.getRespostas().stream().map(Resposta::getDataCriacao).collect(Collectors.toList()));
    }

    public List<LocalDateTime> getDatasDasRespostas() {
        return datasDasRespostas;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public StatusTopico getStatus() {
        return status;
    }

    public List<RespostaDto> getRespostas() {
        return respostas;
    }
}
