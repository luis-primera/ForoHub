package Challenge.ForoHub.controller;

import Challenge.ForoHub.domain.usuario.DatosAutenticacion;
import Challenge.ForoHub.domain.usuario.Usuario;
import Challenge.ForoHub.infra.security.DatosTokenJWT;
import Challenge.ForoHub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity iniciarSesion(@RequestBody @Valid DatosAutenticacion datos){
        var autenticacionToken = new UsernamePasswordAuthenticationToken(datos.usuario(), datos.contrasena());
        var autenticacion = manager.authenticate(autenticacionToken);
        var tokenJWT = tokenService.generaToken((Usuario) autenticacion.getPrincipal());
        return  ResponseEntity.ok(new DatosTokenJWT(tokenJWT));
    }
}
