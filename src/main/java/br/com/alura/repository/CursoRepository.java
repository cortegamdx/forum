package br.com.alura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.modelo.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{

	Curso findByNome(String nome);
}
