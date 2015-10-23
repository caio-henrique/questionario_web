package br.com.softbox.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.softbox.model.Pergunta;
import br.com.softbox.model.Opcao;

public class OpcaoDAOHibernate implements OpcaoDAO {

	private Session session;

	public Session getSession() {
		
		return this.session;
	}

	public void setSession(Session session) {
		
		this.session = session;
	}

	@Override
	public void salvar(Opcao opcao) {
		
		this.session.save(opcao);
	}

	@Override
	public void excluir(Opcao opcao) {
		
		this.session.delete(opcao);
	}

	@Override
	public Pergunta carregar(long identificador) {
		
		return (Pergunta) this.session.get(Pergunta.class, identificador);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Opcao> listar(Pergunta pergunta) {
		
		return this.session.createCriteria(Opcao.class)
				.add( Restrictions.eq("pergunta", pergunta )).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Opcao> listar() {
		
		return this.session.createCriteria(Pergunta.class).list();
	}
}