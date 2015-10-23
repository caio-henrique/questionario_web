package br.com.softbox.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import br.com.softbox.enums.EnumTipoPergunta;

@Entity
@PrimaryKeyJoinColumn( name = "id" )
public class Pergunta implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn( name = "id_questionario")
	private Questionario questionario;
	
	@Enumerated(EnumType.STRING)
	private EnumTipoPergunta tipo;
	
	@Column(nullable = false, length = 255)
	private String pergunta;
	
	public Pergunta() {
		
		this.questionario = new Questionario();
	}

	public long getId() {
		
		return this.id;
	}

	public void setId(long id) {
		
		this.id = id;
	}

	public Questionario getQuestionario() {
		
		return this.questionario;
	}

	public void setQuestionario(Questionario questionario) {
		
		this.questionario = questionario;
	}

	public EnumTipoPergunta getTipo() {
		
		return this.tipo;
	}

	public void setTipo(EnumTipoPergunta tipo) {
		
		this.tipo = tipo;
	}

	public String getPergunta() {
		
		return this.pergunta;
	}

	public void setPergunta(String pergunta) {
		
		this.pergunta = pergunta;
	}

	public static long getSerialversionuid() {
		
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((pergunta == null) ? 0 : pergunta.hashCode());
		result = prime * result
				+ ((questionario == null) ? 0 : questionario.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		
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
		
		Pergunta other = (Pergunta) obj;
		
		if (id != other.id)
			return false;
		
		if (pergunta == null) {
			
			if (other.pergunta != null)
				return false;
		}
		
		else if (!pergunta.equals(other.pergunta))
			return false;
		
		if (questionario == null) {
			
			if (other.questionario != null)
				return false;
		} 
		
		else if (!questionario.equals(other.questionario))
			return false;
		
		if (tipo != other.tipo)
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		
		return "Pergunta [id=" + id + ", questionario=" + questionario
				+ ", tipo=" + tipo + ", pergunta=" + pergunta + "]";
	}
}
