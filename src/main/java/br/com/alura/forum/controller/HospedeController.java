package main.java.br.com.alura.forum.controller;

import main.java.br.com.alura.forum.dao.HospedeDAO;
import main.java.br.com.alura.forum.jdbc.factory.ConnectionFactory;
import main.java.br.com.alura.forum.model.Hospede;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class HospedeController {

    private HospedeDAO hospedeDAO;

    public HospedeController() {
        Connection connection = new ConnectionFactory().recuperarConexao();
        this.hospedeDAO = new HospedeDAO(connection);
    }

    public void salvar(Hospede hospede) {
        this.hospedeDAO.salvar(hospede);
    }

    public List<Hospede> buscar() {
        return this.hospedeDAO.listarHospedes();
    }

    public List<Hospede> buscarId(String id) {
        return this.hospedeDAO.buscarId(id);
    }

    public void atualizar(String nome, String sobrenome, Date dataNascimento, String nacionalidade, String telefone, Long idReserva, Long id) {
        this.hospedeDAO.atualizar(nome, sobrenome, dataNascimento, nacionalidade, telefone, idReserva, id);
    }

    public void deletar(Long id) {
        this.hospedeDAO.deletar(id);
    }
}
