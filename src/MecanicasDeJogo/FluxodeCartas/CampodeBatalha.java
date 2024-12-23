package MecanicasDeJogo.FluxodeCartas;

import MecanicasDeJogo.Abstract.*;
import MecanicasDeJogo.Jogador;
import Personagens.Criatura;

import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import Encantamento.Encantamento;
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
         campo.remove(carta);
         System.out.println("Carta removida do campo de batalha");

    }

    public void comprarCartaCampo() {
        if (!deck.isEmpty()) {
            Carta cartaComprada = deck.comprarCarta();
            mao.adicionarCartasMao(cartaComprada);
            System.out.println("Você comprou a carta: " + cartaComprada.getNome());
        } else {
            System.out.println("Não há mais cartas no deck!");
        }
    }

    public List<Carta> getCampo() {
        return campo;
    }

    public List<Criatura> getCriaturasNoCampo() {
        List<Criatura> criaturasNoCampo = new ArrayList<>();
        for (Carta carta : campo) {
            if (carta instanceof Criatura) {
                criaturasNoCampo.add((Criatura) carta);
            }
        }
        return criaturasNoCampo;
    }

    public List<Encantamento> getEncantamentosNoCampo() {
        List<Encantamento> encantamentos = new ArrayList<>();
        for (Carta carta : campo) {
            if (carta instanceof Encantamento) {
                encantamentos.add((Encantamento) carta);
            }
        }
        return encantamentos;
    }

    public void limpar() {
        campo.clear(); // Remove todas as cartas do campo
    }

}