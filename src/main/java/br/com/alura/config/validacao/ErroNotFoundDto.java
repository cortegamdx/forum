package br.com.alura.config.validacao;

public class ErroNotFoundDto {
    private String mensagem;

    public ErroNotFoundDto(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
