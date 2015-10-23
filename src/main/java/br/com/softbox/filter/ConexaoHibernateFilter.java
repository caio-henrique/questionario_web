package br.com.softbox.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.SessionFactory;

import br.com.softbox.util.HibernateUtil;

public class ConexaoHibernateFilter implements Filter {

	private SessionFactory sessionFactory;
	
	public void init (FilterConfig config) throws ServletException{
		
		this.sessionFactory = HibernateUtil.getSessionFactory();
	}
	
	public void doFilter( ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException{
		
		try{
			
			this.sessionFactory.getCurrentSession().beginTransaction();
			chain.doFilter(servletRequest, servletResponse);
			this.sessionFactory.getCurrentSession().getTransaction().commit();
			this.sessionFactory.getCurrentSession().close();
		}
		
		catch( Throwable excessao ){
			
			try{
				
				if( this.sessionFactory.getCurrentSession().getTransaction().isActive()){
					
					this.sessionFactory.getCurrentSession().getTransaction().rollback();
				}			
			}
			
			catch( Throwable t ){
				
				t.printStackTrace();
			}
			
			throw new ServletException( excessao );
		}
	}
	
	public void destroy(){
		
	}
}
