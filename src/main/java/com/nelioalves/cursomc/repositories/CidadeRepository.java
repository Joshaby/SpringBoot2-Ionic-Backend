package com.nelioalves.cursomc.repositories;

import com.nelioalves.cursomc.domain.Cidade;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface de repositório para Cidades
 * @author José Henrique
 */
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
