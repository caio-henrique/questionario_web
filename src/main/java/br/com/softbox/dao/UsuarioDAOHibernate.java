package br.com.softbox.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.softbox.model.Usuario;

public class UsuarioDAOHibernate implements UsuarioDAO {

	private Session session;

	public Session getSession() {
		
		return this.session;
	}

	public void setSession(Session session) {
		
		this.session = session;
	}

	@Override
	public void salvar(Usuario usuario) {
		
		this.session.save(usuario);
	}

	@Override
	public void excluir(Usuario usuario) {
		
		this.session.delete(usuario);
	}

	@Override
	public Usuario carregar(long identificador) {
		
		return (Usuario) this.session.get(Usuario.class, identificador);
	}
	
	@Override
	public Usuario carregar(String email){

		Usuario usuario = (Usuario) this.session.createCriteria(Usuario.class)
				.add( Restrictions.like("email", email)).uniqueResult();
		
		if(usuario != null)
			this.session.evict( usuario );
		
		return usuario;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> listar() {
		
		return this.session.createCriteria(Usuario.class).list();
	}
}
