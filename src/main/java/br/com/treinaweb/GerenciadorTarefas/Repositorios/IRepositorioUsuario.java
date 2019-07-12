package br.com.treinaweb.GerenciadorTarefas.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinaweb.GerenciadorTarefas.Models.Usuario;

public interface IRepositorioUsuario extends JpaRepository<Usuario, Long>{

	Usuario findByEmail(String email);
}
