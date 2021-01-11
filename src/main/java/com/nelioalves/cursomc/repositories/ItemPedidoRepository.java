package com.nelioalves.cursomc.repositories;

import com.nelioalves.cursomc.domain.ItemPedidoPK;
import com.nelioalves.cursomc.domain.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoPK> {

}
