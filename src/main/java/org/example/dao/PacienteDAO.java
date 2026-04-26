package org.example.dao;

import org.example.conexao.ConexaoBanco;
import org.example.modelo.Paciente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    //CADASTRO DO PACIENTE
    public void inserir(Paciente p) {
        String sql = "INSERT INTO paciente (nome, cpf, telefone, email ) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoBanco.conectar();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getNome());
            ps.setString(2, p.getCpf());
            ps.setString(3, p.getTelefone());
            ps.setString(4, p.getEmail());
            ps.executeUpdate();

            System.out.println("Paciente Cadastrado!");

        } catch (SQLException e) {
            System.out.println("ERRO ao cadastrar: " + e.getMessage());
        }
    }


    //READ --- BUSCAR TODOS OS PACIENTES

    public List<Paciente> buscarTodos () {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM paciente";

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Paciente p = new Paciente (
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        rs.getString("email")
                );
                lista.add(p);
            }
        }catch (SQLException e) {
            System.out.println("ERRO ao buscar: " + e.getMessage());
        }
        return lista;
    }


    //UPDATE -- ATUALIZAR PACIENTE

    public void atualizar(Paciente p ) {
        String sql = "UPDATE paciente SET nome=?, telefone=?, email=? WHERE id=?";

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getNome());
            ps.setString(2, p.getTelefone());
            ps.setString(3, p.getEmail());
            ps.setInt(4, p.getId());
            ps.executeUpdate();

            System.out.println("Paciente atualizado!");
        } catch (SQLException e) {
            System.out.println("ERRO ao atualizar:" + e.getMessage());
        }
    }

    //DELETE -- DELETAR PACIENTE

    public void deletar (int id) {
        String sql = "DELETE FROM paciente WHERE id=?";

        try(Connection conn = ConexaoBanco.conectar();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Paciente deletado!");

        } catch (SQLException e) {
            System.out.println("ERRO ao deletar: " + e.getMessage());
        }
    }
}
