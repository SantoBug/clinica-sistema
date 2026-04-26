🏥 Sistema de Clínica
Olá! Esse é um dos meus primeiros projetos completos em Java — um sistema de gerenciamento de clínica com interface gráfica e banco de dados real.
Desenvolvi esse projeto para praticar e consolidar meus conhecimentos em Java, JDBC e Java Swing, indo além da teoria e colocando a mão na massa em algo que pudesse usar de verdade.

💻 Tecnologias utilizadas

Java 21 — linguagem principal<br>
Java Swing — interface gráfica<br>
MySQL — banco de dados<br>
JDBC — conexão Java com o banco<br>
Maven — gerenciador de dependências<br>


⚙️ Funcionalidades

✅ Cadastro de pacientes
✅ Listagem de pacientes em tabela
✅ Atualização de dados do paciente
✅ Exclusão de pacientes
🚧 Agendamento de consultas (em desenvolvimento)


🗄️ Estrutura do projeto
src/<br>
├── conexao/        → Conexão com o banco de dados<br>
├── modelo/         → Classes que representam os dados (Paciente, Consulta)<br>
├── dao/            → Comunicação com o banco (CRUD)<br>
└── tela/           → Interface gráfica (Java Swing)<br>

🚀 Como executar

Clone o repositório

bashgit clone https://github.com/seu-usuario/clinica-sistema.git

Crie o banco de dados no MySQL

sqlCREATE DATABASE clinica;
USE clinica;

CREATE TABLE paciente (<br>
    id        INT PRIMARY KEY AUTO_INCREMENT,<br>
    nome      VARCHAR(100) NOT NULL,<br>
    cpf       VARCHAR(14)  NOT NULL UNIQUE,<br>
    telefone  VARCHAR(15),<br>
    email     VARCHAR(100)<br>
);

Configure a conexão em ConexaoBanco.java

javaprivate static final String SENHA = "sua_senha_aqui";

Execute o Main.java e o sistema vai abrir!


📚 O que aprendi com esse projeto

Como conectar Java a um banco de dados MySQL usando JDBC
Como criar interfaces gráficas com Java Swing
Como organizar o código usando o padrão DAO
Como versionar um projeto com Git e GitHub


👨‍💻 Autor
Feito com dedicação por Douglas Tavares 🚀
Estou em constante aprendizado e esse projeto faz parte da minha jornada como desenvolvedor. Fique à vontade para explorar o código!
