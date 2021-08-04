package com.nelioalves.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Classe de serviço de email que envia email usando SMTP do Google
 * @author José Henrique
 */
public class SmtpEmailService extends AbstractEmailService {

    private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

    @Autowired
    private MailSender mailSender;
    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * Envia o email
     * @param simpleMailMessage Email a ser enviado
     */
    @Override
    public void sendEmail(SimpleMailMessage simpleMailMessage) {
        LOG.info("Enviando email...");
        mailSender.send(simpleMailMessage);
        LOG.info("Email enviado");
    }

    /**
     * Envia o email com HTML
     * @param mimeMessage Email a ser enviado
     */
    @Override
    public void sendHTMLEmail(MimeMessage mimeMessage) {
        LOG.info("Enviando email com HTML...");
        javaMailSender.send(mimeMessage);
        LOG.info("Email enviado!");
    }
}
