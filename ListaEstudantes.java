import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ListaEstudantes {
    private final List<Estudante> lista;

    public ListaEstudantes() {
        this.lista = new ArrayList<>();
    }

    public void adicionarEstudante(Estudante e) {
        lista.add(e);
    }

    public boolean removerEstudantePorId(int id) {
        return lista.removeIf(e -> e.getId() == id);
    }

    public Estudante obterEstudantePorIndice(int indice) {
        if (indice < 0 || indice >= lista.size()) return null;
        return lista.get(indice);
    }

    public List<Estudante> buscarEstudantesPorNome(String substring) {
        String sub = substring.toLowerCase();
        return lista.stream()
                .filter(e -> e.getNome().toLowerCase().contains(sub))
                .collect(Collectors.toList());
    }

    public void ordenarEstudantesPorNome() {
        Collections.sort(lista, (a, b) -> a.getNome().compareToIgnoreCase(b.getNome()));
    }

    public List<Estudante> obterLista() {
        return new ArrayList<>(lista); // cópia para segurança
    }
}
