package br.com.zup.edu.personmanager.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "pessoas")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    @CPF
    private String cpf;

    private String apelido;

    @Column(nullable = false)
    @PastOrPresent
    private LocalDate dataNascimento;

    /**
     * @deprecated Construtor de uso exclusivo do Hibernate
     */
    @Deprecated
    public Pessoa() {}

    public Pessoa(String nome, @CPF String cpf, String apelido,
                  @PastOrPresent LocalDate dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.apelido = apelido;
        this.dataNascimento = dataNascimento;
    }

    public Long getId() {
        return id;
    }

    public Long idade() {
        return ChronoUnit.YEARS.between(dataNascimento, LocalDate.now());
    }

}
