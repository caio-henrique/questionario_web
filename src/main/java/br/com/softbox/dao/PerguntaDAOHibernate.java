package br.com.softbox.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.softbox.model.Pergunta;
import br.com.softbox.model.Questionario;

public class PerguntaDAOHibernate implements PerguntaDAO {

	private Session session;

	public Session getSession() {
		
		return this.session;
	}

	public void setSession(Session session) {
		
		this.session = session;
	}

	@Override
	public void salvar(Pergunta pergunta) {
		
		this.session.save(pergunta);
	}

	@Override
	public void excluir(Pergunta pergunta) {
		
		this.session.delete(pergunta);
	}

	@Override
	public Pergunta carregar(long identificador) {
		
		Pergunta pergunta = (Pergunta) this.session.get(Pergunta.class, identificador);
		
		if(pergunta != null)
			this.session.evict( pergunta );
		
		return pergunta;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Pergunta> listar(Questionario questionario){
		
		return this.session.createCriteria(Pergunta.class)
				.add( Restrictions.eq("questionario", questionario )).list();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pergunta> listar() {
		
		return this.session.createCriteria(Pergunta.class).list();
	}
}
