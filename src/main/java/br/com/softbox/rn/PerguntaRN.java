package br.com.softbox.rn;

import java.util.List;

import br.com.softbox.dao.PerguntaDAO;
import br.com.softbox.model.Pergunta;
import br.com.softbox.model.Questionario;
import br.com.softbox.util.DAOFactory;

public class PerguntaRN {

	private PerguntaDAO perguntaDAO;
	
	public PerguntaRN() {
		
		this.perguntaDAO = DAOFactory.criarPerguntaDAO();
	}
	
	public void salvar(Pergunta pergunta){
		
		this.perguntaDAO.salvar(pergunta);
	}
	
	public void excluir(Pergunta pergunta){
		
		this.perguntaDAO.excluir(pergunta);
	}
	
	public Pergunta carregar(long identificador){
		
		return this.perguntaDAO.carregar(identificador);
	}
	
	public List<Pergunta> listar(Questionario questionario){
		
		return this.perguntaDAO.listar(questionario);
	}
	
	public List<Pergunta> listar(){
		
		return this.perguntaDAO.listar();
	}
}