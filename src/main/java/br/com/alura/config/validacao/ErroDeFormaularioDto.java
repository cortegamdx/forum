package br.com.alura.config.validacao;

public class ErroDeFormaularioDto {
    private String campo;
    private String erro;


    public ErroDeFormaularioDto(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }
}
