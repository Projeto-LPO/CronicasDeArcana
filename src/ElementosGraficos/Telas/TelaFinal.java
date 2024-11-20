package ElementosGraficos.Telas;

import ElementosGraficos.UiElements.JogadorUI;
import MecanicasDeJogo.Jogador;

import javax.swing.*;
import java.awt.*;

public class TelaFinal extends JFrame {
    private Jogador jogador1;
    private Jogador jogador2;

    public TelaFinal() {
        setTitle("Partida Finalizada!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        JPanel mainFinalPanel = new JPanel(new BorderLayout());
        mainFinalPanel.setBackground(Color.LIGHT_GRAY);

        // Área de texto
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

        // Painel de botões
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());

        JButton menuButton = new JButton("Menu Inicial");
        menuButton.addActionListener(e -> {
            // Instanciando o Menu Inicial
            new MenuInicial(jogador1, jogador2);
            dispose(); // Fecha a TelaFinal
        });
        buttonsPanel.add(menuButton);

        JButton exitButton = new JButton("Sair");
        exitButton.addActionListener(e -> {
            System.exit(0); // Sai do programa
        });
        buttonsPanel.add(exitButton);

        // Adicionando os botões no painel principal
        mainFinalPanel.add(buttonsPanel, BorderLayout.SOUTH);

        // Adicionando o painel final na janela
        this.add(mainFinalPanel);
    }
}

