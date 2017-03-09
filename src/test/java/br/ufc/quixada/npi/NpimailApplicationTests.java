package br.ufc.quixada.npi;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufc.quixada.npi.model.Email;
import br.ufc.quixada.npi.model.Email.EmailBuilder;
import br.ufc.quixada.npi.service.SendEmailService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NpimailApplicationTests {

	@Autowired
	SendEmailService service;
	
	@Test
	public void sendMailTest() {
		EmailBuilder emailBuilder = new EmailBuilder("UFC Quixada","naoresponda@quixada.ufc.br",
													"Npimail Test", 
													"zarathonmaia@gmail.com", 
													"um teste muito louco!");
		Email email = new Email(emailBuilder);
		Assert.assertTrue(service.sendEmail(email));
		
	}
	
	@Test
	public void sendMailWithAttachmentsTest() {
		EmailBuilder emailBuilder = new EmailBuilder("UFC Quixada","naoresponda@quixada.ufc.br",
													"Npimail Test", 
													"zarathonmaia@gmail.com",
													"um teste muito louco com anexo!");
		Email email = new Email(emailBuilder);
		File file = new File("/home/zara/eu.png");
		email.setArquivo(file.getName(), file);
		
		Assert.assertTrue(service.sendEmail(email));
		
	}

}
