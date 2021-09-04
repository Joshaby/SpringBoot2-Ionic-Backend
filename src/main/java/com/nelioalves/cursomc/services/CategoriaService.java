package com.nelioalves.cursomc.services;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.dto.CategoriaDTO;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Classe de serviço com regras de negócio de Categorias
 * @author José Henrique
 */
@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    /**
     * Procura uma Categoria por id
     * @param id Id da Categoria a ser procurada
     * @throws ObjectNotFoundException
     * @return A Categoria encontrada
     */
    public Categoria find(Integer id) {
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
        return optionalCategoria.orElseThrow(() ->
                new ObjectNotFoundException(String.format("Objeto %d não encontrado! Tipo: %s", id, Categoria.class.getName())));
    }

    /**
     * Insere uma Categoria
     * @param categoria Categoria a ser inserida
     * @return A Categoria inserida
     */
    public Categoria insert(Categoria categoria) {
        categoria.setId(null);
        return categoriaRepository.save(categoria);
    }

    /**
     * Atualiza uma Categoria
     * @param categoria Categoria a ser inserida
     * @return A Categoria atualizada
     */
    public Categoria update(Categoria categoria) {
        find(categoria.getId());
        return categoriaRepository.save(categoria);
    }

    /**
     * Deleta uma Categoria por id
     * @param id Id da Categoria a ser deletada
     * @throws DataIntegrityException
     */
    public void delete(Integer id) {
        find(id);
        try {
            categoriaRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível deletar a categoria, ela possuí produtos! ");
        }
    }

    /**
     * Procura todas as Categorias
     * @return Uma lista de Categorias encotradas
     */
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    /**
     * Procura Categorias e as retornam em um Page
     * @param page Número da página
     * @param linesPerPage Linhas da página, o seu tamanho
     * @param direction Direção da página
     * @param orderBy Ordenação da página
     * @return Um Page com Categorias
     */
    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return categoriaRepository.findAll(pageRequest);
    }

    /**
     * Converte um CategoriaDTO em uma Categoria
     * @param categoriaDTO
     * @return Uma Categoria
     */
    public Categoria toCategoria(CategoriaDTO categoriaDTO) {
        return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
    }
}
