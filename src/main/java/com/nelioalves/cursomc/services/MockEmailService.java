package com.nelioalves.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

/**
 * Classe de serviço de emails para simular envio de emails
 *
 * @author José Henrique
 */
public class MockEmailService extends AbstractEmailService {

    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

    /**
     * Envio simulado de email
     * @param simpleMailMessage Email a ser enviado
     */
    @Override
    public void sendEmail(SimpleMailMessage simpleMailMessage) {
        LOG.info("Enviando email...");
        LOG.info(simpleMailMessage.toString());
        LOG.info("Email enviado!");
    }

    /**
     * Envio simulado de email com HTML
     * @param mimeMessage Email a ser enviado
     */
    @Override
    public void sendHTMLEmail(MimeMessage mimeMessage) {
        LOG.info("Enviando email com HTML...");
        LOG.info(mimeMessage.toString());
        LOG.info("Email enviado!");
    }
}
