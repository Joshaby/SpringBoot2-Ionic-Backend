package com.nelioalves.cursomc.services;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}
