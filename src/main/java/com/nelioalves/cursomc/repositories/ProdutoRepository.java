package com.nelioalves.cursomc.repositories;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Query("SELECT DISTINCT produto " +
        "FROM Produto produto " +
        "INNER JOIN " +
        "produto.categoriasList categorias " +
        "WHERE produto.nome " +
        "LIKE %:nome% AND " +
        "categorias IN :categorias")
    Page<Produto> findDistinctByNomeContainingAndCategoriasIn(
        @Param("nome") String nome,
        @Param("categorias") List<Categoria> categorias,
        Pageable pageable);
}
