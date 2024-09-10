import java.util.ArrayList;
import java.util.List;

public class Mao {
    private List<Carta> cartas;

    public Mao() {
        this.cartas = new ArrayList<>();
    }

    public void adicionarCartas(Carta carta) {
        cartas.add(carta);
        System.out.println(carta.getNome() + " foi adicionada à mão.");
    }

    public void removerCarta(Carta carta) {
        if (cartas.remove(carta)) {
            System.out.println(carta.getNome() + " foi removida da mão.");
        } else {
            System.out.println("A carta " + carta.getNome() + " não está na mão.");
        }
    }

    public boolean temCarta(Carta carta) {
        return cartas.contains(carta);
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public void mostrarCartas() {
        System.out.println("Cartas na mão:");
        for (Carta carta : cartas) {
            System.out.println("- " + carta.getNome());
        }
    }
}
