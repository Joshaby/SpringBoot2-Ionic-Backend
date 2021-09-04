package com.nelioalves.cursomc.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Classe útil com métodos estáticos usados no contexto do JWT
 * @author José Henrique
 */
@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * Gera um token JWT a partir de um email recebido
     * @param email Um email de um Cliente
     * @return O token JWT
     */
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes(StandardCharsets.UTF_8))
                .compact();
    }

    /**
     * Verifica se um token recebido em um requisição é válido
     * @param token Token a ser verificado
     * @return Um boolean
     */
    public boolean tokenValido(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {
            String username = claims.getSubject();
            Date expiration = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());
            if (username != null && expiration != null && now.before(expiration)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Pega as reinvidicações do token, que são o nome de usuário e tempo de expiração
     * @param token Token onde suas reinvidecações seram tiradas
     * @return Um Claims
     */
    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token).getBody();
        }
        catch (Exception e) {
            return null;
        }
    }

    /**
     * Pega o nome de usuário no token
     * @param token Token onde o nome de usuário será extraído
     * @return O nome de usuário
     */
    public String getUsername(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {
            return claims.getSubject();
        }
        return null;
    }
}
