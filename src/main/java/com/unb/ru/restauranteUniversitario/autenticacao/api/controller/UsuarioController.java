package com.unb.ru.restauranteUniversitario.autenticacao.api.controller;

import com.unb.ru.restauranteUniversitario.autenticacao.api.dto.UsuarioDto;
import com.unb.ru.restauranteUniversitario.autenticacao.exception.ErroDeAutenticacao;
import com.unb.ru.restauranteUniversitario.autenticacao.exception.RegraNegocioException;
import com.unb.ru.restauranteUniversitario.autenticacao.model.entity.Usuario;
import com.unb.ru.restauranteUniversitario.autenticacao.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UsuarioController {


    private UsuarioService service;

    public UsuarioController(UsuarioService service ){
        this.service = service;
    }
    @PostMapping("/autenticar")
    public  ResponseEntity autenticar(@RequestBody UsuarioDto dto){
        try {
           Usuario usuarioAutenticado = service.autenticar(dto.getEmail(), dto.getSenha());
            return ResponseEntity.ok(usuarioAutenticado);
        }catch (ErroDeAutenticacao e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/cadastrar")
    public ResponseEntity salvar(@RequestBody UsuarioDto dto){
        Usuario usuario = Usuario.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(dto.getSenha())
                .matricula(dto.getMatricula())
                .build();

        try{
            Usuario usuarioSalvo = service.cadastrar(usuario);

            return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);

        }catch (RegraNegocioException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
