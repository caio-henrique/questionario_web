package br.com.softbox.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import br.com.softbox.enums.EnumTipoPergunta;
import br.com.softbox.model.Opcao;
import br.com.softbox.model.Pergunta;
import br.com.softbox.model.Questionario;
import br.com.softbox.model.QuestionarioRespondido;
import br.com.softbox.model.Usuario;
import br.com.softbox.rn.OpcaoRN;
import br.com.softbox.rn.PerguntaRN;
import br.com.softbox.rn.QuestionarioRN;
import br.com.softbox.rn.QuestionarioRespondidoRN;
import br.com.softbox.rn.UsuarioRN;

@ViewScoped
@ManagedBean(name="renderizadorBean")
public class RenderizadorBean {
	
	
	private boolean renderizarTela1 = true;
	private boolean renderizarTela2 = false;
	private boolean renderizarTela3 = false;
	private boolean renderizarTela4 = false;
	
	private Usuario usuario;
	private HashMap<Long, String> repostas;
	private Questionario questionario;
	private List<Questionario> questionarios;
	private List<Pergunta> perguntas;
	private List<String> resultadosRespostas;
	private HashMap<Long, ArrayList<SelectItem>> opcoes;

	public boolean isRenderizarTela1() {
		
		return this.renderizarTela1;
	}

	public void setRenderizarTela1(boolean renderizarTela1) {
		
		this.renderizarTela1 = renderizarTela1;
	}

	public boolean isRenderizarTela2() {
		
		return this.renderizarTela2;
	}

	public void setRenderizarTela2(boolean renderizarTela2) {
		
		this.renderizarTela2 = renderizarTela2;
	}

	public boolean isRenderizarTela3() {
		
		return this.renderizarTela3;
	}

	public void setRenderizarTela3(boolean renderizarTela3) {
		
		this.renderizarTela3 = renderizarTela3;
	}
	
	public boolean isRenderizarTela4() {
		
		return this.renderizarTela4;
	}

	public void setRenderizarTela4(boolean renderizarTela4) {
		
		this.renderizarTela4 = renderizarTela4;
	}

	public Usuario getUsuario() {
		
		if(this.usuario == null)
			this.usuario = new Usuario();
		
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		
		this.usuario = usuario;
	}

	public HashMap<Long, String> getRepostas() {
		
		if(this.repostas == null)
			this.repostas = new HashMap<Long, String>();
		
		return this.repostas;
	}

	public void setRepostas(HashMap<Long, String> repostas) {
		
		this.repostas = repostas;
	}

	public Questionario getQuestionario() {
		
		if(this.questionario == null)
			this.questionario = new Questionario();
		
		return this.questionario;
	}

	public void setQuestionario(Questionario questionario) {
		
		this.questionario = questionario;
	}

	public List<Questionario> getQuestionarios() {
		
		if(this.questionarios == null)
			this.carregarQuestionarios();
		
		return this.questionarios;
	}

	public void setQuestionarios(List<Questionario> questionarios) {
		
		this.questionarios = questionarios;
	}

	public List<Pergunta> getPerguntas() {
		
		if(this.perguntas == null)
			this.carregarPerguntas();
		
		return this.perguntas;
	}

	public void setPerguntas(List<Pergunta> perguntas) {
		
		this.perguntas = perguntas;
	}
	
	
	
	
	
	
	

	public List<String> getResultadosRespostas() {
		
		if(this.resultadosRespostas == null)
			this.resultadosRespostas = new ArrayList<String>();
		
		return this.resultadosRespostas;
	}

	public void setResultadosRespostas(List<String> resultadosRespostas) {
		
		this.resultadosRespostas = resultadosRespostas;
	}

	
	
	
	
	public HashMap<Long, ArrayList<SelectItem>> getOpcoes() {
		
		if(this.opcoes == null)
			this.opcoes = new HashMap<Long, ArrayList<SelectItem>>();
		
		return this.opcoes;
	}

