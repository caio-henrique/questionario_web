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
public class Questionario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(nullable = false, length = 90, unique = true)
	private String nome;

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

	public static long getSerialversionuid() {
		
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		
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
		
		Questionario other = (Questionario) obj;
		
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
		
		return "Questionario [id=" + id + ", nome=" + nome + "]";
	}
}
