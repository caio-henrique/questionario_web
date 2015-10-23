package br.com.softbox.dao;

import java.util.List;

import br.com.softbox.model.Pergunta;
import br.com.softbox.model.Opcao;

public interface OpcaoDAO {

	public void salvar(Opcao opcao);
	
	public void excluir(Opcao opcao);
	
	public Pergunta carregar(long identificador);
	
	public List<Opcao> listar(Pergunta pergunta);
	
	public List<Opcao> listar();
}
