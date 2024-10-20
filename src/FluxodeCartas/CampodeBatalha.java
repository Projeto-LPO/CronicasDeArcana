package FluxodeCartas;

import MecanicasDeJogo.Abstract.*;
import MecanicasDeJogo.Jogador;
import Personagens.Criatura;

import java.util.ArrayList;
import java.util.List;

public class CampodeBatalha {
    private List<Carta> campo;
    private Mao mao;
    private Cemiterio cemiterio;
    private Decks deck;

    public CampodeBatalha(Mao mao, Cemiterio cemiterio, Decks deck) {
        this.campo = new ArrayList<>();
        this.mao = mao;
        this.cemiterio = cemiterio;
        this.deck = deck;
    }

    public void adicionarCartasAoCampo(Carta carta) {
        campo.add(carta);
        mao.removerCartaMao(carta);
        System.out.println(carta.getNome() + " foi colocada no campo de batalha.");
    }

    public void removerCartaDoCampo(Carta carta) {
        if (campo.remove(carta)) {
            cemiterio.adicionarCartasNoCemiterio(carta);
            System.out.println(carta.getNome() + " foi removida do campo de batalha.");
        } else {
            System.out.println(carta.getNome() + " não está no campo de batalha.");
        }
    }

    public void comprarCartaCampo() {
        if (!deck.isEmpty()) {
            Carta cartaComprada = deck.comprarCarta(); // Supondo que você tenha esse método no Deck
            mao.adicionarCartasMao(cartaComprada);
            System.out.println("Você comprou a carta: " + cartaComprada.getNome());
        } else {
            System.out.println("Não há mais cartas no deck!");
        }
    }

    public List<Carta> getCampo() {
        return campo;
    }

    public List<Criatura> getCriaturasNoCampo(Jogador jogador) {
        List<Criatura> criaturasNoCampo = new ArrayList<>();
        for (Carta carta : campo) {
            if (carta instanceof Criatura) {
                criaturasNoCampo.add((Criatura) carta);
            }
        }
        return criaturasNoCampo;
    }
}
