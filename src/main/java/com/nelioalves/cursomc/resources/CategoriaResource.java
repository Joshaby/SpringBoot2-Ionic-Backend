package com.nelioalves.cursomc.resources;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.dto.CategoriaDTO;
import com.nelioalves.cursomc.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe de REST API para endpoints /categorias
 *
 * @author José Henrique
 */
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    /**
     * Procura uma Categoria por id
     * @param id Id da Categoria a ser procurada
     * @return Um JSON de resposta(ResponseEntity) com a Categoria no corpo e com status HTTP ok(200)
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Categoria> find(@PathVariable Integer id) {
        Categoria categoria = categoriaService.find(id);
        return ResponseEntity.ok().body(categoria);
    }

    /**
     * Insere uma Categoria
     * @param categoriaDTO Categoria a ser inserida
     * @return Um JSON de resposta(ResponseEntity) com corpo vazio e com URL da Categoria adicionada no header
     * Usa o status HTTP created(201)
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO categoriaDTO) {
        Categoria categoria = categoriaService.toCategoria(categoriaDTO);
        categoriaService.insert(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(categoriaDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /**
     * Atualiza um Categoria
     * @param categoriaDTO Atualizaçãoe em forma de Categoria
     * @param id Id da Categoria a ser atualizada
     * @return Um JSON de resposta sem corpo e com status HTTP no content(204)
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Categoria> update(@Valid @RequestBody CategoriaDTO categoriaDTO, @PathVariable Integer id) {
        categoriaDTO.setId(id);
        Categoria categoria = categoriaService.toCategoria(categoriaDTO);
        categoriaService.update(categoria);
        return ResponseEntity.noContent().build();
    }

    /**
     * Deleta uma Categoria
     * @param id Id da Categoria a ser removida
     * @return Um JSON de resposta sem corpo e com status HTTP no contente(204)
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Procura todas as Categorias
     * @return Uma lista de Categorias
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        return ResponseEntity.ok().body(
            categoriaService.findAll().stream().map(CategoriaDTO::new).collect(Collectors.toList()));
    }
    /**
     * Procura Categorias e os retornam em um página(Page)
     * @param page Número da página - valor padrão: "0"
     * @param linesPerPage Quantidade de linhas da página - valor padrão: "24"
     * @param direction Direção da página - valor padrão: "ASC"
     * @param orderBy Ordem da página - valor padrão: "id"
     * @return Um JSON como resposta(ResponseEntity) com a página no corpo e com status HTTP ok(200)
     */
    @RequestMapping(value = "/pages", method = RequestMethod.GET)
    public ResponseEntity<Page<CategoriaDTO>> findPage(
        @RequestParam(value = "page", defaultValue = "0") Integer page,
        @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
        @RequestParam(value = "direction", defaultValue = "ASC") String direction,
        @RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {
        return ResponseEntity.ok().body(
            categoriaService.findPage(page, linesPerPage, direction, orderBy).map(CategoriaDTO::new));
    }
}
