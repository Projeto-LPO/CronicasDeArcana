package main;

import ElementosGraficos.UiElements.GerenciadorDeCombate;
import MecanicasDeJogo.FluxodeCartas.InstanciaCartas;
import MecanicasDeJogo.Jogador;
import MecanicasDeJogo.FluxodeCartas.Decks;
import ElementosGraficos.Telas.TelaUsuarios;
import ElementosGraficos.Telas.JogoTela;


public class CronicasDeArcana {
    public static void main(String[] args) {
        // Instanciador de Cartas
        InstanciaCartas instanciador = new InstanciaCartas();
        // Criação dos decks com base nas cartas geradas
        Decks deckJogador1 = new Decks(instanciador.gerarCartas());
        Decks deckJogador2 = new Decks(instanciador.gerarCartas());

        // Criação dos jogadores
        Jogador jogador1 = new Jogador("Jogador 1", deckJogador1, 10, 10, 10);
        Jogador jogador2 = new Jogador("Jogador 2", deckJogador2, 10, 10, 10);


        GerenciadorDeCombate gerenciador = new GerenciadorDeCombate();

        // Inicializar a interface gráfica
        javax.swing.SwingUtilities.invokeLater(() -> {
            TelaUsuarios telaUsuarios = new TelaUsuarios(jogador1, jogador2);
            telaUsuarios.setVisible(true);

            JogoTela jogoTela = new JogoTela(jogador1, jogador2, gerenciador);
            jogoTela.iniciarJogo();
        });
    }
}
