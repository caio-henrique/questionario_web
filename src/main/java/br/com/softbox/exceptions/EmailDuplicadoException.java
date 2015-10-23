package br.com.softbox.exceptions;

public class EmailDuplicadoException extends Exception{

	private static final long serialVersionUID = 1L;
	private String mensagem;

	public EmailDuplicadoException(String mensagem, String causa) {
		
		super(mensagem, new Exception(causa));
        this.mensagem = mensagem;
	}
	
	public String getMensagem(){
		
		return this.mensagem;
	}
}