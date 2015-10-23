package br.com.softbox.rn;

import java.util.List;

import br.com.softbox.dao.OpcaoDAO;
import br.com.softbox.model.Pergunta;
import br.com.softbox.model.Opcao;
import br.com.softbox.util.DAOFactory;

public class OpcaoRN {

	private OpcaoDAO questaoDAO;
	
	public OpcaoRN() {
		
		this.questaoDAO = DAOFactory.criarQuestaoDAO();
	}
	
	public void salvar(Opcao questao){
		
		this.questaoDAO.salvar(questao);
	}
	
	public void excluir(Opcao questao){
		
		this.questaoDAO.excluir(questao);
	}
	
	public Pergunta carregar(long identificador){
		
		return this.questaoDAO.carregar(identificador);
	}
	
	public List<Opcao> listar(Pergunta pergunta){
		
		return this.questaoDAO.listar(pergunta);
	}
	
	public List<Opcao> listar(){
		
		return this.questaoDAO.listar();
	}
}