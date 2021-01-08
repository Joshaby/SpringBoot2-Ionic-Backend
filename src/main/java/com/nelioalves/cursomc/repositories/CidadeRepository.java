package com.nelioalves.cursomc.repositories;

import com.nelioalves.cursomc.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
