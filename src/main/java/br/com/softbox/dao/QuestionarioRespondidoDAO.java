package br.com.softbox.dao;

import java.util.List;

import br.com.softbox.model.QuestionarioRespondido;

public interface QuestionarioRespondidoDAO {
	
	public void salvar(QuestionarioRespondido questionarioRespondido);
	
	public void excluir(QuestionarioRespondido questionarioRespondido);
	
	public QuestionarioRespondido carregar(long identificador);
	
	public List<QuestionarioRespondido> listar();
	
}
