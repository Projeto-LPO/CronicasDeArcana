package FluxodeCartas;

import MecanicasDeJogo.Abstract.Carta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Decks {
    private List<Carta> cartas;

    // Construtor com lista de cartas
    public Decks(List<Carta> cartas) {
        if (cartas.size() < 8) {
            throw new IllegalArgumentException("O deck deve ter no mínimo 8 cartas");
        }
        this.cartas = new ArrayList<>(cartas);
    }

    // Construtor sem parâmetros, inicializa lista vazia
    public Decks() {
        this.cartas = new ArrayList<>();  // Inicializa a lista de cartas
    }

    // Método para verificar se o deck está vazio
    public boolean isEmpty() {
        return cartas.isEmpty();
    }

    // Método para comprar carta do topo do deck
    public Carta comprarCarta() {
        if (cartas.isEmpty()) {
            System.out.println("O deck está vazio!");
            return null; // Não há cartas para comprar
        }
        // Remove e retorna a última carta do deck
        Carta carta = cartas.remove(cartas.size() - 1);
        System.out.println("Carta comprada: " + carta.getNome());
        return carta;
    }

    // Retorna o tamanho do deck
    public int getTamanho() {
        return cartas.size();
    }

    // Adiciona uma carta ao deck
    public void adicionarCarta(Carta carta) {
        cartas.add(carta);
    }

    // Embaralha o deck
    public void embaralhar() {
        Collections.shuffle(cartas);
    }

    // Retorna a lista de cartas (por referência)
    public List<Carta> getCartas() {
        return cartas;
    }



}
