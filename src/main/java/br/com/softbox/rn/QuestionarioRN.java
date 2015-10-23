package br.com.softbox.rn;

import java.util.List;

import br.com.softbox.dao.QuestionarioDAO;
import br.com.softbox.exceptions.NomeDuplicadoException;
import br.com.softbox.model.Questionario;
import br.com.softbox.model.Usuario;
import br.com.softbox.util.DAOFactory;

public class QuestionarioRN {
	
	private QuestionarioDAO questionarioDAO;

	public QuestionarioRN() {
		
		this.questionarioDAO = DAOFactory.criarQuestionarioDAO();
	}
	
	public void salvar(Questionario questionario) throws NomeDuplicadoException{
		
		if(this.verificarExistenciaNome(questionario))
			this.questionarioDAO.salvar(questionario);
		
		else 
			throw new NomeDuplicadoException( "Erro!", "Nome já cadastrado!" );
	}
	
	public void atualizar(Questionario questionario) throws NomeDuplicadoException{
		
		if(this.verificarExistenciaNome(questionario))
			this.questionarioDAO.atualizar(questionario);
		
		else 
			throw new NomeDuplicadoException( "Erro!", "Nome já cadastrado!" );
	}
	
	public void excluir(Questionario questionario){
		
		this.questionarioDAO.excluir(questionario);
	}
	
	public Questionario carregar(long identificador){
		
		return this.questionarioDAO.carregar(identificador);
	}
	
	public Questionario carregar(String nome){
		
		return this.questionarioDAO.carregar(nome);
	}
	
	public List<Questionario> listar(){
		
		return this.questionarioDAO.listar();
	}
	
	public List<Questionario> listar(Usuario usuario){
		
		return this.questionarioDAO.listar(usuario);
	}
	
	public boolean verificarExistenciaNome(Questionario questionario){

		Questionario questionarioConsulta = this.carregar(questionario.getNome());

		if( questionarioConsulta == null || questionarioConsulta.getId() == questionario.getId() )
			return true;
		
		return false;
	}
}