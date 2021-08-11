package com.nelioalves.cursomc.resources;

import javax.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import com.nelioalves.cursomc.services.UserService;
import com.nelioalves.cursomc.security.util.JWTUtil;
import com.nelioalves.cursomc.security.UserDetailsImpl;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe REST API para endpoint /auth
 * @author José Henrique
 */
@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

    @Autowired
    private JWTUtil jwtUtil;

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
}
