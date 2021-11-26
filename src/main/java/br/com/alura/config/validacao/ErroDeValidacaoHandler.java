package br.com.alura.config.validacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice //essa anotacao transforma a classe em uma especie de interceptador
public class ErroDeValidacaoHandler {
    @Autowired
    //Ajuda com a traducao das mensagens por locale
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class) //essa anotacao diz para o spring que esse metodo deve ser chamado, quando houver uma exceção dentro de algum controller
    public List<ErroDeFormaularioDto> handle(MethodArgumentNotValidException exception){
        List<ErroDeFormaularioDto> dto = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        fieldErrors.forEach(erro -> {
            String mensagem = messageSource.getMessage(erro, LocaleContextHolder.getLocale());
            dto.add(new ErroDeFormaularioDto(erro.getField(),mensagem));
        });

        return dto;
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmptyResultDataAccessException.class )
    public  ErroNotFoundDto notFound(EmptyResultDataAccessException exception){
        return new ErroNotFoundDto("Topico não encontrado.");
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public  ErroNotFoundDto notFound(EntityNotFoundException exception){
        return new ErroNotFoundDto("Topico não encontrado.");
    }



}