	public void setOpcoes(HashMap<Long, ArrayList<SelectItem>> opcoes) {
		
		this.opcoes = opcoes;
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
	
	public UsuarioRN gerarUsuarioRN(){
		
		return new UsuarioRN();
	}
	
	public QuestionarioRespondidoRN gerarQuestionarioRespondidoRN(){
		
		return new QuestionarioRespondidoRN();
	}
	
	public void carregarQuestionarios(){
		
		this.questionarios = this.gerarQuestionarioRN().listar(this.usuario);
	}
	
	public void carregarPerguntas(){
		
		this.setPerguntas(this.gerarPerguntaRN().listar(this.questionario));
		
		this.getRepostas();
		this.getOpcoes();
		
		for(Pergunta pergunta: this.perguntas){
			
			this.repostas.put(pergunta.getId(), "");
			
			if(pergunta.getTipo() == EnumTipoPergunta.objetiva)
				this.opcoes.put(pergunta.getId(), this.carregarOPcoes(pergunta));
		}
	}
	
	public ArrayList<SelectItem> carregarOPcoes(Pergunta pergunta){
		
		List<Opcao> opcoes = this.gerarOpcaoRN().listar(pergunta);
		
		ArrayList<SelectItem> listOpcoes = new ArrayList<SelectItem>(opcoes.size());
		
		for (Opcao opcao: opcoes) {
			
			SelectItem selectItem = new SelectItem(opcao, opcao.getDescricao());
			selectItem.setEscape(false);
			listOpcoes.add(selectItem);
		}
		
		return listOpcoes;
	}
	
	public boolean verificarTipoPergunta(Pergunta pergunta){
		
		if(pergunta.getTipo() == EnumTipoPergunta.discursiva)
			return true;

		else 
			return false;
	}
	
	public boolean verificaTipoPerguntaOpcoes(Pergunta pergunta){
		
		if(pergunta.getTipo() == EnumTipoPergunta.objetiva)			
			return true;

		else 
			return false;
	}
	
	public String validarResultados(){
		
		this.gerarResultados();
		
		this.setRenderizarTela1(false);
		this.setRenderizarTela2(false);
		this.setRenderizarTela3(false);
		this.setRenderizarTela4(true);
		
		return null;
	}
	
	public void gerarResultados(){
		
		this.getResultadosRespostas();
		
		for(Pergunta pergunta: this.perguntas){
			
			this.resultadosRespostas.add(this.repostas.get(pergunta.getId()));
		}
	}
	
	public String validarUsuario(){

		Usuario usuario = this.gerarUsuarioRN().carregar(this.usuario.getEmail());
		
		if(usuario == null)
			this.gerarUsuarioRN().salvar(this.usuario);
		
		else
			this.setUsuario(usuario);

		this.setRenderizarTela1(false);
		this.setRenderizarTela2(true);
		this.setRenderizarTela3(false);
		this.setRenderizarTela4(false);
		
		return null;
	}
	
	public String validarQuestionario(){
		
		QuestionarioRespondido questionarioRespondido = new QuestionarioRespondido();
		questionarioRespondido.setQuestionario(this.questionario);
		questionarioRespondido.setUsuario(this.usuario);
		
		this.gerarQuestionarioRespondidoRN().salvar(questionarioRespondido);
		
		this.setRenderizarTela1(false);
		this.setRenderizarTela2(false);
		this.setRenderizarTela3(true);
		this.setRenderizarTela4(false);
		
		return null;
	}
	
	public String buscarListaPerguntas(){
		
		this.setQuestionarios(null);
		this.setPerguntas(null);
		this.setResultadosRespostas(null);
		
		this.setRenderizarTela1(false);
		this.setRenderizarTela2(true);
		this.setRenderizarTela3(false);
		this.setRenderizarTela4(false);
		
		return null;
	}
}
