package main2;

import MecanicasDeJogo.Jogador;
import MecanicasDeJogo.FluxodeCartas.Decks;
import ElementosGraficos.Telas.TelaUsuarios;

public class CronicasDeArcana {
    public static void main(String[] args) {
        //criação dos decks
        Decks deckJogador1 = new Decks();
        Decks deckJogador2 = new Decks();

        //criação dos jogadores
        final Jogador jogador1 = new Jogador("Jogador 1", deckJogador1, 50, 10);
        final Jogador jogador2 = new Jogador("Jogador 2", deckJogador2, 50, 10);

        //criação da tela
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TelaUsuarios telaUsuarios = new TelaUsuarios(jogador1, jogador2);
                telaUsuarios.setVisible(true);
            }
        });
    }
}
