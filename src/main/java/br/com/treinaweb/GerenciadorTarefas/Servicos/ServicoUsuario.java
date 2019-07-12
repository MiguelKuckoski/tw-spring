package br.com.treinaweb.GerenciadorTarefas.Servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.treinaweb.GerenciadorTarefas.Models.Usuario;
import br.com.treinaweb.GerenciadorTarefas.Repositorios.IRepositorioUsuario;

@Service
public class ServicoUsuario {

	@Autowired
	private IRepositorioUsuario repositorioUsuario;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	public Usuario encontrarPorEmail(String email) {
		return repositorioUsuario.findByEmail(email);
	}
	
	public void salvar(Usuario usuario) {
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		repositorioUsuario.save(usuario);
	}
	
	
	
	
}
