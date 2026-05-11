# 🏥 Sistema de Clínica

Olá! Esse é um dos meus primeiros projetos completos em Java — um sistema de gerenciamento de clínica com interface gráfica e banco de dados real.

Desenvolvi esse projeto para praticar e consolidar meus conhecimentos em Java, JDBC e Java Swing, indo além da teoria e colocando a mão na massa em algo que pudesse usar de verdade.

---

## 💻 Tecnologias utilizadas

- **Java 21** — linguagem principal
- **Java Swing** — interface gráfica
- **MySQL** — banco de dados
- **JDBC** — conexão Java com o banco
- **Maven** — gerenciador de dependências

---

## ⚙️ Funcionalidades

- ✅ Cadastro de pacientes
- ✅ Listagem de pacientes em tabela
- ✅ Atualização de dados do paciente
- ✅ Exclusão de pacientes
- 🚧 Agendamento de consultas (em desenvolvimento)

---

## 🗄️ Estrutura do projeto

```
src/
├── conexao/   → Conexão com o banco de dados
├── modelo/    → Classes que representam os dados (Paciente, Consulta)
├── dao/       → Comunicação com o banco (CRUD)
└── tela/      → Interface gráfica (Java Swing)
```

---

## 🚀 Como executar

1. Clone o repositório
```bash
git clone https://github.com/SantoBug/clinica-sistema.git
```

2. Crie o banco de dados no MySQL
```sql
CREATE DATABASE clinica;
USE clinica;

CREATE TABLE paciente (
    id        INT PRIMARY KEY AUTO_INCREMENT,
    nome      VARCHAR(100) NOT NULL,
    cpf       VARCHAR(14)  NOT NULL UNIQUE,
    telefone  VARCHAR(15),
    email     VARCHAR(100)
);
```

3. Configure a conexão em `ConexaoBanco.java`
```java
private static final String SENHA = "sua_senha_aqui";
```

4. Execute o `Main.java` e o sistema vai abrir!

---

## 📚 O que aprendi com esse projeto

- Como conectar Java a um banco de dados MySQL usando JDBC
- Como criar interfaces gráficas com Java Swing
- Como organizar o código usando o padrão DAO
- Como versionar um projeto com Git e GitHub

---

## 🔗 Projetos relacionados

- [clinica-api](https://github.com/SantoBug/clinica-api) — versão API REST com Spring Boot

---

## 👨‍💻 Autor

Feito com dedicação por **Douglas Tavares** 🚀

Estou em constante aprendizado e esse projeto faz parte da minha jornada como desenvolvedor. Fique à vontade para explorar o código!
