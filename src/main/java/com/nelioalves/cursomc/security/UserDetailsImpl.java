package com.nelioalves.cursomc.security;

import java.util.Set;
import java.util.Collection;
import java.util.stream.Collectors;
import com.nelioalves.cursomc.domain.enums.Perfil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * Classe que representa um Cliente com informações básica
 * @author José Henrique
 */
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String email;
    private String senha;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public UserDetailsImpl() {

    }
    public UserDetailsImpl(Integer id, String email, String senha, Set<Perfil> perfilSet) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.grantedAuthorities = perfilSet.stream().map(
                perfil -> new SimpleGrantedAuthority(perfil.getDescricao())).collect(Collectors.toSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
