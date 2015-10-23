package br.com.softbox.dao;

import java.util.List;

import br.com.softbox.model.Pergunta;
import br.com.softbox.model.Questionario;

public interface PerguntaDAO {
	
	public void salvar(Pergunta pergunta);
	
	public void excluir(Pergunta pergunta);
	
	public Pergunta carregar(long identificador);
	
	public List<Pergunta> listar(Questionario questionario);
	
	public List<Pergunta> listar();
}
