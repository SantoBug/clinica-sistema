package org.example.tela;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal (){
        setTitle ("🏥 Sistema de Clínica");
        setSize(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //TITULO

        JLabel titulo = new JLabel("🏥 Sistema de Clínica", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        //BOTOES
        JButton btnPacientes = new JButton("👤 Gerenciar Pacientes");
        JButton btnConsultas = new JButton("📅 Gerenciar Consultas");

        btnPacientes.setPreferredSize(new Dimension(250, 0));
        btnConsultas.setPreferredSize(new Dimension(250, 0));


        btnPacientes.setFont(new Font("Arial", Font.PLAIN, 14));
        btnConsultas.setFont(new Font("Arial", Font.PLAIN, 14));

        //PAINEL DOS BOTOES
        JPanel painelBotoes = new JPanel(new GridLayout(2, 1, 10, 10));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(10,50,30,50));
        painelBotoes.add(btnPacientes);
        painelBotoes.add(btnConsultas);

        add(titulo, BorderLayout.NORTH);
        add(painelBotoes, BorderLayout.CENTER);


        //Acoes
        btnPacientes.addActionListener(e -> abrirPacientes());
        btnConsultas.addActionListener(e -> abrirConsultas());

        setVisible(true);
    }

    private void abrirPacientes() {
        setVisible(false); // esconde o menu
        TelaPaciente tela = new TelaPaciente();

        //QUANDO FECHAR A TELA DE PACIENTES, VOLTA PRO MENU
        tela.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed (java.awt.event.WindowEvent e) {
                setVisible(true); // MOSTRA O MENU DE NOVO
            }
        });
    }

    // DEPOIS — abre a tela de consultas!
    private void abrirConsultas() {
        setVisible(false);
        TelaConsulta tela = new TelaConsulta();

        tela.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent e) {
                setVisible(true);
            }
        });
    }
}
