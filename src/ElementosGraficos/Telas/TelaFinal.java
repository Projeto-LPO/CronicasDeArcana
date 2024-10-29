package ElementosGraficos.Telas;

import ElementosGraficos.UiElements.JogadorUI;
import MecanicasDeJogo.Jogador;

import javax.swing.*;
import java.awt.*;

public class TelaFinal extends JFrame {
    private Jogador jogador1;
    private Jogador jogador2;
    private JogadorUI jogadorUi1;
    private JogadorUI jogadorUi2;

    public TelaFinal(JogadorUI jogadorUI1, JogadorUI jogadorUI2) {
        this.jogadorUi1 = jogadorUI1;
        this.jogadorUi2 = jogadorUI2;

        setTitle("Partida Finalizada!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,600);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        JPanel mainFinalPanel = new JPanel(new BorderLayout());
        mainFinalPanel.setBackground(Color.LIGHT_GRAY);

        //area de texto
        JTextArea primeiraTextArea = new JTextArea();
        primeiraTextArea.setEditable(false);
        primeiraTextArea.setOpaque(false);
        primeiraTextArea.setFont(new Font("Uncial Antiqua", Font.BOLD, 30));
        primeiraTextArea.setText("Jogador 1 venceu a partida!");

        mainFinalPanel.add(primeiraTextArea, BorderLayout.CENTER);

        JTextArea segundaTextArea = new JTextArea();
        segundaTextArea.setEditable(false);
        segundaTextArea.setOpaque(false);
        segundaTextArea.setFont(new Font("Uncial Antiqua", Font.BOLD, 20));
        segundaTextArea.setText("Subiu para o nível x!");

        mainFinalPanel.add(segundaTextArea, BorderLayout.CENTER);

        JTextArea terceiraTextArea = new JTextArea();
        terceiraTextArea.setEditable(false);
        terceiraTextArea.setOpaque(false);
            terceiraTextArea.setFont(new Font("Uncial Antiqua", Font.BOLD, 20));
        terceiraTextArea.setText("Carta Desbloqueada: 'nome da carta'");

        mainFinalPanel.add(terceiraTextArea, BorderLayout.CENTER);


        this.add(mainFinalPanel);
    }
}
