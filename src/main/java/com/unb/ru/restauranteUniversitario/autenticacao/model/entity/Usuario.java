package com.unb.ru.restauranteUniversitario.autenticacao.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario",schema = "restauranteuniversitario")
public class Usuario {
        @Id
        @Column(name = "id")
        private int id;

        @Column(name = "matricula")
        private String matricula;

        @Column(name = "nome")
        private String nome;

        @Column(name = "email")
        private String email;

        @Column(name = "senha")
        private String senha;

}
