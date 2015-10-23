package br.com.softbox.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.softbox.exceptions.EmailDuplicadoException;
import br.com.softbox.model.Administrador;
import br.com.softbox.rn.AdministradorRN;

@ViewScoped
@ManagedBean(name="administradorBean")
public class AdministradorBean {
	
	private Administrador administrador;
	private String confirmacaoSenha;
	private List<Administrador> lista;
	
	public Administrador getAdministrador() {
		
		if(this.administrador == null )
			this.administrador = new Administrador();
		
		return this.administrador;
	}
	
	public void setAdministrador(Administrador administrador) {
		
		this.administrador = administrador;
		
		if( administrador != null ) 
			this.confirmacaoSenha = administrador.getSenha();
		
		else 
			this.confirmacaoSenha = "";
	}
	
	public String getConfirmacaoSenha() {
		
		return this.confirmacaoSenha;
	}
	
	public void setConfirmacaoSenha(String confirmacaoSenha) {
		
		this.confirmacaoSenha = confirmacaoSenha;
	}
	
	public List<Administrador> getLista() {
		
		if(this.lista == null)
			this.buscarLista();
		
		return this.lista;
	}
	
	public void setLista(List<Administrador> lista) {
		
		this.lista = lista;
	}
	
	@PostConstruct
	public void carregarAdministrador() {

		HttpServletRequest request = (HttpServletRequest) FacesContext 
				.getCurrentInstance().getExternalContext().getRequest();
		
		if( request.getParameter("identificador") != null ){

			long identificador = Long.parseLong( request.getParameter("identificador") );
		
			if ( identificador != 0 )
				this.carregarAdministrador( identificador );
		}
	}
	
	public AdministradorRN gerarAdministradorRN(){
		
		return new AdministradorRN();
	}
	
	public void carregarAdministrador( long identificador){
		
		this.setAdministrador(this.gerarAdministradorRN().carregar(identificador));
	}
	
	public String atualizarOuSalvar(){
		
		if( this.administrador.getId() == 0  )
			return this.salvar();
		
		else 
			return this.atualizar();
	}
	
	public String salvar(){
		
		try {
			
			this.gerarAdministradorRN().salvar(this.administrador);
			this.setAdministrador(null);
			
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Administrador cadastrado com sucesso!", ""));
		}
		
		catch(EmailDuplicadoException erro){
			
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notamos a existencia de um cadastro com este e-mail. Tente outro e-mail!", ""));
		}
		
		return null;
	}
	
	public String atualizar(){
		
		try {
	
			this.gerarAdministradorRN().atualizar(this.administrador);
		
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados alterados com sucesso!", ""));
		}
		
		catch(EmailDuplicadoException erro){
			
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notamos a existencia de um cadastro com este e-mail. Tente outro e-mail!", ""));
		}
		
		return null;
	}
	
	public void buscarLista(){

		this.setLista(this.gerarAdministradorRN().listar());
	}
	
	public String excluir(){

		this.gerarAdministradorRN().excluir(this.administrador);
		
		this.lista.remove(this.administrador);
		
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Administrador excluido com sucesso!", ""));
		
		return null;
	}
}
