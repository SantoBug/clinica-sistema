package org.example.tela;

import  org.example.conexao.ConexaoBanco;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class TelaConsulta extends JFrame {

    //LISTA SUSPENSA COM OS PACIENTES
    private JComboBox<String> cbPaciente = new JComboBox<>();

    private JTextField txtMedico    = new JTextField(20);
    private JTextField txtData      = new JTextField(20);
    private JTextField txtObservacao = new JTextField(40);

    private JButton btnAgendar      = new JButton("Agendar");
    private JButton btnDeletar      = new JButton("Cancelar Consulta");
    private JButton btnLimpar       = new JButton("Limpar");

    private DefaultTableModel modelo = new DefaultTableModel(
            new String[]{"ID", "Paciente", "Data", "Médico","Observacao"}, 0
    );
    private JTable tabela = new JTable(modelo);

    private int idSelecionado = -1;

    private java.util.List<Integer> idsPacientes = new java.util.ArrayList<>();



    // CONSTRUTOR
    public TelaConsulta() {
        setTitle("Consultas");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //FORMULARIO
        JPanel painelForm = new JPanel(new GridLayout(5, 2, 5, 5));
        painelForm.setBorder(BorderFactory.createTitledBorder("Dados da Consulta"));

        painelForm.add (new JLabel("Paciente"));        painelForm.add(cbPaciente);
        painelForm.add (new JLabel("Medico"));          painelForm.add(txtMedico);
        painelForm.add (new JLabel("Data"));            painelForm.add(txtData);
        painelForm.add (new JLabel("Observacao"));      painelForm.add(txtObservacao);

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnAgendar);
        painelBotoes.add(btnDeletar);
        painelBotoes.add(btnLimpar);

        // PAINEL SUPERIOR

        JPanel painelSuperior = new JPanel(new BorderLayout());
        painelSuperior.add(painelForm, BorderLayout.CENTER);
        painelSuperior.add(painelBotoes, BorderLayout.SOUTH);

        //TABELA

        JScrollPane scroll = new JScrollPane(tabela);

        add(painelSuperior, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        btnAgendar.addActionListener(e -> agendar());
        btnDeletar.addActionListener(e -> deletar());
        btnLimpar.addActionListener(e -> limpar());

        tabela.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selecionarLinha();
            }
        });
        //CARREGAR DADOS
        carregarPacientes();
        carregarTabela();

        setVisible(true);
    }

    private void carregarPacientes(){
        cbPaciente.removeAllItems();
        idsPacientes.clear();

        String sql = "SELECT id, nome FROM paciente";

        try (Connection conn = ConexaoBanco.conectar();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                idsPacientes.add(rs.getInt("id"));
                cbPaciente.addItem(rs.getString("nome"));
            }

        }catch (SQLException e) {
            System.out.println("ERRO ao carregar pacientes:" + e.getMessage());
        }
    }

    private void carregarTabela() {
        modelo.setRowCount(0);

        String sql =    "SELECT consulta.id, paciente.nome " +
                        "consulta.data_consulta, consulta.medico, consulta.observacao " +
                        "FROM consulta " +
                        "JOIN paciente ON consulta.paciente_id = paciente.id";

        try (Connection conn = ConexaoBanco.conectar();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                modelo.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("nome" ),
                        rs.getString("data_consulta"),
                        rs.getString("medico"),
                        rs.getString("observacao"),
                });
            }
        }catch (SQLException e) {
            System.out.println("ERRO ao carregar consults: " + e.getMessage());
        }
    }

    private void agendar() {

        int indice = cbPaciente.getSelectedIndex();

        if (indice == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um paciente!");
            return;
        }

        int pacienteId = idsPacientes.get(indice);

        String sql =    "INSERT INTO consulta (paciente_id, data_consulta, medico, observacao) " +
                        "VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoBanco.conectar();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, pacienteId);
            ps.setString(2, txtData.getText());
            ps.setString(3, txtMedico.getText());
            ps.setString(4, txtObservacao.getText());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Consulta agendada!");
            carregarTabela();
            limpar();
        }catch (SQLException e) {
            System.out.println("ERRO ao agendar " + e.getMessage());
        }
    }
    // CANCELAR CONSULTA
    private void deletar() {
        if (idSelecionado == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma consulta na tabela!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Deseja cancelar esta consulta?", "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM consulta WHERE id = ?";

            try (Connection conn = ConexaoBanco.conectar();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, idSelecionado);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Consulta cancelada!");
                carregarTabela();
                limpar();

            } catch (SQLException e) {
                System.out.println("Erro ao cancelar: " + e.getMessage());
            }
        }
    }
    // SELECIONAR LINHA DA TABELA
    private void selecionarLinha() {
        int linha = tabela.getSelectedRow();
        if (linha >= 0) {
            idSelecionado = (int) modelo.getValueAt(linha, 0);

            // seleciona o paciente correto no ComboBox
            String nomePaciente = (String) modelo.getValueAt(linha, 1);
            for (int i = 0; i < cbPaciente.getItemCount(); i++) {
                if (cbPaciente.getItemAt(i).equals(nomePaciente)) {
                    cbPaciente.setSelectedIndex(i);
                    break;
                }
            }

            txtData.setText((String) modelo.getValueAt(linha, 2));
            txtMedico.setText((String) modelo.getValueAt(linha, 3));
            txtObservacao.setText((String) modelo.getValueAt(linha, 4));
        }
    }

    // LIMPAR CAMPOS
    private void limpar() {
        cbPaciente.setSelectedIndex(0);
        txtMedico.setText("");
        txtData.setText("");
        txtObservacao.setText("");
        idSelecionado = -1;
    }

}
