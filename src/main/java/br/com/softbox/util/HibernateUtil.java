package br.com.softbox.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	public static SessionFactory buildSessionFactory(){
		
		try{
			
			 Configuration configuration = new Configuration();
	         configuration.configure();
	            
	         ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
            		applySettings(configuration.getProperties()).build();
            
	         SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            
	         return sessionFactory;

		}
		
		catch( Throwable excecao ){
			
			System.out.println( "Criação inicial do objeto SessionFactory falhou. Erro: " + excecao );
			
			throw new ExceptionInInitializerError(excecao);
		}
		
	}
	
	public static SessionFactory getSessionFactory(){
		
		return sessionFactory;
	}
}
