package ElementosGraficos.Telas.Inventario;

import ElementosGraficos.Telas.MenuInicial;
import ElementosGraficos.UiElements.JogadorUI;
import MecanicasDeJogo.Jogador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaInventario2 extends JFrame {

    private Jogador jogador1;
    private Jogador jogador2;

    public TelaInventario2(Jogador jogador1, Jogador jogador2) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;


        this.setTitle("Inventário - "+jogador2.getNome());
        this.setSize(1000, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        // painel principal com GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        mainPanel.setBackground(new Color(0, 74, 173));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // painel esquerdo (Cartas Disponíveis)
        JPanel painelPrincipalInventario = new JPanel(new BorderLayout());
        painelPrincipalInventario.setOpaque(false);
        painelPrincipalInventario.setPreferredSize(new Dimension(300, 600));

        JPanel cartasDisponiveis = new JPanel(new BorderLayout());
        cartasDisponiveis.setBackground(new Color(242, 213, 174));

        JLabel tituloCartasDisponiveis = new JLabel("Cartas disponíveis", JLabel.CENTER);
        tituloCartasDisponiveis.setFont(new Font("Uncial Antiqua", Font.BOLD, 17));
        cartasDisponiveis.add(tituloCartasDisponiveis, BorderLayout.NORTH);

        JScrollPane scrollPainel = new JScrollPane(cartasDisponiveis);
        painelPrincipalInventario.add(scrollPainel, BorderLayout.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.3;
        gbc.weighty = 1;
        mainPanel.add(painelPrincipalInventario, gbc);

        // painel central (Deck)
        JPanel deckPainelPrincipal = new JPanel(new BorderLayout());
        JPanel deckCartasPainel = new JPanel(new BorderLayout());
        gbc.fill = GridBagConstraints.BOTH;

        deckPainelPrincipal.setPreferredSize(new Dimension(400, 600));
        deckPainelPrincipal.setOpaque(false);
        deckCartasPainel.setBackground(new Color(149, 124, 90));
        deckPainelPrincipal.add(deckCartasPainel, BorderLayout.CENTER);

        //painel inferior - botoes salvar e descartar
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelInferior.setOpaque(false);

        JButton btnSalvar = new JButton("Salvar deck");
        btnSalvar.setBackground(new Color(242, 213, 174));

        JButton btnDescartar = new JButton("Descartar deck");
        btnDescartar.setBackground(new Color(242, 213, 174));

        painelInferior.add(btnSalvar);
        painelInferior.add(btnDescartar);
        deckPainelPrincipal.add(painelInferior, BorderLayout.SOUTH);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridheight = 1;
        gbc.weightx = 0.7;
        gbc.weighty = 1;
        mainPanel.add(deckPainelPrincipal, gbc);

        //painel superior - informações do jogador
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.setBackground(new Color(242, 213, 174));
        topPanel.setPreferredSize(new Dimension(200, 100));

        JPanel playerInfoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        playerInfoPanel.setOpaque(false);

        JLabel playerInfoLabel = new JLabel("Nome: "+jogador2.getNome()+" | Nível: "+jogador2.getNivel());
        playerInfoLabel.setFont(new Font("Uncial Antiqua", Font.BOLD, 20));
        playerInfoPanel.add(playerInfoLabel);

        topPanel.add(playerInfoPanel);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(topPanel, gbc);

        //botão Voltar
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(new Font("Uncial Antiqua", Font.PLAIN, 20));
        btnVoltar.setBackground(new Color(242, 213, 174));
        btnVoltar.setPreferredSize(new Dimension(200, 40));
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MenuInicial(jogador1, jogador2).setVisible(true);
            }
        });

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(btnVoltar, gbc);

        //botão de troca de inventario (jogador 2 ---> jogador 1)
        JButton btnInventarioJogador2 = new JButton("Inventário do Jogador 1");
        btnInventarioJogador2.setBackground(new Color(242, 213, 174));
        btnInventarioJogador2.setPreferredSize(new Dimension(50, 50));

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.EAST;

        btnInventarioJogador2.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {
            dispose();
            new TelaInventario1(jogador1, jogador2).setVisible(true);
          }
        });

        mainPanel.add(btnInventarioJogador2, gbc);

        //adiciona o painel ao frame
        this.add(mainPanel);
    }
}
