package com.nelioalves.cursomc.config;

import com.nelioalves.cursomc.services.DataBaseService;
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

    @Bean
    public boolean initializaH2() throws ParseException {
        dataBaseService.initializeDataBase();
        return true;
    }
}
