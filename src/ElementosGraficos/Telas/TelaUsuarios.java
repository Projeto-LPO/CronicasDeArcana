package ElementosGraficos.Telas;

import ElementosGraficos.UiElements.JogadorUI;
import MecanicasDeJogo.Jogador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaUsuarios extends JFrame {
    private JTextField nomeJogador1Field;
    private JTextField nomeJogador2Field;
    private JButton confirmarButton;
    private JogadorUI jogadorUi1;
    private JogadorUI jogadorUi2;
    private Jogador jogador1;
    private Jogador jogador2;

    public TelaUsuarios(Jogador jogador1, Jogador jogador2){
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;

        this.setTitle("Entrada de Nomes");
        this.setSize(300,150);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(3, 2));
        this.setBackground(Color.BLACK);

        nomeJogador1Field = new JTextField();
        nomeJogador2Field = new JTextField();

        confirmarButton = new JButton("Confirmar nomes");
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeJogador1 = nomeJogador1Field.getText();
                String nomeJogador2 = nomeJogador2Field.getText();

                jogador1.setNome(nomeJogador1);
                jogador2.setNome(nomeJogador2);

                JFrame jogadoresFrame = new JFrame("Informações dos Jogadores");
                jogadoresFrame.setLayout(new GridLayout(2, 1));

                //inicializa a tela de menu inicial ao confirmar
                dispose();
                new MenuInicial(jogador1, jogador2, jogadorUi1, jogadorUi2).setVisible(true);

            }
        });
        add(new JLabel("Nome do Jogador 1:"));
        add(nomeJogador1Field);
        add(new JLabel("Nome do Jogador 2:"));
        add(nomeJogador2Field);
        add(confirmarButton);

        setVisible(true);
    }
}
