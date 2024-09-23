import Feiticos.FeitiçoCura;
import Feiticos.FeitiçoDano;
import MecanicasDeJogo.Decks;
import MecanicasDeJogo.Jogador;
import MecanicasDeJogo.Jogo;
import MecanicasDeJogo.Mao;
import Personagens.*;

import java.util.Scanner;

public class CronicasDeArcanaTerminal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Pede os nomes dos jogadores
        System.out.print("Insira o nome do Jogador 1: ");
        String nomeJogador1 = scanner.nextLine();

        System.out.print("Insira o nome do Jogador 2: ");
        String nomeJogador2 = scanner.nextLine();

        // Cria os decks para os dois jogadores
        Decks deck1 = criarDeck1();
        Decks deck2 = criarDeck2();

        // Cria os jogadores com os nomes, decks e pontos de vida e mana
        Jogador jogador1 = new Jogador(nomeJogador1, deck1, 100, 20);
        Jogador jogador2 = new Jogador(nomeJogador2, deck2, 100, 20);

        // Cria a mão de cada jogador com as primeiras 3 cartas do deck
        Mao maoJogador1 = new Mao();
        Mao maoJogador2 = new Mao();

        // Adiciona 3 cartas do deck à mão do Jogador 1
        for (int i = 0; i < 3; i++) {
            maoJogador1.adicionarCartasMao(deck1.comprarCarta());
        }

        // Adiciona 3 cartas do deck à mão do Jogador 2
        for (int i = 0; i < 3; i++) {
            maoJogador2.adicionarCartasMao(deck2.comprarCarta());
        }

        // Exibe as cartas na mão de cada jogador
        System.out.println("Cartas na mão do " + jogador1.getNome() + ":");
        maoJogador1.mostrarCartas();

        System.out.println("Cartas na mão do " + jogador2.getNome() + ":");
        maoJogador2.mostrarCartas();

        // Cria e inicia o jogo
        Jogo jogo = new Jogo(jogador1, jogador2);
        jogo.iniciar();
    }

    // Função para criar o Deck 1
    private static Decks criarDeck1() {
        Decks deck = new Decks();
        deck.adicionarCarta(new Guerreiro("Cavaleiro da Luz", 3, 7, 5));
        deck.adicionarCarta(new Arqueiro("Arqueiro da Floresta", 2, 5, 3));
        deck.adicionarCarta(new Mago("Conjurador das Chamas", 7, 5, 8));
        deck.adicionarCarta(new Dragao("Dragão Ancião", 8, 12, 10));
        deck.adicionarCarta(new FeitiçoCura("Revigor", 4, "Cura uma carta", 2));
        return deck;
    }

    // Função para criar o Deck 2
    private static Decks criarDeck2() {
        Decks deck = new Decks();
        deck.adicionarCarta(new Guerreiro("Guardião do Reino", 5, 8, 7));
        deck.adicionarCarta(new Arqueiro("Mestre do Arco", 6, 8, 5));
        deck.adicionarCarta(new Mago("Feiticeiro do Fogo", 5, 4, 6));
        deck.adicionarCarta(new Dragao("Dragão Celestial", 10, 14, 12));
        deck.adicionarCarta(new FeitiçoDano("Bola de Fogo", 4, "Dano instantâneo", 4));
        return deck;
    }
}
