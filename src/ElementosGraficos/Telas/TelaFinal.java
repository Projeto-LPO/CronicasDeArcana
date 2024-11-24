package ElementosGraficos.Telas;

import MecanicasDeJogo.Jogador;

import javax.swing.*;
import java.awt.*;

public class TelaFinal extends JFrame {
    private Jogador jogador1;
    private Jogador jogador2;
    private Jogador vencedor;

    public TelaFinal(Jogador jogador1, Jogador jogador2, Jogador vencedor) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.vencedor = vencedor;

        if (vencedor == null) {
            throw new IllegalArgumentException("Vencedor não pode ser null!");
        }

        setTitle("Partida Finalizada!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainFinalPanel = new JPanel();
        mainFinalPanel.setBackground(Color.LIGHT_GRAY);
        mainFinalPanel.setLayout(new BoxLayout(mainFinalPanel, BoxLayout.Y_AXIS));

        // Área de texto
        JTextArea primeiraTextArea = new JTextArea("O jogador " + vencedor.getNome() + " venceu a partida!");
        primeiraTextArea.setEditable(false);
        primeiraTextArea.setOpaque(false);
        primeiraTextArea.setFont(new Font("Uncial Antiqua", Font.BOLD, 30));
        primeiraTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainFinalPanel.add(primeiraTextArea);

        JTextArea mensagemNivel = new JTextArea("Subiu para o nível " + vencedor.getNivel() + "!");
        mensagemNivel.setEditable(false);
        mensagemNivel.setOpaque(false);
        mensagemNivel.setFont(new Font("Uncial Antiqua", Font.BOLD, 20));
        mensagemNivel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainFinalPanel.add(mensagemNivel);

        JTextArea terceiraTextArea = new JTextArea("Carta Desbloqueada: 'nome da carta'");
        terceiraTextArea.setEditable(false);
        terceiraTextArea.setOpaque(false);
        terceiraTextArea.setFont(new Font("Uncial Antiqua", Font.BOLD, 20));
        terceiraTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainFinalPanel.add(terceiraTextArea);

        // Espaço entre os textos
        mainFinalPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Painel de botões
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.setOpaque(false);

        JButton menuButton = new JButton("Menu Inicial");
        menuButton.addActionListener(e -> {
            // Instanciando o Menu Inicial
            new MenuInicial(jogador1, jogador2).setVisible(true);
            dispose(); // Fecha a TelaFinal
        });
        buttonsPanel.add(menuButton);

        JButton exitButton = new JButton("Sair");
        exitButton.addActionListener(e -> System.exit(0));
        buttonsPanel.add(exitButton);

        mainFinalPanel.add(buttonsPanel);

        // Adicionando o painel final na janela
        this.add(mainFinalPanel);
    }
}
