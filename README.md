# 📱 Loja Virtual - Cadastro de Produtos

## 📌 Descrição
Este aplicativo Android foi desenvolvido para permitir o cadastro e a visualização de produtos em uma loja virtual, substituindo o controle manual em cadernos por um sistema digital simples e eficiente.

O app foi desenvolvido como atividade acadêmica, utilizando boas práticas de programação e armazenamento local com banco de dados Room.

---

## 🎯 Objetivo
Criar um aplicativo funcional com:
- Cadastro de produtos
- Listagem de produtos cadastrados
- Persistência de dados local
- Validação de entradas do usuário

---

## ⚙️ Funcionalidades

### 📝 Cadastro de Produto
O usuário pode inserir:
- Nome do produto
- Código do produto (alfanumérico)
- Preço (em reais)
- Quantidade em estoque

### ✔️ Validações
- Todos os campos são obrigatórios
- Preço deve ser maior que zero
- Preço aceita até 2 casas decimais
- Quantidade deve ser número inteiro positivo

### 📋 Listagem de Produtos
- Exibe Nome, Código e Preço
- Utiliza RecyclerView
- Atualiza conforme novos produtos são cadastrados

### 🔄 Navegação
- Botão para acessar lista
- Botão para retornar ao cadastro

---

## 🧱 Tecnologias Utilizadas
- Java
- Android Studio
- Room Database
- RecyclerView
- XML (Layouts)

---

## 🗂 Estrutura do Projeto

```
com.heitor.prova
│
├── CadastroActivity.java      # Tela de cadastro
├── ListaProdutosActivity.java # Tela de listagem
├── Produto.java               # Entidade (Room)
├── ProdutoDao.java            # Interface DAO
├── ProdutoDatabase.java       # Banco de dados
└── ProdutoAdapter.java        # Adapter RecyclerView
```

---

## 💾 Banco de Dados (Room)

O app utiliza Room para persistência local:

- Entity: Produto
- DAO: ProdutoDao
- Database: ProdutoDatabase

Operações:
- Inserção de produtos
- Consulta de todos os produtos

---

## ▶️ Como Executar

1. Abra o projeto no Android Studio
2. Aguarde o Gradle sincronizar
3. Execute em um emulador ou dispositivo físico

---

## 📱 Fluxo do Aplicativo

1. Usuário preenche os dados
2. Clica em "Salvar Produto"
3. Produto é validado e armazenado
4. Usuário acessa a lista
5. Visualiza os produtos cadastrados
6. Pode retornar para cadastrar mais

---

## 📌 Observações
- O armazenamento é local (não usa internet)
- Interface simples e funcional
- Código organizado em camadas (Activity, DAO, Database)

---

## 👨‍💻 Autor
Heitor

---

## 📄 Licença
Projeto acadêmico desenvolvido para fins educacionais.
