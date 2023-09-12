package main.java.br.com.alura.forum.dao;

import main.java.br.com.alura.forum.model.Reserva;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    private Connection connection;

    public ReservaDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Reserva reserva) {
        try {
            String sql = "INSERT INTO reservas (data_entrada, data_saida, valor, forma_pagamento) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstm.setDate(1, reserva.getDataEntrada());
                pstm.setDate(2, reserva.getDataSaida());
                pstm.setString(3, reserva.getValor());
                pstm.setString(4, reserva.getFormaDePagamento());

                pstm.executeUpdate();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        reserva.setId(rst.getLong(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Reserva> buscar() {
        List<Reserva> reservas = new ArrayList<>();
        try {
            String sql = "SELECT id, data_entrada, data_saida, valor, forma_pagamento FROM reservas";
            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();

                transformarResultSetEmReserva(reservas, pstm);
            }
            return reservas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Reserva> buscarId(String id) {
        List<Reserva> reservas = new ArrayList<>();
        try {
            String sql = "SELECT id, data_entrada, data_saida, valor, forma_pagamento FROM reservas WHERE id = ?";
            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, id);
                pstm.execute();

                transformarResultSetEmReserva(reservas, pstm);
            }
            return reservas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletar(Long id) {
        try(PreparedStatement pstm = connection.prepareStatement("DELETE FROM reservas WHERE id = ?")) {
            pstm.setLong(1, id);
            pstm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizar(Date dataEntrada, Date dataSaida, String valor, String formaDePagamento, Long id) {
        try (PreparedStatement pstm = connection.prepareStatement("UPDATE reservas SET data_entrada = ?, data_saida = ?, valor = ?, forma_pagamento = ? WHERE id = ?")) {
            pstm.setDate(1,dataEntrada);
            pstm.setDate(2, dataSaida);
            pstm.setString(3,valor);
            pstm.setString(4, formaDePagamento);
            pstm.setLong(5, id);
            pstm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void transformarResultSetEmReserva(List<Reserva> reservas, PreparedStatement pstm) throws SQLException {
        try (ResultSet rst = pstm.getResultSet()) {
            while (rst.next()) {
                Reserva reserva = new Reserva(rst.getLong(1), rst.getDate(2), rst.getDate(3), rst.getString(4), rst.getString(5));

                reservas.add(reserva);
            }
        }
    }
}
