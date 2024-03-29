package br.com.alura.controller.form;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

import br.com.alura.modelo.Curso;
import br.com.alura.modelo.Topico;
import br.com.alura.repository.CursoRepository;

public class TopicoForm {

	@NonNull @NotEmpty @Length(min = 5)
	private String titulo;
	@NonNull @NotEmpty @Length(min = 10)
	private String mensagem;
	@NonNull @NotEmpty
	private String nomeCurso;
	
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getNomeCurso() {
		return nomeCurso;
	}
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
	
	public  Topico converter(CursoRepository cursoRepository) {
		Curso curso = cursoRepository.findByNome(nomeCurso);
		return new Topico(this.titulo,this.mensagem,curso);
	}
	
	
	
}
