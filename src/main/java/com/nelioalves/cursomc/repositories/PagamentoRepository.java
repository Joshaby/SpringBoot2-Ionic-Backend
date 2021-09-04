package com.nelioalves.cursomc.repositories;

import com.nelioalves.cursomc.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de repositório para Pagamentos
 * @author José Henrique
 */
@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
