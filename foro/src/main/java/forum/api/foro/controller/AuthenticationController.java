package forum.api.foro.controller;

import forum.api.foro.domain.usuario.DtosVerificarUsuario;
import forum.api.foro.domain.usuario.Usuario;
import forum.api.foro.infra.security.DatosJWTToken;
import forum.api.foro.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationM;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DtosVerificarUsuario dtosVerificar){
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                dtosVerificar.nombre(),
                dtosVerificar.clave()
        );
        System.out.println(dtosVerificar.clave());
        System.out.println(authenticationToken);
        var usuarioAutenticado = authenticationM.authenticate(authenticationToken);
        var JWToken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        System.out.println(JWToken);
        return ResponseEntity.ok(new DatosJWTToken(JWToken));
    }
}
