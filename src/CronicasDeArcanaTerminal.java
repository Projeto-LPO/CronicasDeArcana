import Feiticos.FeitiçoCura;
import Feiticos.FeitiçoDano;
import FluxodeCartas.Decks;
import MecanicasDeJogo.Jogador;
import MecanicasDeJogo.Jogo;
import FluxodeCartas.Mao;
import FluxodeCartas.CampodeBatalha;
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

        // Cria as mãos de cada jogador
        Mao maoJogador1 = new Mao();
        Mao maoJogador2 = new Mao();

        // Cria os jogadores com os nomes, decks, pontos de vida e mana
        Jogador jogador1 = new Jogador(nomeJogador1, deck1, 50, 20);
        Jogador jogador2 = new Jogador(nomeJogador2, deck2, 50, 20);

        // Cria o campo de batalha para cada jogador
        CampodeBatalha campoJogador1 = new CampodeBatalha(maoJogador1, jogador1.getCemiterio(), deck1);
        CampodeBatalha campoJogador2 = new CampodeBatalha(maoJogador2, jogador2.getCemiterio(), deck2);

        // Adiciona 3 cartas do deck à mão do Jogador 1
        for (int i = 0; i < 3; i++) {
            campoJogador1.comprarCartaCampo();
        }

        // Adiciona 3 cartas do deck à mão do Jogador 2
        for (int i = 0; i < 3; i++) {
            campoJogador2.comprarCartaCampo();
        }

        // Exibe as cartas na mão de cada jogador
        System.out.println("Cartas na mão do " + jogador1.getNome() + ":");
        maoJogador1.mostrarCartasMao();

        System.out.println("Cartas na mão do " + jogador2.getNome() + ":");
        maoJogador2.mostrarCartasMao();

        // Cria e inicia o jogo
        Jogo jogo = new Jogo(jogador1, jogador2,campoJogador1,campoJogador2);
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