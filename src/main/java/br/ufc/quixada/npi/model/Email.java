package br.ufc.quixada.npi.model;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Email {

	private Map<String, String> enderecosDestinatarios;
	private String corpo;
	private String assunto;
	private Map<String, File> arquivos;
	private String remetenteNome;
	private String remetenteEmail;
	

	public Email(EmailBuilder builder) {
		this.setCorpo(builder.corpo);
		this.setAssunto(builder.assunto);
		this.enderecosDestinatarios = builder.enderecosDestinatarios;
		this.setRemetenteEmail(builder.remetenteEmail);
		this.setRemetenteNome(builder.remetenteNome);
	}

	

	public Map<String, String> getEnderecosDestinatarios() {
		return enderecosDestinatarios;
	}

	public void setEnderecoDestinatario(String email, String nome) {
		for (String key : this.enderecosDestinatarios.keySet()) {
			if (email.equals(this.enderecosDestinatarios.get(key))) {
				this.enderecosDestinatarios.put(email, nome);
			}
		}
	}

	public String getCorpo() {
		return corpo;
	}

	public void setCorpo(String texto) {
		this.corpo = texto;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String titulo) {
		this.assunto = titulo;
	}

	public Map<String, File> getArquivos() {
		return arquivos;
	}

	public void setArquivos(Map<String, File> arquivos) {
		this.arquivos = arquivos;
	}
	
	public void setArquivo(String nome, File arquivo){
		if(this.arquivos==null){
			this.arquivos = new HashMap<String, File>();
		}
		this.arquivos.put(nome, arquivo);
	}

	public String getRemetenteNome() {
		return remetenteNome;
	}



	public void setRemetenteNome(String remetenteNome) {
		this.remetenteNome = remetenteNome;
	}

	public String getRemetenteEmail() {
		return remetenteEmail;
	}



	public void setRemetenteEmail(String remetenteEmail) {
		this.remetenteEmail = remetenteEmail;
	}

	public static class EmailBuilder {
		private String assunto;
		private String corpo;
		private Map<String, String> enderecosDestinatarios = new HashMap<String, String>();
		private String remetenteNome;
		private String remetenteEmail;
		

		public EmailBuilder(String remetenteNome, String remetenteEmail,String assunto, String email, String corpo) {
			this.assunto = assunto;
			this.enderecosDestinatarios.put(email, email);
			this.corpo = corpo;
			this.remetenteEmail = remetenteEmail;
			this.remetenteNome = remetenteNome;
		}

		public EmailBuilder emailDestinatario(String nome_destinario, String email_destinatario) {
			this.enderecosDestinatarios.put(email_destinatario, nome_destinario);
			return this;
		}

		public Email build() {
			return new Email(this);
		}
	}

}
