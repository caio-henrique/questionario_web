package br.com.softbox.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.softbox.model.Administrador;

public class AdministradorDAOHibernate implements AdministradorDAO {
	
	private Session session;

	public Session getSession() {
		
		return this.session;
	}

	public void setSession(Session session) {
		
		this.session = session;
	}

	@Override
	public void salvar(Administrador administrador) {
		
		this.session.save(administrador);
	}

	@Override
	public void atualizar(Administrador administrador) {
		
		this.session.update(administrador);
	}

	@Override
	public void excluir(Administrador administrador) {
		
		this.session.delete(administrador);
	}

	@Override
	public Administrador carregar(long identificador) {
		
		return (Administrador) this.session.get(Administrador.class, identificador);
	}
	
	@Override
	public Administrador carregar(String email) {
		
		Administrador administrador = (Administrador) this.session.createCriteria(Administrador.class)
				.add( Restrictions.like("email", email)).uniqueResult();
		
		if(administrador != null)
			this.session.evict( administrador );
		
		return administrador;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Administrador> listar() {
		
		return this.session.createCriteria(Administrador.class).list();
	}
}
