package br.com.alura.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import br.com.alura.controller.dto.TopicoDetalheDto;
import br.com.alura.controller.form.TopicoUpdateForm;
import br.com.alura.modelo.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.controller.dto.TopicoDto;
import br.com.alura.controller.form.TopicoForm;
import br.com.alura.modelo.Topico;
import br.com.alura.repository.CursoRepository;
import br.com.alura.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
@CrossOrigin("*")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

//	@RequestMapping("/topicos")  essa forma e usada com a anotation @Controller
//	@ResponseBody
//	public List<Topico> lista() {
//		Topico topico = new Topico("Duvida", "Duvida com Spring", new Curso("Spring","Programacao"));
//		
//		return Arrays.asList(topico,topico,topico);
//	}

    @GetMapping
    public Page<TopicoDto> lista(@RequestParam(required = false) String nomeCurso, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao) {

        if (nomeCurso == null) {
            Page<Topico> topicos = topicoRepository.findAll(paginacao);
            return TopicoDto.converter(topicos);
        } else {
            Page<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso, paginacao);
            return TopicoDto.converter(topicos);
        }
    }

    @PostMapping          //Indica que o parametro vem pelo corpo da requisicao
    @Transactional
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
        Topico topico = form.converter(cursoRepository);

        topicoRepository.save(topico);

        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new TopicoDto(topico));


    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDetalheDto> detalhar(@PathVariable Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);

        if (topico.isPresent()) {

            return ResponseEntity.ok(new TopicoDetalheDto(topico.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @Transactional//Avisa pro spring que e pra commitar a transacao no final desse metodo
    @PutMapping("/{id}")
    public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid TopicoUpdateForm form) {
        Topico topico = form.atualizar(id, topicoRepository);
        //Como a JPA trabalha com transactions, nao precisamos chamar nenhum metodo para atualizar
        //quando buscamos ele no banco de dadas ele fica sendo escutado e quais alteracao sera commitada automaticamente
        // quando o metodo terminar

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity remover(@PathVariable Long id) {
        topicoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
