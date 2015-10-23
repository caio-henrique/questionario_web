package br.com.softbox.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;

import br.com.softbox.enums.EnumTipoPergunta;
import br.com.softbox.model.Pergunta;
import br.com.softbox.model.Opcao;
import br.com.softbox.model.Questionario;
import br.com.softbox.rn.OpcaoRN;
import br.com.softbox.rn.PerguntaRN;
import br.com.softbox.rn.QuestionarioRN;

@ViewScoped
@ManagedBean(name="perguntaBean")
public class PerguntaBean {
	
	private Questionario questionario;
	private Pergunta pergunta;
	private Opcao opcao;
	private List<Pergunta> perguntas;
	private List<Opcao> opcoes;
	private List<Opcao> listaOpcoes;
	private ArrayList<SelectItem> questionarios;
	private boolean renderizarListOpcoes = false;

	public Questionario getQuestionario() {

		return this.questionario;
	}

	public void setQuestionario(Questionario questionario) {
		
		this.questionario = questionario;
	}
	
	public Pergunta getPergunta() {
		
		if(this.pergunta == null){
			this.pergunta = new Pergunta();
			this.pergunta.setTipo(EnumTipoPergunta.discursiva);
		}
		
		return this.pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		
		this.pergunta = pergunta;
	}

	public Opcao getOpcao() {
		
		if(this.opcao == null)
			this.opcao = new Opcao();
		
		return this.opcao;
	}

	public void setOpcao(Opcao opcao) {
		
		this.opcao = opcao;
	}

	public List<Pergunta> getPerguntas() {
		
		return this.perguntas;
	}

	public void setPerguntas(List<Pergunta> perguntas) {
		
		this.perguntas = perguntas;
	}
	
	public List<Opcao> getOpcoes() {
		
		if(this.opcoes == null)
			this.opcoes = new ArrayList<Opcao>();
		
		return this.opcoes;
	}

	public void setOpcoes(List<Opcao> opcoes) {
		
		this.opcoes = opcoes;
	}

	public List<Opcao> getListaOpcoes() {
		
		if(this.listaOpcoes == null)
			this.listaOpcoes = new ArrayList<Opcao>();
		
		return this.listaOpcoes;
	}

	public void setListaOpcoes(List<Opcao> listaOpcoes) {
		
		this.listaOpcoes = listaOpcoes;
	}

	public ArrayList<SelectItem> getQuestionarios() {
		
		if(this.questionarios == null )
			this.carregarQuestionarios();
		
		return this.questionarios;
	}

	public void setQuestionarios(ArrayList<SelectItem> questionarios) {
		
		this.questionarios = questionarios;
	}
	
	public boolean getRenderizarListOpcoes() {
		
		return this.renderizarListOpcoes;
	}

	public void setRenderizarListOpcoes(boolean renderizarListOpcoes) {
		
		this.renderizarListOpcoes = renderizarListOpcoes;
	}

	public QuestionarioRN gerarQuestionarioRN(){
		
		return new QuestionarioRN();
	}
	
	public PerguntaRN gerarPerguntaRN(){
		
		return new PerguntaRN();
	}
	
	public OpcaoRN gerarOpcaoRN(){
		
		return new OpcaoRN();
	}
	
	public void carregarQuestionarios(){
		
		List<Questionario> questionarios = this.gerarQuestionarioRN().listar();
		
		this.questionarios = new ArrayList<SelectItem>(questionarios.size());
		
		for (Questionario questionario: questionarios) {
			
			SelectItem selectItem = new SelectItem(questionario, questionario.getNome());
			selectItem.setEscape(false);
			this.questionarios.add(selectItem);
		}
	}
	
	public void carregarPerguntas(){
		
		this.perguntas = this.gerarPerguntaRN().listar(this.questionario);
	}
	
	public String salvarPergunta(){
		
		this.pergunta.setQuestionario(this.questionario);
		this.gerarPerguntaRN().salvar(this.pergunta);
		
		if(pergunta.getTipo() == EnumTipoPergunta.objetiva)
			this.salvarOpcoes();
		
		this.setPergunta(null);
		this.setOpcao(null);
		this.setListaOpcoes(null);
		this.setRenderizarListOpcoes(false);
		this.carregarPerguntas();
		
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pergunta cadastrado com sucesso!", ""));

		return null;
	}
	
	public String salvarOpcoes(){
		
		for(Opcao opcao: this.listaOpcoes){
			
			opcao.setPergunta(this.pergunta);
			this.gerarOpcaoRN().salvar(opcao);
		}
		
		return null;
	}
	
	public String adicionarOpcao(){
		
		this.listaOpcoes.add(this.opcao);
		this.setOpcao(null);
		
		return null;
	}
	
	public String removerOpcaoLista(){

		this.listaOpcoes.remove(this.opcao);
		this.setOpcao(null);
		
		return null;
	}
	
	public String removerOpcao(){
		
		this.opcoes.remove(this.opcao);
		this.gerarOpcaoRN().excluir(this.opcao);
		this.setOpcao(null);
		
		return null;
	}
	
	public String removerPergunta(){
		
		this.perguntas.remove(this.pergunta);
		
		//if(this.pergunta.getTipo() == EnumTipoPergunta.objetiva)
			//this.gerarOpcaoRN().excluir(questao);
		
		this.gerarPerguntaRN().excluir(this.pergunta);
		this.setPergunta(null);
			
		return null;
	}
	
	public void atualizarRenderezacaoListOpcoes(){
		
		if(this.pergunta.getTipo() == EnumTipoPergunta.objetiva)
			this.renderizarListOpcoes = true;
		
		else 
			this.renderizarListOpcoes = false;
	}
	
	public boolean atualizarRenderezacaoListOpcoes(Pergunta pergunta){
		
		if(pergunta.getTipo() == EnumTipoPergunta.objetiva){
			this.carregarOpcoes(pergunta);
			return true;
		}
		
		else 
			return false;
	}
	
	public void carregarOpcoes(Pergunta pergunta){
		
		this.opcoes = this.gerarOpcaoRN().listar(pergunta);
	}
	
	public void validarDadosPergunta( ActionEvent event ) {

	    boolean erro = false;
	     
	    if( this.pergunta.getPergunta() != "" ){
	    	
	    	erro = true;

	    	this.salvarPergunta();
	    }
	    
	    else	    	 
	    	erro = false;
	             
	    RequestContext context = RequestContext.getCurrentInstance();
        context.addCallbackParam("erro", erro);
	}  
}
