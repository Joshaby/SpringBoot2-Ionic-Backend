package com.nelioalves.cursomc.repositories;

import com.nelioalves.cursomc.domain.Pedido;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface de repositório para Pedidos
 * @author José Henrique
 */
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
