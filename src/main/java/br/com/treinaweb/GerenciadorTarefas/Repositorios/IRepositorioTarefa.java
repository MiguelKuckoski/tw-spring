package br.com.treinaweb.GerenciadorTarefas.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.treinaweb.GerenciadorTarefas.Models.Tarefa;

public interface IRepositorioTarefa extends JpaRepository<Tarefa, Long>{

	@Query("SELECT t FROM Tarefa t WHERE t.usuario.email = :emailUsuario")
	List<Tarefa> carregarTarefasPorUsuario(@Param("emailUsuario") String email); 
}
