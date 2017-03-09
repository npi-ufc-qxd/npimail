# NpiMail - Library to send email via UFC Quixadá Server Mail #

## How to import ##

## Usage Example ##

** Send email without attachment ** 
~~~java
@Autowired
SendEmailService service;

EmailBuilder emailBuilder = new EmailBuilder("UFC Quixada","naoresponda@quixada.ufc.br",
													"Npimail Test", 
													"destinatary@gmail.com", 
													"MessageBody");
Email email = new Email(emailBuilder);
service.sendEmail(email);
~~~

** Send email with attachment ** 
~~~java
@Autowired
SendEmailService service;

EmailBuilder emailBuilder = new EmailBuilder("UFC Quixada","naoresponda@quixada.ufc.br",
													"Npimail Test", 
													"destinatary@gmail.com", 
													"MessageBody");
Email email = new Email(emailBuilder);
File file = new File("/path/to/your/file");
email.setArquivo(file.getName(), file);
service.sendEmail(email);
~~~