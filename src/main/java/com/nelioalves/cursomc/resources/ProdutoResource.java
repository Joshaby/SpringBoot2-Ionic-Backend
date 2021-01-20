package com.nelioalves.cursomc.resources;

import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.dto.ProdutoDTO;
import com.nelioalves.cursomc.resources.utils.ResourcesUtils;
import com.nelioalves.cursomc.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Produto> find(@PathVariable Integer id) {
        Produto produto = produtoService.find(id);
        return ResponseEntity.ok().body(produto);
    }
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
