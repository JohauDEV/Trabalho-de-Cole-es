import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // 1) criar estruturas
        ListaEstudantes lista = new ListaEstudantes();
        CadastroDisciplinas cadastro = new CadastroDisciplinas();
        HistoricoNotas historico = new HistoricoNotas();

        // 2) carregar dataset (exemplo do enunciado)
        lista.adicionarEstudante(new Estudante(1, "Ana"));
        lista.adicionarEstudante(new Estudante(2, "Bruno"));
        lista.adicionarEstudante(new Estudante(3, "Carla"));
        lista.adicionarEstudante(new Estudante(4, "Diego"));
        lista.adicionarEstudante(new Estudante(5, "Elisa"));

        cadastro.adicionarDisciplina(new Disciplina("MAT101", "Matemática"));
        cadastro.adicionarDisciplina(new Disciplina("PRG201", "Programação"));
        cadastro.adicionarDisciplina(new Disciplina("BD301", "Banco de Dados"));
        cadastro.adicionarDisciplina(new Disciplina("EDF110", "Educação Física"));

        // matrículas/ notas
        historico.adicionarMatricula(1, "MAT101", 8.5);
        historico.adicionarMatricula(1, "PRG201", 9.0);

        historico.adicionarMatricula(2, "PRG201", 7.0);
        // adicionando MAT101 para Bruno como exemplo (no enunciado exemplo tinha MAT101 com 5.0 em saída)
        historico.adicionarMatricula(2, "MAT101", 5.0);

        historico.adicionarMatricula(3, "BD301", 6.5);
        historico.adicionarMatricula(3, "MAT101", 7.5);

        historico.adicionarMatricula(4, "PRG201", 8.0);

        historico.adicionarMatricula(5, "EDF110", 10.0);

        // 3) exibir estudantes (ordem cadastro)
        System.out.println("== Lista de Estudantes (ordem de cadastro) ==\n");
        for (Estudante e : lista.obterLista()) {
            System.out.println(e);
        }
        System.out.println();

        // 4) exibir estudantes ordenados por nome
        lista.ordenarEstudantesPorNome();
        System.out.println("== Lista de Estudantes (ordenada) ==\n");
        String ordenados = lista.obterLista().stream()
                .map(Estudante::getNome)
                .collect(Collectors.joining(", "));
        System.out.println(ordenados + "\n");

        // 5) exibir disciplinas (manter ordem de inserção)
        System.out.println("== Disciplinas (inserção) ==\n");
        List<Disciplina> disciplinasInsercao = cadastro.obterTodasDisciplinas();
        System.out.println(disciplinasInsercao.stream().map(Disciplina::getCodigo).collect(Collectors.joining(", ")) + "\n");

        // 6) duplicatas detectadas na importação (aqui não há import extra, então mostra (nenhuma))
        System.out.println("== Duplicatas detectadas na importação ==\n");
        System.out.println("(nenhuma)\n");

        // 7) Matrículas e médias por estudante
        System.out.println("== Matrículas ==\n");
        for (Estudante e : lista.obterLista()) {
            List<Matricula> ms = historico.obterMatriculas(e.getId());
            String matriculasStr = ms.stream().map(Matricula::toString).collect(Collectors.joining(", "));
            double media = historico.mediaDoEstudante(e.getId());
            System.out.println(e.getNome() + (matriculasStr.isEmpty() ? ": (nenhuma matrícula)" : ": " + matriculasStr) + "  Média: " + String.format("%.2f", media));
        }
        System.out.println();

        // 8) Médias por disciplina
        System.out.println("== Médias por Disciplina ==\n");
        for (Disciplina d : cadastro.obterTodasDisciplinas()) {
            double media = historico.mediaDaDisciplina(d.getCodigo());
            System.out.println(d.getCodigo() + ": " + String.format("%.2f", media));
        }
        System.out.println();

        // 9) Top 3 alunos por média
        System.out.println("== Top 3 alunos por média ==\n");
        List<HistoricoNotas.EstudanteMedia> top3 = historico.topNEstudantesPorMedia(3, lista);
        int pos = 1;
        for (HistoricoNotas.EstudanteMedia em : top3) {
            System.out.println(pos + ") " + em.estudante.getNome() + " - " + String.format("%.2f", em.media));
            pos++;
        }
        System.out.println();

        // 10) Alunos com média >= 8.0
        System.out.println("== Alunos com média >= 8.0 ==\n");
        List<Estudante> aprovados = historico.alunosComMediaMaiorOuIgual(8.0, lista);
        if (aprovados.isEmpty()) {
            System.out.println("(nenhum)");
        } else {
            System.out.println(aprovados.stream().map(Estudante::getNome).collect(Collectors.joining(", ")));
        }
        System.out.println();

        // 11) Disciplinas com média < 6.0
        System.out.println("== Disciplinas com média < 6.0 ==\n");
        List<Disciplina> abaixo = historico.disciplinasComMediaMenorQue(6.0, cadastro.obterTodasDisciplinas(), cadastro);
        if (abaixo.isEmpty()) {
            System.out.println("(nenhuma)");
        } else {
            System.out.println(abaixo.stream().map(Disciplina::getCodigo).collect(Collectors.joining(", ")));
        }
        System.out.println();

        // Exemplo de uso de obterNota (opcional demonstration)
        System.out.println("== Exemplo: nota de Ana em PRG201 ==\n");
        Optional<Double> notaAnaPrg = historico.obterNota(1, "PRG201");
        System.out.println("Ana PRG201: " + (notaAnaPrg.isPresent() ? notaAnaPrg.get() : "não matriculada"));
    }
}
