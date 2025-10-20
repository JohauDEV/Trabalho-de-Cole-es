# 🎓 Trabalho de POO — Coleções Java

## 📘 Descrição Geral
Este projeto foi desenvolvido como parte de um **trabalho de Programação Orientada a Objetos (POO)** com o objetivo de praticar o uso das coleções **`List`**, **`Set`** e **`Map`** da linguagem Java.

O sistema simula o **controle de estudantes, disciplinas e notas** de uma escola de ensino médio, permitindo armazenar, organizar e associar dados de forma eficiente.  

O código está organizado em múltiplas classes que representam as entidades e operações principais do problema.

---

## 🧠 Estrutura e Conceitos

### 🅰️ Parte A — List (Lista de Estudantes)
Gerencia estudantes usando `ArrayList`.

**Funcionalidades:**
- Adicionar, remover e buscar estudantes.
- Ordenar alfabeticamente por nome.
- Retornar estudantes cujo nome contém uma substring (case-insensitive).

**Classe:** `ListaEstudantes`

---

### 🅱️ Parte B — Set (Cadastro de Disciplinas)
Controla disciplinas únicas usando `LinkedHashSet`.

**Funcionalidades:**
- Adicionar disciplinas (evitando duplicatas).
- Remover disciplinas.
- Listar todas as disciplinas na ordem de inserção.
- Detectar duplicatas ao importar dados.

**Classe:** `CadastroDisciplinas`

---

### 🅲 Parte C — Map (Matrículas e Notas)
Associa estudantes às suas disciplinas e notas utilizando `HashMap<Integer, List<Matricula>>`.

**Funcionalidades:**
- Adicionar e remover matrículas.
- Consultar notas e médias de estudantes.
- Calcular médias por disciplina.
- Retornar os melhores alunos (Top N).

**Classe:** `HistoricoNotas`

---

### 🅳 Parte D — Integração
Integra todas as coleções e exibe relatórios completos.

**Funcionalidades:**
- Exibir estudantes (ordem de cadastro e ordenados por nome).
- Exibir disciplinas cadastradas.
- Exibir matrículas, notas e médias.
- Calcular médias por disciplina.
- Mostrar top 3 alunos e aprovados.

**Classe:** `Main`

---

## 🧱 Estrutura de Arquivos

