package br.com.softbox.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.softbox.exceptions.NomeDuplicadoException;
import br.com.softbox.model.Questionario;
import br.com.softbox.rn.QuestionarioRN;

@ViewScoped
@ManagedBean(name="questionarioBean")
public class QuestionarioBean {

	private Questionario questionario;
	private List<Questionario> lista;
	
	public Questionario getQuestionario() {
		
		if(this.questionario == null)
			this.questionario = new Questionario();
		
		return this.questionario;
	}
	
	public void setQuestionario(Questionario questionario) {
		
		this.questionario = questionario;
	}
	
	public List<Questionario> getLista() {
		
		return this.lista;
	}
	
	public void setLista(List<Questionario> lista) {
		
		this.lista = lista;
	}
	
	public QuestionarioRN gerarQuestionarioRN(){
		
		return new QuestionarioRN();
	}
	
	public String atualizarOuSalvar(){
		
		if( this.questionario.getId() == 0  )
			return this.salvar();
		
		else 
			return this.atualizar();
	}
	
	public String salvar(){
		
		try {
			
			this.gerarQuestionarioRN().salvar(this.questionario);
			this.setQuestionario(null);
			
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Questionário cadastrado com sucesso!", ""));
		}
		
		catch(NomeDuplicadoException erro){
			
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notamos a existencia de um questionário com este nome. Tente outro nome!", ""));
		}
		
		return null;
	}
	
	public String atualizar(){
		
		try {
			
			this.gerarQuestionarioRN().atualizar(this.questionario);
		
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados alterados com sucesso!", ""));
		}
		
		catch(NomeDuplicadoException erro){
			
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notamos a existencia de um questionário com este nome. Tente outro nome!", ""));
		}
		
		return null;
	}
}
