# Npi-Mail - Library to send email via UFC Quixadá Server Mail #

## How to import ##

Add this elements in your pom.xml

**In `<properties></properties>`**
~~~xml
<properties>
  <!-- NPI Mail Version -->
  <npi.mail.version>1.0.0</npi.mail.version>
</properties>
~~~

**In `<repositories></repositories>`**
~~~xml
<repositories>
  <repository>
    <id>npi-mvn-repo</id>
    <url>http://npi-ufc-qxd.github.io/npi-mvn-repo/</url>
  </repository>
</repositories>
~~~

**In `<dependencies></dependencies>`**
~~~xml
<dependencies>
  <!-- NPI Mail -->
  <dependency>
    <groupId>br.ufc.quixada.npi</groupId>
    <artifactId>npi-mail</artifactId>
    <version>${npi.mail.version}</version>
  </dependency>
</dependencies>
~~~

## Usage Example ##

**Send email without attachment** 
~~~java
@Autowired
SendEmailService service;

EmailBuilder emailBuilder = new EmailBuilder("Nome do Remetente",
											 "email@do.remetente.br",
											 "Email Subject", 
											 "destinatary@gmail.com", 
											 "Message Body");
Email email = new Email(emailBuilder);
service.sendEmail(email);
~~~

**Send email with attachment** 
~~~java
@Autowired
SendEmailService service;

EmailBuilder emailBuilder = new EmailBuilder("Nome do Remetente",
											 "email@do.remetente.br",
											 "Email Subject", 
											 "destinatary@gmail.com", 
											 "Message Body");
Email email = new Email(emailBuilder);
File file = new File("/path/to/your/file");
email.setArquivo(file.getName(), file);
service.sendEmail(email);
~~~