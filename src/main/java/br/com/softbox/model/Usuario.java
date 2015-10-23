package br.com.softbox.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn( name = "id" )
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(nullable = false, length = 90, unique = true)
	private String email;

	public String getEmail() {
		
		return this.email;
	}

	public void setEmail(String email) {
		
		this.email = email;
	}

	public static long getSerialversionuid() {
		
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Usuario other = (Usuario) obj;
		
		if (email == null) {
			
			if (other.email != null)
				return false;
		} 
		
		else if (!email.equals(other.email))
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		
		return "Usuario [email=" + email + "]";
	}
}
