package org.example.modelo;

public class Paciente {

    private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;

    //CONSTRUTOR

    public Paciente(int id, String nome, String cpf, String telefone, String email ) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
    }

    //GETTERS

    public int getId()          {return id; }
    public String getNome()     {return nome; }
    public String getCpf()      {return cpf; }
    public String getTelefone() {return telefone; }
    public String getEmail()    {return email; }

    //SETTERS

    public void setNome(String nome)            {this.nome = nome; }
    public void setCpf(String cpf)              {this.cpf = cpf; }
    public void setTelefone(String telefone)    {this.telefone = telefone; }
    public void setEmail(String email)          {this.email = email; }


    //ToString

    @Override
    public String toString() {
        return nome + "| CPF: " + cpf + "| TEL: " + telefone;
    }
}
