package com.nelioalves.cursomc.repositories;

import com.nelioalves.cursomc.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de repositório para Endereços
 * @author José Henrique
 */
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
