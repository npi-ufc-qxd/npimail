package br.ufc.quixada.npi.service;


import java.util.Map;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import br.ufc.quixada.npi.model.Email;

@Service
public class SendEmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	public SendEmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public boolean sendEmail(final Email email) {

		Map<String, String> destinatariosHash;
		int i = 0;
		destinatariosHash = email.getEnderecosDestinatarios();
		int qtdDestinatario = destinatariosHash.size();

		final InternetAddress[] address = new InternetAddress[qtdDestinatario];

		for (String key : destinatariosHash.keySet()) {
			try {
				address[i] = new InternetAddress(key);
			} catch (AddressException e) {
				e.printStackTrace();
			}
		}

		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				messageHelper.setTo(address);
				messageHelper.setFrom(new InternetAddress(email.getRemetenteEmail(), email.getRemetenteNome()));
				messageHelper.setText(email.getCorpo());
				messageHelper.setSubject(email.getAssunto());

				if (email.getArquivos() != null) {
					for (String key : email.getArquivos().keySet()) {
						FileSystemResource file = new FileSystemResource(email.getArquivos().get(key));
						String attachName = email.getArquivos().get(key).getName();

						messageHelper.addAttachment(attachName, file);

					}
				}

			}
		};

		try {
			javaMailSender.send(preparator);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}