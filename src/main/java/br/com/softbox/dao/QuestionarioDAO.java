package br.com.softbox.dao;

import java.util.List;

import br.com.softbox.model.Questionario;
import br.com.softbox.model.Usuario;

public interface QuestionarioDAO {
	
	public void salvar(Questionario questionario);
	
	public void atualizar(Questionario questionario);
	
	public void excluir(Questionario questionario);
	
	public Questionario carregar(long identificador);
	
	public Questionario carregar(String nome);
	
	public List<Questionario> listar(Usuario usuario);
	
	public List<Questionario> listar();
}
