package com.nelioalves.cursomc.repositories;

import com.nelioalves.cursomc.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de repositório para Estados
 * @author José Henrique
 */
@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

}
