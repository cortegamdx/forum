package br.com.alura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.alura.modelo.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long>{

	List<Topico> findByCursoNome(String nomeCurso);

	//select pelo atributo do relacionamento
	//List<Topico> findByCurso_Nome(String nomeCurso);

	//Criando o metodo com jpql
	@Query("SELECT t FROM Topico t WHERE t.curso.nome = :nomeCurso")
	List<Topico> carregarPorNomeCurso(@Param("nomeCurso") String nomeCurso);
	
}
