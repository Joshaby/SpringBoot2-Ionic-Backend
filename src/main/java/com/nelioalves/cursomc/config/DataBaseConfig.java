package com.nelioalves.cursomc.config;

import com.nelioalves.cursomc.services.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import java.text.ParseException;

@Configuration
@Profile("test")
public class DataBaseConfig {

    @Autowired
    private DataBaseService dataBaseService;

    @Bean
    public boolean initializaH2() throws ParseException {
        dataBaseService.initializeJ2();
        return true;
    }
}
