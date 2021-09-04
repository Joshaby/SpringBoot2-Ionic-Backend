package com.nelioalves.cursomc.services;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * Classe abstrata de base para serviço de email
 * @author José Henrique
 */
public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;
    @Autowired
    private TemplateEngine templeEngine;
    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * Faz o envio do email
     * @param pedido Pedido a ser enviado pelo email
     */
    @Override
    public void sendOrderConfirmationEmail(Pedido pedido) {
        SimpleMailMessage simpleMailMessage = prepareMailMessage(pedido);
        sendEmail(simpleMailMessage);
    }

    /**
     * Envia um email com HTML!
     * Caso ocorra uma MessagingException, um email simples é enviado
     * @param pedido Pedido a ser enviado no email
     */
    @Override
    public void sendOrderConfirmationHTMLEmail(Pedido pedido) {
        try {
            MimeMessage mimeMessage = prepareHTMLMimeMessage(pedido);
            sendHTMLEmail(mimeMessage);
        }
        catch (MessagingException e) {
            sendOrderConfirmationEmail(pedido);
        }
    }

    /**
     * Faz envio do email
     * @param cliente Cliente a receber o email
     * @param newPassword Nova senha do Cliente definida pelo sistema
     */
    @Override
    public void sendNewPasswordEmail(Cliente cliente, String newPassword) {
        SimpleMailMessage simpleMailMessage = prepareNewPasswordEmail(cliente, newPassword);
        sendEmail(simpleMailMessage);
    }

    /**
     * Prepara o email(SimpleMailMessage)
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

    /**
     * Prepara um email(MimeMessage)
     * @param pedido Pedido a ser inserido no email
     * @throws MessagingException
     * @return O email(MimeMessage) a ser enviado
     */
    protected MimeMessage prepareHTMLMimeMessage(Pedido pedido) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(pedido.getCliente().getEmail());
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setSubject(String.format("Pedido confirmado! Código: %d", pedido.getId()));
        mimeMessageHelper.setSentDate(new Date(System.currentTimeMillis()));
        mimeMessageHelper.setText(htmlFromTemplatePedido(pedido), true);
        return mimeMessage;
    }

    /**
     * Prepara um email(SimpleMailMessage) contendo a nova senha do Cliente
     * @param cliente Cliente que recebeŕa um email
     * @param newPassword Nova senha do Cliete definida pelo sistema
     * @return Um email(SimpleMailMessage)
     */
    protected SimpleMailMessage prepareNewPasswordEmail(Cliente cliente, String newPassword) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(cliente.getEmail());
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setSubject("Solicitação de nova senha");
        simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
        simpleMailMessage.setText(String.format("Nova senha: %s", newPassword));
        return simpleMailMessage;
    }

    /**
     * Coloca dados do Pedido em um HTML com Thymeleaf
     * @param pedido Pedido a ser usado no HTML
     * @return O HTML povoado com dados do Pedido
     */
    protected String htmlFromTemplatePedido(Pedido pedido) {
        Context context = new Context();
        context.setVariable("pedido", pedido);
        return templeEngine.process("email/confirmacaoPedido", context);
    }
}
