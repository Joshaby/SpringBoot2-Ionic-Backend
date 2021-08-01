package com.nelioalves.cursomc.repositories;

import com.nelioalves.cursomc.domain.Pagamento;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface de repositório para Pagamentos
 * @author José Henrique
 */
@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
