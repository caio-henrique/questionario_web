package br.com.softbox.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn( name = "id" )
public class Opcao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn( name = "id_pergunta")
	private Pergunta pergunta;
	
	@Column(nullable = false, length = 255)
	private String descricao;

	public Opcao() {
		
		this.pergunta = new Pergunta();
	}

	public long getId() {
		
		return this.id;
	}

	public void setId(long id) {
		
		this.id = id;
	}

	public Pergunta getPergunta() {
		
		return this.pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		
		this.pergunta = pergunta;
	}

	public String getDescricao() {
		
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		
		this.descricao = descricao;
	}

	public static long getSerialversionuid() {
		
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((pergunta == null) ? 0 : pergunta.hashCode());
		
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
		
		Opcao other = (Opcao) obj;
		
		if (descricao == null) {
			
			if (other.descricao != null)
				return false;
		} 
		
		else if (!descricao.equals(other.descricao))
			return false;
		
		if (id != other.id)
			return false;
		
		if (pergunta == null) {
			
			if (other.pergunta != null)
				return false;
		} 
		
		else if (!pergunta.equals(other.pergunta))
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		
		return "Questao [id=" + id + ", pergunta=" + pergunta + ", descricao="
				+ descricao + "]";
	}
}
