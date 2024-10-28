package ElementosGraficos.Telas;

import javax.swing.*;
import java.awt.*;

public class JogoTela extends JFrame {
    public JogoTela() {
        // Configurações da janela principal
        this.setTitle("Partida executada!");
        this.setResizable(false);
        this.setBounds(100, 100, 1200, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel gamePanel = new JPanel(new GridBagLayout());
        gamePanel.setBackground(new Color(0, 0, 128)); // fundo azul escuro

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10); // espaçamento entre os painéis

        // Campo de Batalha (centro)
        JPanel campoDeBatalhaPainel = new JPanel();
        campoDeBatalhaPainel.setPreferredSize(new Dimension(600, 400));
        campoDeBatalhaPainel.setBackground(new Color(139, 69, 19)); // cor marrom
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        gamePanel.add(campoDeBatalhaPainel, c);

        // Painel do Cemitério (lado direito)
        JPanel cemiterioPainel = new JPanel();
        cemiterioPainel.setPreferredSize(new Dimension(150, 300));
        cemiterioPainel.setBackground(Color.GRAY);
        c.gridx = 3;
        c.gridy = 1;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.CENTER;
        gamePanel.add(cemiterioPainel, c);

        // Painel de Descrição da Carta (lado esquerdo)
        JPanel descricaoPainel = new JPanel();
        descricaoPainel.setPreferredSize(new Dimension(200, 250));
        descricaoPainel.setBackground(new Color(255, 228, 181)); // cor bege para descrição da carta
        descricaoPainel.setLayout(new BorderLayout());

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.CENTER;
        gamePanel.add(descricaoPainel, c);

        // Painel de Jogo do Jogador 1 (parte inferior)
        JPanel jogador1Painel = new JPanel(new BorderLayout());
        jogador1Painel.setPreferredSize(new Dimension(500, 150));
        jogador1Painel.setBackground(new Color(0, 128, 0)); // cor verde para jogador 1

            // Painel de Deck do Jogador 1
            JPanel deckJogador1Painel = new JPanel();
            deckJogador1Painel.setPreferredSize(new Dimension(150, 150));
            deckJogador1Painel.setBackground(new Color(44, 22, 11)); // cor marrom para o deck
            jogador1Painel.add(deckJogador1Painel, BorderLayout.WEST);

            // Painel da Mão do Jogador 1
            JPanel maoJogador1Painel = new JPanel();
            maoJogador1Painel.setPreferredSize(new Dimension(350, 150));
            maoJogador1Painel.setBackground(new Color(123, 54, 53)); // cor para a mão do jogador 1
            jogador1Painel.add(maoJogador1Painel, BorderLayout.CENTER);

        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.SOUTH;
        gamePanel.add(jogador1Painel, c);

        // Painel de Jogo do Jogador 2 (parte superior)
        JPanel jogador2Painel = new JPanel(new BorderLayout());
        jogador2Painel.setPreferredSize(new Dimension(500, 150));

        jogador2Painel.setBackground(new Color(0, 128, 0));

            //painel de Deck do Jogador 2
            JPanel deckJogador2Painel = new JPanel();
            deckJogador2Painel.setPreferredSize(new Dimension(100, 150));
            deckJogador2Painel.setBackground(new Color(44, 22, 11));
            jogador2Painel.add(deckJogador2Painel, BorderLayout.WEST);

            // Painel da Mão do Jogador 2
            JPanel maoJogador2Painel = new JPanel();
            maoJogador2Painel.setPreferredSize(new Dimension(400, 150));
            maoJogador2Painel.setBackground(new Color(123, 54, 53));
            jogador2Painel.add(maoJogador2Painel, BorderLayout.CENTER);

        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.NORTH;
        gamePanel.add(jogador2Painel, c);

        // Adiciona o painel principal ao JFrame
        this.add(gamePanel);
        this.setVisible(true);
    }
}
