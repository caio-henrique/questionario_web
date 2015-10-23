package br.com.softbox.rn;

import java.util.List;

import br.com.softbox.dao.AdministradorDAO;
import br.com.softbox.enums.EnumTipoAdministrador;
import br.com.softbox.exceptions.EmailDuplicadoException;
import br.com.softbox.model.Administrador;
import br.com.softbox.util.DAOFactory;

public class AdministradorRN {
	
	private AdministradorDAO administradorDAO;

	public AdministradorRN() {
		
		this.administradorDAO = DAOFactory.criarAdministradorDAO();
	}
	
	public void salvar( Administrador administrador ) throws EmailDuplicadoException{
		
		if(this.verificarExistenciaEmail(administrador)){
			
			administrador.setTipo(EnumTipoAdministrador.ROLE_ADMINISTRADOR);
			this.administradorDAO.salvar(administrador);
		}
		
		else 
			throw new EmailDuplicadoException( "Erro!", "Email já cadastrado!" );
	}
	
	public void atualizar( Administrador administrador ) throws EmailDuplicadoException{
		
		if(this.verificarExistenciaEmail(administrador))
			this.administradorDAO.atualizar(administrador);
		
		else 
			throw new EmailDuplicadoException( "Erro!", "Email já cadastrado!" );
	}
	
	public void excluir( Administrador administrador ){
		
		this.administradorDAO.excluir(administrador);
	}
	
	public Administrador carregar( long identificador ){
		
		return this.administradorDAO.carregar(identificador);
	}
	
	public Administrador carregar( String email ){
		
		return this.administradorDAO.carregar(email);
	}
	
	public List<Administrador> listar(){
		
		return this.administradorDAO.listar();
	}
	
	public boolean verificarExistenciaEmail(Administrador administrador){

		Administrador administradorConsulta = this.carregar(administrador.getEmail());

		if( administradorConsulta == null || administradorConsulta.getId() == administrador.getId() )
			return true;
		
		return false;
	}
}
