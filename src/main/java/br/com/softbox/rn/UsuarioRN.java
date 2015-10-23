package br.com.softbox.rn;

import java.util.List;

import br.com.softbox.dao.UsuarioDAO;
import br.com.softbox.model.Usuario;
import br.com.softbox.util.DAOFactory;

public class UsuarioRN {
	
	private UsuarioDAO usuarioDAO;

	public UsuarioRN() {
		
		this.usuarioDAO = DAOFactory.criarUsuarioDAO();
	}
	
	public void salvar(Usuario usuario) {

		this.usuarioDAO.salvar(usuario);
	}
	
	public void excluir(Usuario usuario){
		
		this.usuarioDAO.excluir(usuario);
	}
	
	public Usuario carregar(long identificador){
		
		return this.usuarioDAO.carregar(identificador);
	}
	
	public Usuario carregar(String email){
		
		return this.usuarioDAO.carregar(email);
	}
	
	public List<Usuario> listar(){
		
		return this.usuarioDAO.listar();
	}
}
