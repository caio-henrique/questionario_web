package br.com.softbox.dao;

import java.util.List;

import org.hibernate.Session;

import br.com.softbox.model.QuestionarioRespondido;

public class QuestionarioRespondidoDAOHibernate implements QuestionarioRespondidoDAO {

	private Session session;

	public Session getSession() {
		
		return this.session;
	}

	public void setSession(Session session) {
		
		this.session = session;
	}

	@Override
	public void salvar(QuestionarioRespondido questionarioRespondido) {
		
		this.session.save(questionarioRespondido);
	}

	@Override
	public void excluir(QuestionarioRespondido questionarioRespondido) {
		
		this.session.delete(questionarioRespondido);
	}

	@Override
	public QuestionarioRespondido carregar(long identificador) {
		
		return (QuestionarioRespondido) this.session.get(QuestionarioRespondido.class, identificador);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionarioRespondido> listar() {
		
		return this.session.createCriteria(QuestionarioRespondido.class).list();
	}
}
