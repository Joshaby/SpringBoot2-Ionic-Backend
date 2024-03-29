package com.nelioalves.cursomc.security;

import com.nelioalves.cursomc.domain.enums.Perfil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Classe que representa um usuário com informações básica que está logado
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

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * Verifica se o usuário possui algum Perfil
     * @param perfil Perfil a ser verificado
     * @return Um boolean
     */
    public boolean hasHole(Perfil perfil) {
        return grantedAuthorities.contains(new SimpleGrantedAuthority(perfil.getDescricao()));
    }
}
