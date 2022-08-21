package com.unb.ru.restauranteUniversitario.autenticacao.model.repository;

import com.unb.ru.restauranteUniversitario.autenticacao.model.entity.Usuario;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

    @Autowired
    UsuarioRepository repository;

    @Test
    public void deveVerificarAexistenciaDeumEmail(){
        //cenario
        Usuario usuario = Usuario.builder().matricula("211041295").nome("usuario2").email("usuario@email.com").senha("1234").build();
        repository.save(usuario);
        //ação
        boolean result = repository.existsByEmail("usuario@email.com");
        //verificação
        Assertions.assertThat(result).isTrue();

    }

    @Test
    public void deveRetornarFalsoQuandoHouverUsuarioCadastradoComEmail(){
        //cenario
        repository.deleteAll();
        //ação
        boolean result = repository.existsByEmail("usuario@email.com");
        //verificação
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void deveVerificarAexistenciaDeumaMatricula(){
        //cenario
        Usuario usuario = Usuario.builder().matricula("211041295").nome("usuario2").email("usuario@email.com").senha("1234").build();
        repository.save(usuario);
        //ação
        boolean result = repository.existsByMatricula("211041295");
        //verificação
        Assertions.assertThat(result).isTrue();

    }


    @Test
    public void devePersistirUmUsuarioNaBaseDeDados(){
        //cenario
        Usuario usuario = criarUsuario();
        //acao
        Usuario usuarioSalvo = repository.save(usuario);

        //verificacao
        Assertions.assertThat(usuarioSalvo.getMatricula()).isNotNull();
    }
    @Test

    public void deveBuscarUmUsuarioPorEmail(){
        Usuario usuario = criarUsuario();
        repository.save(usuario);

        Optional<Usuario> result = repository.findByEmail("usuario@email.com");

        Assertions.assertThat(result.isPresent());

    }
    @Test

    public void deveRetornarVazioAoBuscarUmUsuarioPorEmailQuandoNaoExisteNaBase(){
        repository.deleteAll();
        Optional<Usuario> result = repository.findByEmail("usuario@email.com");

        Assertions.assertThat(result.isPresent()).isFalse();

    }

    public static Usuario criarUsuario(){
        Usuario usuario = Usuario.builder()
                .matricula("211041295")
                .nome("usuario")
                .email("usuario@email.com")
                .senha("123")
                .build();

        return usuario;
    }
    



}
