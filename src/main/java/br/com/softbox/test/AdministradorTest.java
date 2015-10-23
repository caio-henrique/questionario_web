package br.com.softbox.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import br.com.softbox.model.Administrador;
import br.com.softbox.rn.AdministradorRN;
import br.com.softbox.util.HibernateUtil;

public class AdministradorTest {

	@Test
	public void salvar(){
		
		/*Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
		Administrador administrador = this.criarAdministrador();
		
		AdministradorRN administradorRN = new AdministradorRN();
		administradorRN.salvar(administrador);
		
		transaction.commit();*/
		
	}
	
	public Administrador criarAdministrador(){
		
		Administrador administrador = new Administrador();
		administrador.setNome("Caio Henrique");
		administrador.setEmail("caiohenrique_cs@hotmail.com");
		administrador.setSenha("123456");
		
		return administrador;
	}
}
