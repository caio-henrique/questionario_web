package br.com.softbox.dao;

import java.util.List;

import br.com.softbox.model.Usuario;

public interface UsuarioDAO {

	public void salvar(Usuario usuario);
	
	public void excluir(Usuario usuario);
	
	public Usuario carregar(long identificador);
	
	public Usuario carregar(String email);
	
	public List<Usuario> listar();
}
