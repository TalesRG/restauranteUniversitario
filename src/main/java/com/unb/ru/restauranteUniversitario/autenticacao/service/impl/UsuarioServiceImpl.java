package com.unb.ru.restauranteUniversitario.autenticacao.service.impl;

import com.unb.ru.restauranteUniversitario.autenticacao.exception.ErroDeAutenticacao;
import com.unb.ru.restauranteUniversitario.autenticacao.exception.RegraNegocioException;
import com.unb.ru.restauranteUniversitario.autenticacao.model.entity.Usuario;
import com.unb.ru.restauranteUniversitario.autenticacao.model.repository.UsuarioRepository;
import com.unb.ru.restauranteUniversitario.autenticacao.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@AllArgsConstructor

public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository repository;

    @Override
    public Usuario autenticar(String email, String senha) {
     var usuario =  repository.findByEmail(email);

     if (!usuario.isPresent()){
         throw new ErroDeAutenticacao("Usúario não encontrado para o e-mail informado");
     }
     if (!usuario.get().getSenha().equals(senha)){
        throw  new ErroDeAutenticacao("Senha Inválida");
     }
        return usuario.get();
    }

    @Override
    @Transactional
    public Usuario cadastrar(Usuario usuario) {
        validarEmail(usuario.getEmail());
        validarMatricula(usuario.getMatricula());

        return repository.save(usuario);
    }

    @Override
    public boolean validarEmail(String email) {
        boolean existe  = repository.existsByEmail(email);
        if (existe){
            throw new RegraNegocioException("Já existe um úsuario cadastrado com este email");
        }

        return existe;
    }
    public boolean validarMatricula(String matricula){
        boolean existe  = repository.existsByMatricula(matricula);
        if (existe){
            throw new RegraNegocioException("Já existe um úsuario cadastrado com esta matricula");
        }

        return existe;

    }

}
