package com.nelioalves.cursomc.repositories;

import com.nelioalves.cursomc.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de repositório para Cidades
 * @author José Henrique
 */
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
