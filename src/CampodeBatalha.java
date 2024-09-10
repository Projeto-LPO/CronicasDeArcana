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
        mao.removerCarta(carta);
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

    public void comprarCarta() {
        Carta cartaComprada = deck.comprarCarta();
        if (cartaComprada != null) {
            mao.adicionarCartas(cartaComprada);
            System.out.println("Carta comprada do deck: " + cartaComprada.getNome());
        }
    }

    public List<Carta> getCampo() {
        return campo;
    }
}
