package com.nelioalves.cursomc.services;

import javax.mail.internet.MimeMessage;
import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.domain.Cliente;
import org.springframework.mail.SimpleMailMessage;

/**
 * Interface para serviço de email
 * @author José Henrique
 */
public interface EmailService {

    void sendOrderConfirmationEmail(Pedido pedido);

    void sendEmail(SimpleMailMessage simpleMailMessage);

    void sendOrderConfirmationHTMLEmail(Pedido pedido);

    void sendHTMLEmail(MimeMessage mimeMessage);

    void sendNewPasswordEmail(Cliente cliente, String newPassword);
}
