package br.com.softbox.dao;

import java.util.List;

import br.com.softbox.model.Administrador;

public interface AdministradorDAO {
	
	public void salvar(Administrador administrador);
	
	public void atualizar(Administrador administrador);
	
	public void excluir(Administrador administrador);
	
	public Administrador carregar(long identificador);
	
	public Administrador carregar(String email);
	
	public List<Administrador> listar();
}
