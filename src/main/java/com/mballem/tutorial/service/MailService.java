package com.mballem.tutorial.service;

import com.mballem.tutorial.domain.MailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Marcio Ballem on 05/08/2014.
 */
@Service
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private VelocityService velocityService;

    public void sendMail(MailMessage message) {
        Date date = new Date();

        message.setFrom( ((JavaMailSenderImpl) javaMailSender).getUsername() );

        //objeto que recebe os dados a serem enviados na mensagem.
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        //quando trabalhamos com anexos, devemos usar um objeto
        // do tipo MimeMessageHelper.
        MimeMessageHelper helper;

        //adiciona ao template do velocity
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("subject", message.getSubject());
        model.put("text", message.getText());
        model.put("to", message.getTo());
        model.put("personal", message.getPersonal());
        model.put("from", message.getFrom());
        model.put("date", date);
        velocityService.setModel(model);

        try {
            //inicializamos o obejto helper.
            helper = new MimeMessageHelper(mimeMessage, true);
            //inserimos dados de quem está enviando a mensagem.
            helper.setFrom(message.getFrom(), message.getPersonal());
            //inserimos o destinatario
            helper.setTo(message.getTo());
            //inserimos a data de envio
            helper.setSentDate(date);
            //inserimos o texto da mensagem,
            // o atributo true indica que será em html
            // e adicionamos o velocity no corpo da mensagem.
            helper.setText(velocityService.getVelocityBody(), true);
            //inserimos o assunto da mensagem.
            helper.setSubject(message.getSubject());
            //inserimos os anexos adicionados a lista de anexos.
            // Fazemos um for() na lista para adicionar um anexo por vez.
            for (String anexo : message.getAttachments()) {
                File attach = new File(anexo);
                helper.addAttachment("Attachment: " + attach.getName(), attach);
            }
            //objeto de envio da mensagem.
            javaMailSender.send(mimeMessage);
            System.out.println("Envio com Sucesso!");
        } catch (MailException e) {
            System.out.println("Email não pode ser eviado!\n" + e.getMessage());
        } catch (MessagingException e) {
            System.out.println("Email não pode ser eviado.\n" + e.getMessage());
        } catch (IOException e) {
            System.out.println("Anexo não encontrado\n" + e.getMessage());
        }
    }
}
