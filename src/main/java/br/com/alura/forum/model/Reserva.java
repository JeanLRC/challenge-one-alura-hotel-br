package main.java.br.com.alura.forum.model;

import java.sql.Date;

public class Reserva {

    private Long id;
    private Date dataEntrada;
    private Date dataSaida;
    private String valor;
    private String formaDePagamento;

    public Reserva(Long id, Date dataEntrada, Date dataSaida, String valor, String formaDePagamento) {
        this.id = id;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.valor = valor;
        this.formaDePagamento = formaDePagamento;
    }

    public Reserva(Date dataEntrada, Date dataSaida, String valor, String formaDePagamento) {
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.valor = valor;
        this.formaDePagamento = formaDePagamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public String getValor() {
        return valor;
    }

    public String getFormaDePagamento() {
        return formaDePagamento;
    }
}
