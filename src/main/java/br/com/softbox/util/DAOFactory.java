package br.com.softbox.util;

import br.com.softbox.dao.AdministradorDAO;
import br.com.softbox.dao.AdministradorDAOHibernate;
import br.com.softbox.dao.PerguntaDAO;
import br.com.softbox.dao.PerguntaDAOHibernate;
import br.com.softbox.dao.OpcaoDAO;
import br.com.softbox.dao.OpcaoDAOHibernate;
import br.com.softbox.dao.QuestionarioDAO;
import br.com.softbox.dao.QuestionarioDAOHibernate;
import br.com.softbox.dao.QuestionarioRespondidoDAO;
import br.com.softbox.dao.QuestionarioRespondidoDAOHibernate;
import br.com.softbox.dao.UsuarioDAO;
import br.com.softbox.dao.UsuarioDAOHibernate;

public class DAOFactory {

	public static AdministradorDAO criarAdministradorDAO(){
		
		AdministradorDAOHibernate administradorDAOHibernate = new AdministradorDAOHibernate();
		
		administradorDAOHibernate.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		
		return administradorDAOHibernate;
	}
	
	public static QuestionarioDAO criarQuestionarioDAO(){
		
		QuestionarioDAOHibernate questionarioDAOHibernate = new QuestionarioDAOHibernate();
		
		questionarioDAOHibernate.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		
		return questionarioDAOHibernate;
	}
	
	public static PerguntaDAO criarPerguntaDAO(){
		
		PerguntaDAOHibernate perguntaDAOHibernate = new PerguntaDAOHibernate();
		
		perguntaDAOHibernate.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		
		return perguntaDAOHibernate;
	}
	
	public static OpcaoDAO criarQuestaoDAO(){
		
		OpcaoDAOHibernate questaoDAOHibernate = new OpcaoDAOHibernate();
		
		questaoDAOHibernate.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		
		return questaoDAOHibernate;
	}
	
	public static UsuarioDAO criarUsuarioDAO(){
		
		UsuarioDAOHibernate usuarioDAOHibernate = new UsuarioDAOHibernate();
		
		usuarioDAOHibernate.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		
		return usuarioDAOHibernate;
	}
	
	public static QuestionarioRespondidoDAO criarQuestionarioRespondidoDAO(){
		
		QuestionarioRespondidoDAOHibernate questionarioRespondidoDAOHibernate = new QuestionarioRespondidoDAOHibernate();
		
		questionarioRespondidoDAOHibernate.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		
		return questionarioRespondidoDAOHibernate;
	}
}
