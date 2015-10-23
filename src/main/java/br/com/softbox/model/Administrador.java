package br.com.softbox.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;

import br.com.softbox.enums.EnumTipoAdministrador;

@Entity
@PrimaryKeyJoinColumn( name = "id" )
public class Administrador extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false, length = 90)
	private String senha;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private EnumTipoAdministrador tipo;

	public String getSenha() {
		
		return this.senha;
	}

	public void setSenha(String senha) {
		
		this.senha = senha;
	}
	
	public EnumTipoAdministrador getTipo() {
		
		return this.tipo;
	}

	public void setTipo(EnumTipoAdministrador tipo) {
		
		this.tipo = tipo;
	}

	public static long getSerialversionuid() {
		
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Administrador other = (Administrador) obj;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Administrador [senha=" + senha + ", tipo=" + tipo + "]";
	}
}
