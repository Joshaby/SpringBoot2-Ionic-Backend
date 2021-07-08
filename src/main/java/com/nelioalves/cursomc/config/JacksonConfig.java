package com.nelioalves.cursomc.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import com.nelioalves.cursomc.domain.PagamentoComBoleto;
import com.nelioalves.cursomc.domain.PagamentoComCartao;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * Classe de configuração do Jackson(Biblioteca de json Java)
 * @author José Henrique
 */
@Configuration
public class JacksonConfig {

    /**
     * Método Bean para mapear os "filhos" de Pagamento! Esse mapeamento é feito para que a requisiçao do usuário que
     * contenha um PagamentoComBoleto ou PagamentoComCartao seja processada da maneira correta pelo Backend, ou seja,
     * que o backend saiba instanciar de forma correta as subclasses de Pagamento
     * @return Jackson2ObjectMapperBuilder
     */
    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder = new Jackson2ObjectMapperBuilder() {
            public void configure(ObjectMapper objectMapper) {
                objectMapper.registerSubtypes(PagamentoComCartao.class);
                objectMapper.registerSubtypes(PagamentoComBoleto.class);
                super.configure(objectMapper);
            }
        };
        return jackson2ObjectMapperBuilder;
    }
}
