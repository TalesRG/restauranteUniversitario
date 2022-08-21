package com.unb.ru.restauranteUniversitario.autenticacao.api.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {
    private String email;
    private String nome;
    private String senha;
    private String matricula;
}
