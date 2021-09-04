package com.nelioalves.cursomc.repositories;

import com.nelioalves.cursomc.domain.ItemPedido;
import com.nelioalves.cursomc.domain.ItemPedidoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de repositório para ItemPedidos
 * @author José Henrique
 */
@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoPK> {

}
