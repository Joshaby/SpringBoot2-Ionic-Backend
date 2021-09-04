package com.nelioalves.cursomc.services;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Classe de serviço com regras de negócio para autenticação
 * @author José Henrique
 */
@Service
public class AuthService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private EmailService emailService;
    private Random random = new Random();

    /**
     * Gera uma nova senha e faz envio dela por email
     * @param email Email que irá receber a senha
     */
    public void sendNewPassword(String email) {
        Cliente cliente = clienteRepository.findByEmail(email);
        if (cliente == null) {
            throw new ObjectNotFoundException("Email não encontrado");
        }
        String newPassword = generateNewPassword();
        cliente.setSenha(bCryptPasswordEncoder.encode(newPassword));
        emailService.sendNewPasswordEmail(cliente, newPassword);
    }

    /**
     * Gera uma senha alerátoria
     * @return Uma senha
     */
    private String generateNewPassword() {
        char[] password = new char[10];
        for (int i = 0; i < 10; i ++) {
            password[i] = getRandomChar();
        }
        return new String(password);
    }

    /**
     * Gera caracteres randômicos
     * @return Um caracter
     */
    private char getRandomChar() {
        int option = random.nextInt(3);
        if (option == 1) {
            return (char) (random.nextInt(10) + 48);
        }
        else if (option == 2) {
            return (char) (random.nextInt(26) + 65);
        }
        else {
            return (char) (random.nextInt(26) + 97);
        }
    }
}
