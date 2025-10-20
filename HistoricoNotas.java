import java.util.*;
import java.util.stream.Collectors;

public class HistoricoNotas {
    // map: idEstudante -> lista de matrículas
    private final Map<Integer, List<Matricula>> mapa;

    public HistoricoNotas() {
        this.mapa = new HashMap<>();
    }

    public void adicionarMatricula(int idEstudante, String codigoDisciplina, double nota) {
        mapa.computeIfAbsent(idEstudante, k -> new ArrayList<>())
                .add(new Matricula(codigoDisciplina, nota));
    }

    public List<Matricula> obterMatriculas(int idEstudante) {
        return mapa.getOrDefault(idEstudante, Collections.emptyList());
    }

    public Optional<Double> obterNota(int idEstudante, String codigoDisciplina) {
        return obterMatriculas(idEstudante).stream()
                .filter(m -> m.getCodigoDisciplina().equalsIgnoreCase(codigoDisciplina))
                .map(Matricula::getNota)
                .findFirst();
    }

    public boolean removerMatricula(int idEstudante, String codigoDisciplina) {
        List<Matricula> list = mapa.get(idEstudante);
        if (list == null) return false;
        boolean removed = list.removeIf(m -> m.getCodigoDisciplina().equalsIgnoreCase(codigoDisciplina));
        if (list.isEmpty()) {
            mapa.remove(idEstudante);
        }
        return removed;
    }

    public double mediaDoEstudante(int idEstudante) {
        List<Matricula> list = mapa.get(idEstudante);
        if (list == null || list.isEmpty()) return 0.0;
        double soma = 0.0;
        for (Matricula m : list) soma += m.getNota();
        return soma / list.size();
    }

    public double mediaDaDisciplina(String codigoDisciplina) {
        List<Double> notas = mapa.values().stream()
                .flatMap(List::stream)
                .filter(m -> m.getCodigoDisciplina().equalsIgnoreCase(codigoDisciplina))
                .map(Matricula::getNota)
                .collect(Collectors.toList());
        if (notas.isEmpty()) return 0.0;
        double soma = notas.stream().mapToDouble(Double::doubleValue).sum();
        return soma / notas.size();
    }

    public List<EstudanteMedia> topNEstudantesPorMedia(int N, ListaEstudantes listaEstudantes) {
        // cria lista de pares (Estudante, media)
        List<EstudanteMedia> medias = new ArrayList<>();
        for (Estudante e : listaEstudantes.obterLista()) {
            double media = mediaDoEstudante(e.getId());
            medias.add(new EstudanteMedia(e, media));
        }
        medias.sort((a, b) -> Double.compare(b.media, a.media)); // decrescente
        return medias.stream().limit(N).collect(Collectors.toList());
    }

    // apoio: retorna estudantes com media >= threshold
    public List<Estudante> alunosComMediaMaiorOuIgual(double threshold, ListaEstudantes listaEstudantes) {
        return listaEstudantes.obterLista().stream()
                .filter(e -> mediaDoEstudante(e.getId()) >= threshold)
                .collect(Collectors.toList());
    }

    // apoio: disciplinas com media < threshold (recebe lista de disciplinas para checar)
    public List<Disciplina> disciplinasComMediaMenorQue(double threshold, List<Disciplina> todas, CadastroDisciplinas cadastro) {
        return todas.stream()
                .filter(d -> mediaDaDisciplina(d.getCodigo()) < threshold)
                .collect(Collectors.toList());
    }

    // classe auxiliar para top N
    public static class EstudanteMedia {
        public final Estudante estudante;
        public final double media;

        public EstudanteMedia(Estudante e, double media) {
            this.estudante = e;
            this.media = media;
        }
    }
}
