package org.example.tela;

import org.example.dao.PacienteDAO;
import org.example.modelo.Paciente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;


public class TelaPaciente  extends JFrame{

    //CAMPOS DE TEXTO

    private JTextField txtNome      = new JTextField(20);
    private JTextField txtCpf       = new JTextField(20);
    private JTextField txtTelefone  = new JTextField(20);
    private JTextField txtEmail     = new JTextField(20);

    // BOTOES
    private JButton btnCadastrar    = new JButton("Cadastrar");
    private JButton btnAtualizar    = new JButton("Atualizar");
    private JButton btnDeletar      = new JButton("Deletar");
    private JButton btnLimpar       = new JButton("Limpar");

    //TABELA
    private DefaultTableModel modelo = new DefaultTableModel(
            new String[] {"ID", "Nome", "CPF", "Telefone", "Email"}, 0
    );
    private JTable tabela = new JTable(modelo);

    //DAO
    private PacienteDAO dao = new PacienteDAO();
    private int idSelecionado = -1;

    public TelaPaciente() {
        setTitle("🏥 Sistema de Clínica — Pacientes");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // PAINEL DO FORMULARIO
        JPanel painelForm = new JPanel(new GridLayout(5, 2, 5, 5));
        painelForm.setBorder(BorderFactory.createTitledBorder("Dados do Paciente"));
        painelForm.add(new JLabel("Nome:"));        painelForm.add(txtNome);
        painelForm.add(new JLabel("CPF:"));         painelForm.add(txtCpf);
        painelForm.add(new JLabel("Telefone:"));    painelForm.add(txtTelefone);
        painelForm.add(new JLabel("Email:"));       painelForm.add(txtEmail);

        //PAINEL DOS BOTOES
        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnDeletar);
        painelBotoes.add(btnLimpar);

        //PAINEL SUPERIOR
        JPanel painelSuperior = new JPanel(new BorderLayout());
        painelSuperior.add(painelForm, BorderLayout.CENTER);
        painelSuperior.add(painelBotoes, BorderLayout.SOUTH);

        //TABELA
        JScrollPane scroll = new JScrollPane(tabela);

        add(painelSuperior, BorderLayout.NORTH);
        add(scroll,BorderLayout.CENTER);

        //ACOES DOS BOTOES
        btnCadastrar.addActionListener(e -> cadastrar());
        btnAtualizar.addActionListener(e -> atualizar());
        btnDeletar.addActionListener(e -> deletar());
        btnLimpar.addActionListener(e -> limpar());

        //CLIQUE NA TABELA
        tabela.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selecionarLinha();
            }
        });
        carregarTabela();
        setVisible(true);
    }

    //CADASTRAR

    public void cadastrar() {
        Paciente p = new Paciente(0,
                txtNome.getText(),
                txtCpf.getText(),
                txtTelefone.getText(),
                txtEmail.getText()
        );
        dao.inserir(p);
        carregarTabela();
        limpar();
    }
    ///METODO ATUALIZAR

    public void atualizar (){
        if (idSelecionado == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um paciente na tabela!");
            return;
        }
        Paciente p = new Paciente(idSelecionado,
                txtNome.getText(),
                txtCpf.getText(),
                txtTelefone.getText(),
                txtEmail.getText()
        );
        dao.atualizar(p);
        carregarTabela();
        limpar();
    }

    private void deletar() {
        System.out.println("ID selecionado: " + idSelecionado);
        if (idSelecionado == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um paciente na tabela!");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this,
                "Deseja deletar este paciente?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dao.deletar(idSelecionado);
            carregarTabela();
            limpar();
        }
    }

    // CARREGAR TABELA
    private void carregarTabela () {
        modelo.setRowCount(0);
        List<Paciente> lista = dao.buscarTodos();
        for (Paciente p : lista) {
            modelo.addRow( new Object[] {
                    p.getId(), p.getNome(), p.getCpf(),
                    p.getTelefone(), p.getEmail()
            });
        }
    }

    //SELECIONAR LINHA
    private void selecionarLinha(){
        int linha = tabela.getSelectedRow();
        if(linha >= 0) {
            idSelecionado = (int)  modelo.getValueAt(linha, 0);
            txtNome.setText((String) modelo.getValueAt(linha, 1));
            txtCpf.setText((String) modelo.getValueAt(linha, 2));
            txtTelefone.setText((String) modelo.getValueAt(linha,3));
            txtEmail.setText((String) modelo.getValueAt(linha, 4));
        }
    }

    //LIMPAR CAMPOS
    private void limpar() {
        txtNome.setText("");
        txtCpf.setText("");
        txtTelefone.setText("");
        txtEmail.setText("");
        idSelecionado = -1;
    }
}
