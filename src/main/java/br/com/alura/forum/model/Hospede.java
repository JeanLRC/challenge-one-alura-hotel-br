package main.java.br.com.alura.forum.model;

import java.sql.Date;

public class Hospede {

    private Long id;
    private String nome;
    private String sobrenome;
    private Date dataNascimento;
    private String nacionalidade;
    private String telefone;
    private Long idReserva;

    public Hospede(String nome, String sobrenome, Date dataNascimento, String nacionalidade, String telefone, Long idReserva) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.nacionalidade = nacionalidade;
        this.telefone = telefone;
        this.idReserva = idReserva;
    }

    public Hospede(Long id, String nome, String sobrenome, Date dataNascimento, String nacionalidade, String telefone, Long idReserva) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.nacionalidade = nacionalidade;
        this.telefone = telefone;
        this.idReserva = idReserva;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public Long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
    }
}
