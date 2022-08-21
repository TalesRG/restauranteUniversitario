package com.unb.ru.restauranteUniversitario.autenticacao.model.repository;

import com.unb.ru.restauranteUniversitario.autenticacao.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,String> {
    boolean existsByEmail(String email);

    boolean existsByMatricula(String matricula);
    Optional<Usuario> findByEmail(String email);

}
