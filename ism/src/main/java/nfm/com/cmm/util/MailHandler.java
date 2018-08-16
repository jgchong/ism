package nfm.com.cmm.util;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailHandler {
    private JavaMailSender mailSender;
    private MimeMessage message;
    private MimeMessageHelper messageHelper;

    public MailHandler(JavaMailSender mailSender) throws MessagingException {
        this.mailSender = mailSender;
        message = this.mailSender.createMimeMessage();
        messageHelper = new MimeMessageHelper(message, true, "UTF-8");
    }


    public void setSubject(String subject) throws MessagingException {
        messageHelper.setSubject(subject);
    }
  
    public void setText(String htmlContent) throws MessagingException {
        messageHelper.setText(htmlContent, true); 
    }
   
    public void setFrom(String email, String name) throws UnsupportedEncodingException, MessagingException {
        messageHelper.setFrom(email, name);
    }
   
    public void setTo(String email) throws MessagingException {
        messageHelper.setTo(email);
    }
    
    public void addInline(String contentId, DataSource dataSource) throws MessagingException {
        messageHelper.addInline(contentId, dataSource);
    }
    
    public void addAttachFile(String filename, String filefullname)  throws MessagingException {

    	File file = new File(filefullname);

        messageHelper.addAttachment(filename, file);
    }
   
    public void setToCC(String emails) throws MessagingException {
    	String ccEmails[] = emails.split(";");
        messageHelper.setCc(ccEmails);
    }
   
    public void send() throws Exception {
            mailSender.send(message);
    }
}