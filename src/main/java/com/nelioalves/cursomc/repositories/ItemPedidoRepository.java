package com.nelioalves.cursomc.repositories;

import com.nelioalves.cursomc.domain.ItemPedido;
import org.springframework.stereotype.Repository;
import com.nelioalves.cursomc.domain.ItemPedidoPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface de repositório para ItemPedidos
 * @author José Henrique
 */
@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoPK> {

}
