package org.example.conexao;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/clinica?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String SENHA = "1472";

    public static Connection conectar () {
        try {
            return DriverManager.getConnection(URL,USUARIO,SENHA);

        }catch (SQLException e){
            System.out.println("Erro ao conectar!!!" + e.getMessage());
            return null;
        }
    }
}
