package com.nelioalves.cursomc.services;

import com.nelioalves.cursomc.security.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Classe de serviço com regras de negócio para usuários(UserDetailsImpl)
 * @author José Henrique
 */
public class UserService {

    /**
     * Retorna um usuário logado
     * @return Um usuário(UserDetailsImpl) logado
     */
    public static UserDetailsImpl getUserAuthenticated() {
        return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
