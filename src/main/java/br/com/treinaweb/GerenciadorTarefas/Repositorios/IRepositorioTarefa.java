package br.com.treinaweb.GerenciadorTarefas.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinaweb.GerenciadorTarefas.Models.Tarefa;

public interface IRepositorioTarefa extends JpaRepository<Tarefa, Long>{

}
