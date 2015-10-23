package br.com.softbox.exceptions;

public class NomeDuplicadoException extends Exception {

	private static final long serialVersionUID = 1L;
	private String mensagem;

	public NomeDuplicadoException(String mensagem, String causa) {
		
		super(mensagem, new Exception(causa));
        this.mensagem = mensagem;
	}
	
	public String getMensagem(){
		
		return this.mensagem;
	}
}