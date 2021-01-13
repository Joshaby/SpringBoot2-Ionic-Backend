package com.nelioalves.cursomc.services;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Categoria find(Integer id) {
        Optional<Categoria> optionalCategoria = repository.findById(id);
        return optionalCategoria.orElseThrow(() ->
                new ObjectNotFoundException(String.format("Objeto %d não encontrado! Tipo: %s", id, Categoria.class.getName())));
    }
    public Categoria insert(Categoria categoria) {
        categoria.setId(null);
        return repository.save(categoria);
    }
    public Categoria update(Categoria categoria) {
        return repository.save(categoria);
    }
    public void delete(Integer id) {
        find(id);
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível deletar a categoria, ela possuí produtos! ");
        }
    }
    public List<Categoria> findAll() {
        return repository.findAll();
    }
}
