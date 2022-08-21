package com.unb.ru.restauranteUniversitario.autenticacao.service;

import com.unb.ru.restauranteUniversitario.autenticacao.model.entity.Usuario;

public interface UsuarioService {

    Usuario autenticar(String email, String senha);

    Usuario cadastrar(Usuario usuario);

    boolean validarEmail(String email);

    boolean validarMatricula(String matricula);
}
