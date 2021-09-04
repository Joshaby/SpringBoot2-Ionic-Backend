package com.nelioalves.cursomc.config;

import com.nelioalves.cursomc.services.DataBaseService;
import com.nelioalves.cursomc.services.EmailService;
import com.nelioalves.cursomc.services.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

/**
 * Classe de configuração do ambiente test que usa persistência em H2
 * @author José Henrique
 */
@Configuration
@Profile("test")
public class DataBaseConfig {

    @Autowired
    private DataBaseService dataBaseService;

    /**
     * Método para inicializar o banco H2
     * @return um boolean
     * @throws ParseException
     */
    @Bean
    public boolean initializaH2() throws ParseException {
        dataBaseService.initializeDataBase();
        return true;
    }

    /**
     * Método pra retornar um Bean de EmailService
     * @return Um EmailService
     */
    @Bean
    public EmailService getEmailService() {
//        return new MockEmailService();
        return new SmtpEmailService();
    }
}
