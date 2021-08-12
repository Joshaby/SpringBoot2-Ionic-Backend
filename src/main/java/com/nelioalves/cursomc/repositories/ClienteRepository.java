package com.nelioalves.cursomc.repositories;

import com.nelioalves.cursomc.domain.Cliente;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface de repositório para Clientes
 * @author José Henrique
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    /**
     * Procura um Cliente por email
     * @param email Email do Cliente a ser procurado
     * @return Um Cliente encontrado
     */
    Cliente findByEmail(String email);
}
