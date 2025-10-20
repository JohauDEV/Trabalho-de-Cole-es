import java.util.LinkedHashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

public class CadastroDisciplinas {
    // LinkedHashSet to preserve insertion order
    private final LinkedHashSet<Disciplina> disciplinas;

    public CadastroDisciplinas() {
        this.disciplinas = new LinkedHashSet<>();
    }

    public boolean adicionarDisciplina(Disciplina d) {
        // retorna false se já existia (ignora duplicatas)
        return disciplinas.add(d);
    }

    public boolean verificarDisciplina(String codigo) {
        return disciplinas.stream().anyMatch(d -> d.getCodigo().equalsIgnoreCase(codigo));
    }

    public boolean removerDisciplina(String codigo) {
        return disciplinas.removeIf(d -> d.getCodigo().equalsIgnoreCase(codigo));
    }

    public List<Disciplina> obterTodasDisciplinas() {
        return new ArrayList<>(disciplinas);
    }

    // utilitário para detectar duplicatas ao tentar inserir uma lista (retorna codigos duplicados)
    public List<String> detectarDuplicatasAoImportar(List<Disciplina> listaParaImportar) {
        List<String> duplicatas = new ArrayList<>();
        for (Disciplina d : listaParaImportar) {
            if (!adicionarDisciplina(d)) {
                duplicatas.add(d.getCodigo());
            }
        }
        return duplicatas;
    }

    // obter lista ordenada por código
    public List<Disciplina> obterDisciplinasOrdenadas() {
        return disciplinas.stream().sorted().collect(Collectors.toList());
    }
}
