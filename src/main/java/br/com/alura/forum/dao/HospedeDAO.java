package main.java.br.com.alura.forum.dao;

import main.java.br.com.alura.forum.model.Hospede;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HospedeDAO {

    private Connection connection;

    public HospedeDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Hospede hospede) {
        try {
            String sql = "INSERT INTO hospedes (nome, sobrenome, data_nascimento, nacionalidade, telefone, id_reserva) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstm.setString(1, hospede.getNome());
                pstm.setString(2, hospede.getSobrenome());
                pstm.setDate(3, hospede.getDataNascimento());
                pstm.setString(4, hospede.getNacionalidade());
                pstm.setString(5, hospede.getTelefone());
                pstm.setLong(6, hospede.getIdReserva());
                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        hospede.setId(rst.getLong(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Hospede> listarHospedes() {
        List<Hospede> hospedes = new ArrayList<>();
        try {
            String sql = "SELECT id, nome, sobrenome, data_nascimento, nacionalidade, telefone, id_reserva FROM hospedes";
            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();

                transformarResultSetEmHospede(hospedes, pstm);
            }
            return hospedes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Hospede> buscarId(String id) {
        List<Hospede> hospedes = new ArrayList<>();

        try {
            String sql = "SELECT id, nome, sobrenome, data_nascimento, nacionalidade, telefone, id_reserva FROM hospedes WHERE id = ?";
            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, id);
                pstm.execute();

                transformarResultSetEmHospede(hospedes, pstm);
            }
            return hospedes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizar(String nome, String sobrenome, Date dataNascimento, String nacionalidade, String telefone, Long idReserva, Long id) {
        try (PreparedStatement pstm = connection.prepareStatement("UPDATE hospedes SET nome = ?, sobrenome = ?, data_nascimento = ?, nacionalidade = ?, telefone = ?, id_reserva = ? WHERE id = ?")) {
            pstm.setString(1, nome);
            pstm.setString(2, sobrenome);
            pstm.setDate(3, dataNascimento);
            pstm.setString(4, nacionalidade);
            pstm.setString(5, telefone);
            pstm.setLong(6, idReserva);
            pstm.setLong(7, id);
            pstm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletar(Long id) {
        try (PreparedStatement pstm = connection.prepareStatement("DELETE FROM hospedes WHERE id = ?")) {
            pstm.setLong(1, id);
            pstm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void transformarResultSetEmHospede(List<Hospede> hospedes, PreparedStatement pstm) throws SQLException {
        try (ResultSet rst = pstm.getResultSet()) {
            while (rst.next()) {
                Hospede hospede = new Hospede(rst.getLong(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getDate(4),
                        rst.getString(5),
                        rst.getString(6),
                        rst.getLong(7)
                );
                hospedes.add(hospede);
            }
        }
    }
}
