package com.nelioalves.cursomc.resources;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.dto.ProdutoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import com.nelioalves.cursomc.services.ProdutoService;
import com.nelioalves.cursomc.resources.utils.ResourcesUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Classe REST API para endpoints /produtos
 * @author José Henrique
 */
@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    /**
     * Procura um Produto por id
     * @param id Id do Produto a ser procurado
     * @return Um JSON como resposta(ResponseEntity) com o Produto no corpo com status HTTP ok(200)
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Produto> find(@PathVariable Integer id) {
        Produto produto = produtoService.find(id);
        return ResponseEntity.ok().body(produto);
    }

    /**
     * Procura por Produtos e os retornam em uma página(Page)
     * @param nome Nome do Produto
     * @param categorias Categorias do produtos em uma lista
     * @param page Número da página - valor padrão: "0"
     * @param linesPerPage Quantidade de linhas da página - valor padrão: "24"
     * @param direction Direção da página - valor padrão: "ASC"
     * @param ordeBy Ordem da página - valor padrão: "nome"
     * @return Um JSON como resposta(ResponseEntity) com a página no corpo e com status HTTP ok(200)
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProdutoDTO>> search(
        @RequestParam(value = "nome", defaultValue = "") String nome,
        @RequestParam(value = "categorias" , defaultValue = "") String categorias,
        @RequestParam(value = "page", defaultValue = "0") Integer page,
        @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
        @RequestParam(value = "direction", defaultValue = "ASC") String direction,
        @RequestParam(value = "orderBy", defaultValue = "nome") String ordeBy) {

        String newNome = ResourcesUtils.ParamDecoder(nome);
        List<Integer> newCategorias = ResourcesUtils.ParamToList(categorias);
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), ordeBy);
        Page<Produto> produtoPage = produtoService.findDistinctByNomeContainingAndCategoriaIn(newNome, newCategorias, pageRequest);
        return ResponseEntity.ok().body(produtoPage.map(ProdutoDTO::new));
    }
}
