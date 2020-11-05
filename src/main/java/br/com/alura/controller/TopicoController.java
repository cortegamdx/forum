package br.com.alura.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.controller.dto.DetalhesTopicoDto;
import br.com.alura.controller.dto.TopicoDto;
import br.com.alura.controller.form.AtualizacaoTopicoForm;
import br.com.alura.controller.form.TopicoForm;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.repository.CursoRepository;
import br.com.alura.repository.TopicoRepository;

//@Controller
@RestController
@RequestMapping("/topicos")
public class TopicoController {
	
	@Autowired
	private TopicoRepository TopicoRep;
	
	@Autowired
	private CursoRepository cursoRep;
	
	@GetMapping
   //@ResponseBody //Indicar ao Spring que o retorno do método deve ser devolvido como resposta
	public  List<TopicoDto> lista(@RequestParam(required = false) String nomeCurso, @RequestParam int pagina, @RequestParam int qtd){
		
//		Topico topico = new Topico("Duvida","Duvida com spring",
//				new Curso("Springboot","Programação"));
//		return TopicoDto.converter(Arrays.asList(topico,topico,topico));
		
		Pageable paginacao = PageRequest.of(pagina,qtd);
		
		
		
		if(nomeCurso == null) {
			
		Page<Topico>	topicos = TopicoRep.findAll(paginacao);
			
			return TopicoDto.converter(topicos);
		}else {

			Page<Topico> topicos = TopicoRep.findByCursoNome(nomeCurso,paginacao);
			return TopicoDto.converter(topicos);
		}
	}
	

	@PostMapping
	@Transactional
	public ResponseEntity<TopicoDto> cadastar(@RequestBody @Valid TopicoForm topicoForm,UriComponentsBuilder uriBuilder) {
		Topico topico = topicoForm.converter(cursoRep);
		TopicoRep.save(topico);
		
		//devolve o endereço do topico criado
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDto(topico));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalhesTopicoDto> detalhar(@PathVariable("id") Long id) {
		
		Optional<Topico> topico = TopicoRep.findById(id);
		
		if(topico.isPresent()) {
			
			return ResponseEntity.ok(new DetalhesTopicoDto(topico.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicoDto> atualizar(@PathVariable("id") Long id, @RequestBody @Valid AtualizacaoTopicoForm form){

		Optional<Topico> topicoVerify = TopicoRep.findById(id);
		
		if(topicoVerify.isPresent()) {
			
			Topico topico = form.atualizar(id,TopicoRep);
			return ResponseEntity.ok( new TopicoDto(topico));
		}
		return ResponseEntity.badRequest().build();
	}
	
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable("id") Long id){
		
		Optional<Topico> topicoVerify = TopicoRep.findById(id);

		if(topicoVerify.isPresent()) {
			
			TopicoRep.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.badRequest().build();
	}
	

}
