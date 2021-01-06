package com.nelioalves.cursomc.resources;

import com.nelioalves.cursomc.domain.Categoria;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
    @RequestMapping(method = RequestMethod.GET)
    public List<Categoria> listar() {
        Categoria cat1 = new Categoria(1, "José");
        Categoria cat2 = new Categoria(2, "Henrique");

        List<Categoria> categoriasList = new ArrayList<>();
        categoriasList.add(cat1);
        categoriasList.add(cat2);

        return categoriasList;
    }
}
