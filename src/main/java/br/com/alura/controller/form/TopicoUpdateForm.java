package br.com.alura.controller.form;

import br.com.alura.modelo.Topico;
import br.com.alura.repository.TopicoRepository;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class TopicoUpdateForm {
    @NonNull @NotEmpty
    @Length(min = 5)
    private String titulo;

    @NonNull @NotEmpty @Length(min = 10)
    private String mensagem;


    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public Topico atualizar(Long id, TopicoRepository topicoRepository) {
       Topico topico =  topicoRepository.getById(id);
          topico.setMensagem(this.getMensagem());
          topico.setTitulo(this.titulo);

        return topico;
    }
}
