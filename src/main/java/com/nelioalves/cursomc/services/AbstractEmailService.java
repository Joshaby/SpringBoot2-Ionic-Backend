package com.nelioalves.cursomc.services;

import java.util.Date;
import com.nelioalves.cursomc.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.beans.factory.annotation.Value;

/**
 * Classe abstrata de base para serviço de email
 * @author José Henrique
 */
public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    /**
     * Prepara o email e faz o envio
     * @param pedido Pedido a ser enviado pelo email
     */
    @Override
    public void sendOrderConfirmationEmail(Pedido pedido) {
        SimpleMailMessage simpleMailMessage = prepareMailMessage(pedido);
        sendEmail(simpleMailMessage);
    }

    /**
     * Prepara o email
     * @param pedido Pedido a ser enviado pelo email
     * @return O email(SimpleMailMessage) preparado
     */
    protected SimpleMailMessage prepareMailMessage(Pedido pedido) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(pedido.getCliente().getEmail());
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setSubject(String.format("Pedido confirmado! Código: %d", pedido.getId()));
        simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
        simpleMailMessage.setText(pedido.toString());
        return simpleMailMessage;
    }
}
