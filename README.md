# Trabalho de POO — Coleções Java

## Integrantes do Grupo
- João Ricardo de Brito Melo Santos

> Obs.: substitua os nomes dos integrantes 2 e 3 se necessário. Máximo de 3 integrantes.

---

## Breve descrição do projeto
Este projeto implementa um pequeno sistema escolar em Java que gerencia **estudantes**, **disciplinas** e **matrículas/notas** usando as coleções da linguagem (List, Set e Map).  
O programa organiza os dados em classes separadas (Estudante, Disciplina, Matricula, ListaEstudantes, CadastroDisciplinas, HistoricoNotas) e a classe `Main` integra tudo mostrando relatórios como: lista de estudantes (ordenada e por cadastro), disciplinas, matrículas por aluno, médias por aluno e por disciplina, top N alunos e listas de aprovados/reprovados conforme critérios.

---

## Justificativa das escolhas de coleções e implementações

### `List` — `ArrayList` (em `ListaEstudantes`)
- **Por que List:** precisamos manter uma coleção de estudantes onde a ordem de cadastro importa, facilitar iteração, buscas e ordenação.
- **Implementação (`ArrayList`):** oferece acesso por índice eficiente e boa performance para iteração e ordenação (uso de `Collections.sort`/`Comparator`). Para este trabalho a maioria das operações são leituras e ordenações, portanto `ArrayList` é apropriado.

### `Set` — `LinkedHashSet` (em `CadastroDisciplinas`)
- **Por que Set:** garantir que não haja disciplinas duplicadas (mesmo código não pode existir duas vezes).
- **Implementação (`LinkedHashSet`):** evita duplicatas e mantém a **ordem de inserção**, que é útil para exibir as disciplinas na sequência em que foram cadastradas (requisito do enunciado). `HashSet` descartaria a ordem, `TreeSet` ordenaria por chave (o que também é útil, mas não preserva inserção).

### `Map` — `HashMap<Integer, List<Matricula>>` (em `HistoricoNotas`)
- **Por que Map:** precisamos associar cada estudante (pela `id`) às suas matrículas/ notas — ou seja, uma estrutura chave → valor.
- **Implementação (`HashMap`):** fornece acesso O(1) esperado por id do estudante, o que é eficiente para consultas, inserções e remoções. O valor é uma `List<Matricula>` (lista de objetos `Matricula`) para armazenar múltiplas disciplinas e notas por estudante.

---

## Como executar o programa (passo a passo simples) e gerar `output.txt`

> Presume-se que o diretório contenha os arquivos `.java` do projeto:
> `Estudante.java`, `Disciplina.java`, `Matricula.java`, `ListaEstudantes.java`, `CadastroDisciplinas.java`, `HistoricoNotas.java` e `Main.java`.

1. Abra um terminal na pasta do projeto.

2. Compile todos os arquivos `.java`:
```bash
javac *.java
