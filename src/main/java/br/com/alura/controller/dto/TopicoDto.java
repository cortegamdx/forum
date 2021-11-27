package br.com.alura.controller.dto;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.modelo.Topico;
import org.springframework.data.domain.Page;

public class TopicoDto {

	private Long id;
	private String titulo;
	private String mensagem;
	private String dataCriacao;


	public TopicoDto(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

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



	public static Page<TopicoDto> converter(Page<Topico> topicos) {
		return topicos.map(TopicoDto::new);
	}
	



}
