package br.com.softbox.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance( strategy = InheritanceType.TABLE_PER_CLASS )
public abstract class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(nullable = false, length = 90)
	private String nome;

	@Column(nullable = false, length = 90, unique = true)
	private String email;

	public long getId() {
		
		return this.id;
	}

	public void setId(long id) {
		
		this.id = id;
	}

	public String getNome() {
		
		return this.nome;
	}

	public void setNome(String nome) {
		
		this.nome = nome;
	}

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
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		
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
		
		Pessoa other = (Pessoa) obj;
		
		if (email == null) {
			
			if (other.email != null)
				return false;
		} 
		
		else if (!email.equals(other.email))
			return false;
		
		if (id != other.id)
			return false;
		
		if (nome == null) {
			
			if (other.nome != null)
				return false;
		} 
		
		else if (!nome.equals(other.nome))
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		
		return "Pessoa [id=" + id + ", nome=" + nome + ", email=" + email + "]";
	}
}
