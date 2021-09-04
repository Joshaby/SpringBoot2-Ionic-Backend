package com.nelioalves.cursomc.services;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.repositories.ProdutoRepository;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Classe de serviço com regras de negócio para Produtos
 * @author José Henrique
 */
@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    /**
     * Procura um Produto
     * @param id Id do Produto a ser procurado
     * @return O Produto encontrado
     * @throws ObjectNotFoundException
     */
    public Produto find(Integer id) {
        Optional<Produto> optionalProduto = produtoRepository.findById(id);
        return optionalProduto.orElseThrow(
                () -> new ObjectNotFoundException(String.format("Objeto %d não encontrado! Tipo: %s", id, Produto.class.getName())));
    }

    /**
     * Procura Produtos e os retornam em um Page
     * @param nome Nome do Produto
     * @param categoriaList Lista de Categorias a ser pesquisadas
     * @param pageable Um Page
     * @return Um Page com Produtos
     */
    public Page<Produto> findDistinctByNomeContainingAndCategoriaIn(String nome, List<Integer> categoriaList, Pageable pageable) {
        List<Categoria> categoriaList1 = categoriaRepository.findAllById(categoriaList);
        return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categoriaList1, pageable);
    }
}
