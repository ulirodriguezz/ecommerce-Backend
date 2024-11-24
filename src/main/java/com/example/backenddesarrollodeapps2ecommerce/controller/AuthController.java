package com.example.backenddesarrollodeapps2ecommerce.controller;

import ar.edu.uade.Broker;
import ar.edu.uade.Modules;
import com.example.backenddesarrollodeapps2ecommerce.core.dtos.Utilidades;
import com.example.backenddesarrollodeapps2ecommerce.model.entities.UsuarioEntity;
import com.example.backenddesarrollodeapps2ecommerce.scheduled.ManejadorDeSesiones;
import com.example.backenddesarrollodeapps2ecommerce.service.UsuariosService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import javax.security.auth.login.CredentialException;
import java.util.Date;

@RestController
@RequestMapping("")
public class AuthController {
    int MINUTOS_EXPIRACION_TOKEN = 60;
    @Autowired
    UsuariosService usuariosService;
    @Autowired
    private SecretKey secretKey;
    @Autowired
    private ManejadorDeSesiones manejadorDeSesiones;
    @GetMapping("/healthcheck")
    public ResponseEntity<?> healthcheck() {
        return new ResponseEntity<>(new Mensaje("UP"), HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioEntity credenciales) {
        try{
           UsuarioEntity datosUsuario = usuariosService.validarCredenciales(credenciales);
        }catch (CredentialException e){
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
        catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>(new Mensaje("Username incorrecto"), HttpStatus.UNAUTHORIZED);
        }
        catch (Throwable e){
            return new ResponseEntity<>(new Mensaje("Error interno"), HttpStatus.NOT_ACCEPTABLE);
        }
        String token = Jwts.builder().setSubject(credenciales.getNombreUsuario())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + MINUTOS_EXPIRACION_TOKEN * 60 * 1000))
                .signWith(secretKey, SignatureAlgorithm.HS256).compact();


        return new ResponseEntity<>(new Token(token), HttpStatus.OK);
    }
    @PostMapping("/prueba")
    public ResponseEntity<?> mensajeGI(@RequestBody String username) {

        try {
            Utilidades.enviarMensaje("HOla que tal",Modules.E_COMMERCE,"Prueba","Hola",manejadorDeSesiones.getTokenJWTModulo());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(new Mensaje("OK"), HttpStatus.OK);
    }

}
