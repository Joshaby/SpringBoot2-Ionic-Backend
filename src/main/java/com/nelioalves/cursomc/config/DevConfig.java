package com.nelioalves.cursomc.config;

import java.text.ParseException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import com.nelioalves.cursomc.services.DataBaseService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Classe de configuração de ambiente Dev que usa persistência em MySQL
 * @author José Henrique
 */
@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DataBaseService dataBaseService;
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    /**
     * Método para incializar o banco no MySQL ou MariaDB
     * @return
     * @throws ParseException
     */
    @Bean
    public boolean initializeMariaDB() throws ParseException {
        dataBaseService.initializeDataBase();
        return true;
    }
}
