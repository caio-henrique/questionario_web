package br.com.softbox.rn;

import java.util.List;

import br.com.softbox.dao.QuestionarioRespondidoDAO;
import br.com.softbox.model.QuestionarioRespondido;
import br.com.softbox.util.DAOFactory;

public class QuestionarioRespondidoRN {

	private QuestionarioRespondidoDAO questionarioRespondidoDAO;
	
	public QuestionarioRespondidoRN() {
		
		this.questionarioRespondidoDAO = DAOFactory.criarQuestionarioRespondidoDAO();
	}
	
	public void salvar(QuestionarioRespondido questionarioRespondido){
		
		this.questionarioRespondidoDAO.salvar(questionarioRespondido);
	}
	
	public void excluir(QuestionarioRespondido questionarioRespondido){
		
		this.questionarioRespondidoDAO.excluir(questionarioRespondido);
	}
	
	public QuestionarioRespondido carregar(long identificador){
		
		return this.questionarioRespondidoDAO.carregar(identificador);
	}
	
	public List<QuestionarioRespondido> listar(){
		
		return this.questionarioRespondidoDAO.listar();
	}
}
