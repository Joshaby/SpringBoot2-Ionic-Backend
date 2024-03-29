package com.nelioalves.cursomc.resources;

import com.nelioalves.cursomc.dto.EmailDTO;
import com.nelioalves.cursomc.security.UserDetailsImpl;
import com.nelioalves.cursomc.security.util.JWTUtil;
import com.nelioalves.cursomc.services.AuthService;
import com.nelioalves.cursomc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Classe REST API para endpoint /auth
 *
 * @author José Henrique
 */
@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private AuthService authService;

    /**
     * Gera um novo token para um usuário logado com token perto de expiração
     * @param httpServletResponse Resposta a ser enviada ao usuário
     * @return Um JSON de resposta(ResponseEntity) sem corpo e com status HTTP no contente(204)
     */
    @RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
    public ResponseEntity<Void> refreshToken(HttpServletResponse httpServletResponse) {
        UserDetailsImpl userDetailsImpl = UserService.getUserAuthenticated();
        String token = jwtUtil.generateToken(userDetailsImpl.getUsername());
        httpServletResponse.addHeader("Authorization", "Bearer " + token);
        return ResponseEntity.noContent().build();
    }

    /**
     * Gera uma nova senha e a envia por email
     * @param emailDTO Email que irá receber a nova senha
     * @return Um JSON de resposta(ResponseEntity) sem corpo e com status HTTP no contente(204)
     */
    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO emailDTO) {
        authService.sendNewPassword(emailDTO.getEmail());
        return ResponseEntity.noContent().build();
    }
}
