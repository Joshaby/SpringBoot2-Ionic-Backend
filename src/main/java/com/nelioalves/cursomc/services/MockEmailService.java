package com.nelioalves.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

/**
 * Classe de serviço de emails para simular envio de emails
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
}
