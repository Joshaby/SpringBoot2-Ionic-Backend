package com.nelioalves.cursomc.security;

import java.util.Date;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.nelioalves.cursomc.dto.CredenciaisDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nelioalves.cursomc.security.util.JWTUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Classe que representa filtro de autenticação
 * @author José Henrique
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private JWTUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        setAuthenticationFailureHandler(new JWTAuthenticaionFailureHandler());
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Tenta fazer a autenticação de usuário
     * @param httpServletRequest Requisição feita pelo usuário
     * @param httpServletResponse Resposta a ser enviada pelo usuário
     * @return Um Authentication
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws AuthenticationException {

        try {
            CredenciaisDTO credenciaisDTO = new ObjectMapper()
                    .readValue(httpServletRequest.getInputStream(), CredenciaisDTO.class);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            credenciaisDTO.getEmail(), credenciaisDTO.getSenha(), new ArrayList<>());
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            return authentication;
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retorna um token JWT para o usuário caso a autenticação tenha ocorrido com sucesso
     * @param httpServletRequest Requisição feita pelo usuário
     * @param httpServletResponse Resposta a ser enviada para o usuário
     * @param filterChain Objeto usado para chamada de funções posteriores na cadeia de responsabilidade
     * @param authentication Autenticação feita anteriormente em attemptAuthentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(
            HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            FilterChain filterChain, Authentication authentication) throws IOException, ServletException {

        String username = ((UserDetailsImpl) authentication.getPrincipal()).getUsername();
        String token = jwtUtil.generateToken(username);
        httpServletResponse.addHeader("Authorization", "Bearer " + token);
    }

    /**
     * Classe que representa um manipulador de falha para erros de login
     * @author José Henrique
     */
    private class JWTAuthenticaionFailureHandler implements AuthenticationFailureHandler {

        /**
         * Método chamado para montar resposta do servidor em caso de erro de login
         * @param httpServletRequest Requisição do usuário
         * @param httpServletResponse Resposta a ser enviada para o usuário
         * @param authenticationException Exceção de autenticação
         * @throws IOException
         * @throws ServletException
         */
        @Override
        public void onAuthenticationFailure(
                HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                AuthenticationException authenticationException) throws IOException, ServletException {

            httpServletResponse.setStatus(401);
            httpServletResponse.setContentType("application/json");
            httpServletResponse.getWriter().append(json());
        }

        /**
         * Monta o JSON de resposta
         * @return Um JSON
         */
        private String json() {
            long date = new Date().getTime();
            return "{\"timestamp\": " + date + ", " +
                    "\"status\": 401" +
                    "\"error\": Não autorizado" +
                    "\"message\": \"Email ou senha inválidos\"" +
                    "\"path\": \"/login\"" +
                    "}";
        }
    }

}
