package com.unb.ru.restauranteUniversitario.autenticacao.service;

import com.unb.ru.restauranteUniversitario.autenticacao.exception.ErroDeAutenticacao;
import com.unb.ru.restauranteUniversitario.autenticacao.model.entity.Usuario;
import com.unb.ru.restauranteUniversitario.autenticacao.model.repository.UsuarioRepository;
import com.unb.ru.restauranteUniversitario.autenticacao.service.impl.UsuarioServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @InjectMocks
    UsuarioServiceImpl service;

    @Mock
    UsuarioRepository repository;

    @Test
    public void deveValidarEmail() {
        repository.deleteAll();

        service.validarEmail("Email@gmail.com");
    }

    //Esse teste está funcionando porem estou confuso em  o que usar para validar o teste quando retornar uma exception --> TalesRg
    @Test
    public void deveLancarErroAoValidarEmailQuandoHouverEmailCadastrado() {
        Usuario usuario = Usuario.builder().matricula("211041295").nome("fulano").email("Email@gmail.com").senha("1234").build();

        repository.save(usuario);

        boolean result = service.validarEmail("Email@gmail.com");

    }

    @Test
    public void dadoEmailNaoCadastradoAoAutenticarDeveRetornarErroUsuarioNaoCadastrado() {
        // cenario
        Mockito.when(repository.findByEmail(Mockito.eq("usuario@.com"))).thenReturn(Optional.empty());


        assertThatThrownBy(() -> service.autenticar("usuario@.com", "532"))
                .isInstanceOf(ErroDeAutenticacao.class)
                .hasMessage("Usúario não encontrado para o e-mail informado");
    }


}
