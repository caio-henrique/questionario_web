package br.com.softbox.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

import br.com.softbox.model.Questionario;
import br.com.softbox.model.QuestionarioRespondido;
import br.com.softbox.model.Usuario;

public class QuestionarioDAOHibernate implements QuestionarioDAO{

	private Session session;

	public Session getSession() {
		
		return this.session;
	}

	public void setSession(Session session) {
		
		this.session = session;
	}

	@Override
	public void salvar(Questionario questionario) {
		
		this.session.save(questionario);
	}

	@Override
	public void atualizar(Questionario questionario) {
		
		this.session.update(questionario);
	}

	@Override
	public void excluir(Questionario questionario) {
		
		this.session.delete(questionario);
	}

	@Override
	public Questionario carregar(long identificador) {
		
		return (Questionario) this.session.get(Questionario.class, identificador);
	}

	@Override
	public Questionario carregar(String nome) {
		
		Questionario questionario = (Questionario) this.session.createCriteria(Questionario.class)
				.add( Restrictions.like("nome", nome)).uniqueResult();
		
		if(questionario != null)
			this.session.evict( questionario );
		
		return questionario;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Questionario> listar(Usuario usuario){

		Criteria criteria = session.createCriteria(Questionario.class, "q");
		
		criteria.add(Subqueries.notExists(
		      DetachedCriteria.forClass(QuestionarioRespondido.class, "r")		    
	              .setProjection(Projections.id())
	              .add(Restrictions.eq("usuario", usuario))	           
	              .add(Restrictions.eqProperty("q.id", "r.questionario.id"))              
		        ));
		
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Questionario> listar() {
		
		return this.session.createCriteria(Questionario.class).list();
	}
}
