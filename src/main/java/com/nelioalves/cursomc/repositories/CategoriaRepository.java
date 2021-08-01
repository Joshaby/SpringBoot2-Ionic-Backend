package com.nelioalves.cursomc.repositories;

import com.nelioalves.cursomc.domain.Categoria;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface de repositório para Categorias
 * @author José Henrique
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
