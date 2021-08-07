package com.nelioalves.cursomc.security.util;

import java.util.Date;
import io.jsonwebtoken.Jwts;
import java.nio.charset.StandardCharsets;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

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
}
