package MecanicasDeJogo.FluxodeCartas;
import MecanicasDeJogo.Abstract.Carta;
import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<Carta> cartas;

    public Inventario() {
        cartas = new ArrayList<>();
    }

    // Método para adicionar uma carta ao inventário
    public void adicionarCartaAoInventario(Carta carta) {
        cartas.add(carta);
        System.out.println("Carta " + carta.getNome() + " foi adicionada ao inventário.");
    }

    // Método para remover uma carta do inventário
    public void removerCartaDoInventario(Carta carta) {
        if (cartas.remove(carta)) {
            System.out.println("Carta " + carta.getNome() + " foi removida do inventário.");
        } else {
            System.out.println("Carta " + carta.getNome() + " não está presente no inventário.");
        }
    }

    // Método para listar todas as cartas no inventário
    public List<Carta> listarCartasNoInventario() {
        System.out.println("Listando cartas no inventário:");
        for (Carta carta : cartas) {
            System.out.println("- " + carta.getNome());
        }
        return cartas;
    }

    // Método para verificar se o inventário possui uma determinada carta
    public boolean inventarioPossuiCarta(Carta carta) {
        boolean possui = cartas.contains(carta);
        if (possui) {
            System.out.println("Inventário possui a carta " + carta.getNome() + ".");
        } else {
            System.out.println("Inventário não possui a carta " + carta.getNome() + ".");
        }
        return possui;
    }

    // Método para obter a quantidade total de cartas no inventário
    public int contarCartasNoInventario() {
        int quantidade = cartas.size();
        System.out.println("O inventário possui " + quantidade + " cartas.");
        return quantidade;
    }
}
