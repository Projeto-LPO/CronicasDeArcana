package ElementosGraficos.Telas;


import ElementosGraficos.UiElements.CartaUI;
import ElementosGraficos.UiElements.JogadorUI;
import MecanicasDeJogo.Abstract.Carta;
import MecanicasDeJogo.Jogador;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class JogoTela extends JFrame {
    private Jogador jogador1;
    private Jogador jogador2;
    private JogadorUI jogadorUi1;
    private JogadorUI jogadorUi2;
    private List<Carta> maoJogador1;
    private List<Carta> maoJogador2;


    public JogoTela(Jogador jogador1, Jogador jogador2, JogadorUI jogadorUi1, JogadorUI jogadorUi2) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;

        this.setTitle("Partida iniciada! | "+ this.jogador1.getNome()+" versus "+ this.jogador2.getNome());
        this.setResizable(false);
        this.setBounds(100, 100, 1200, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel gamePanel = new JPanel(new GridBagLayout());
        gamePanel.setBackground(new Color(0, 0, 128));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        //campo de Batalha (centro)
        JPanel campoDeBatalhaPainel = new JPanel(new BorderLayout());
        campoDeBatalhaPainel.setPreferredSize(new Dimension(500, 400));
        campoDeBatalhaPainel.setBorder(BorderFactory.createEmptyBorder(15,20,15,20));
        campoDeBatalhaPainel.setBackground(new Color(139, 69, 19));

            //campo de batalha jogador 1
            JPanel campoJogador1 = new JPanel();
            campoJogador1.setPreferredSize(new Dimension(540,180));
            campoJogador1.setBackground(Color.WHITE);

            //campo de batalha jogador 2
            JPanel campoJogador2 = new JPanel();
            campoJogador2.setPreferredSize(new Dimension(540,180));
            campoJogador2.setBackground(Color.WHITE);

        campoDeBatalhaPainel.add(campoJogador1, BorderLayout.SOUTH);
        campoDeBatalhaPainel.add(campoJogador2, BorderLayout.NORTH);

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        gamePanel.add(campoDeBatalhaPainel, c);

        //painel do Cemitério (lado direito)
        JPanel cemiterioPainel = new JPanel();
        cemiterioPainel.setPreferredSize(new Dimension(150, 300));
        cemiterioPainel.setBackground(Color.GRAY);
        c.gridx = 3;
        c.gridy = 1;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.CENTER;
        gamePanel.add(cemiterioPainel, c);

        //painel de Descrição da Carta (lado esquerdo)
        JPanel descricaoPainel = new JPanel();
        descricaoPainel.setPreferredSize(new Dimension(200, 250));
        descricaoPainel.setBackground(new Color(255, 228, 181));
        descricaoPainel.setLayout(new BorderLayout());

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.CENTER;
        gamePanel.add(descricaoPainel, c);

        //painel de Jogo do Jogador 1 (parte inferior)
        JPanel jogador1Painel = new JPanel(new BorderLayout());
        jogador1Painel.setPreferredSize(new Dimension(550, 150));
        jogador1Painel.setOpaque(false);

            //painel de Deck do Jogador 1
            JPanel deckJogador1Painel = new JPanel(new BorderLayout());
            deckJogador1Painel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            deckJogador1Painel.setPreferredSize(new Dimension(125, 150));
            deckJogador1Painel.setOpaque(false);

                //botão do deck jogador1
                JButton btDdeckJog1 = new JButton("Deck");
                btDdeckJog1.setBackground(Color.YELLOW);
                btDdeckJog1.setPreferredSize(new Dimension(75, 100));

                deckJogador1Painel.add(btDdeckJog1, BorderLayout.CENTER);

            //adiciona o deck ao painel de jogador
            jogador1Painel.add(deckJogador1Painel, BorderLayout.WEST);

            //painel da Mão do Jogador 1
            JPanel maoJogador1Painel = new JPanel(new GridBagLayout());
            maoJogador1Painel.setPreferredSize(new Dimension(350, 150));
            maoJogador1Painel.setOpaque(false);
            GridBagConstraints c2 = new GridBagConstraints();
            c2.insets = new Insets(10, 10, 10, 10);
            c2.gridy = 0;


                //teste de integração da logica com interface
                for (int i = 0; i < maoJogador1.size(); i++) {
                    Carta carta = maoJogador1.get(i);
                    CartaUI cartaBotao = new CartaUI(carta, jogador1);
                    cartaBotao.setPreferredSize(new Dimension(62, 80));
                    cartaBotao.setFont(new Font("Uncial Antiqua", Font.BOLD, 12));
                    cartaBotao.setBackground(new Color(0, 128, 0));
                    cartaBotao.setBorderPainted(true);
                    c2.gridx = i;
                    maoJogador1Painel.add(cartaBotao, c2);
                }

             //adiciona a mão ao painel de jogador
            jogador1Painel.add(maoJogador1Painel);

        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.SOUTH;
        gamePanel.add(jogador1Painel, c);

        //painel de Jogo do Jogador 2 (parte superior)
        JPanel jogador2Painel = new JPanel(new BorderLayout());
        jogador2Painel.setPreferredSize(new Dimension(550, 150));
        jogador2Painel.setOpaque(false);

        jogador2Painel.setBackground(new Color(0, 128, 0));

            //painel de Deck do Jogador 2
            JPanel deckJogador2Painel = new JPanel(new BorderLayout());
            deckJogador2Painel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            deckJogador2Painel.setPreferredSize(new Dimension(125, 150));
            deckJogador2Painel.setOpaque(false);

                //botão do deck jogador2
                JButton btDdeckJog2 = new JButton("Deck");
                btDdeckJog2.setBackground(Color.YELLOW);
                btDdeckJog2.setPreferredSize(new Dimension(75, 100));

                deckJogador2Painel.add(btDdeckJog2, BorderLayout.CENTER);

            //adiciona o deck2 ao painel
            jogador2Painel.add(deckJogador2Painel, BorderLayout.WEST);

            // painel da Mão do Jogador 2
            JPanel maoJogador2Painel = new JPanel(new GridBagLayout());
            maoJogador2Painel.setPreferredSize(new Dimension(350, 150));
            maoJogador2Painel.setOpaque(false);
            GridBagConstraints c3 = new GridBagConstraints();
            c3.insets = new Insets(10, 10, 10, 10);
            c3.gridy = 0;

                // botões das cartas para o jogador 2
                // carta1
                JButton btnCartaJogador2 = new JButton("Carta1");
                btnCartaJogador2.setFont(new Font("Uncial Antiqua", Font.BOLD, 12));
                btnCartaJogador2.setBackground(new Color(0, 128, 0));
                btnCartaJogador2.setBorderPainted(true);
                btnCartaJogador2.setPreferredSize(new Dimension(62, 80));

                c3.gridx = 0;
                maoJogador2Painel.add(btnCartaJogador2, c3);

                // carta2
                JButton btnCarta2Jogador2 = new JButton("Carta2");
                 btnCarta2Jogador2.setFont(new Font("Uncial Antiqua", Font.BOLD, 12));
                btnCarta2Jogador2.setBackground(new Color(0, 128, 0));
                btnCarta2Jogador2.setBorderPainted(true);
                btnCarta2Jogador2.setPreferredSize(new Dimension(62, 80));

                c3.gridx = 1;
                maoJogador2Painel.add(btnCarta2Jogador2, c3);

                // carta3
                JButton btnCarta3Jogador2 = new JButton("Carta3");
                btnCarta3Jogador2.setFont(new Font("Uncial Antiqua", Font.BOLD, 12));
                btnCarta3Jogador2.setBackground(new Color(0, 128, 0));
                btnCarta3Jogador2.setBorderPainted(true);
                btnCarta3Jogador2.setPreferredSize(new Dimension(62, 80));

                c3.gridx = 2;
                 maoJogador2Painel.add(btnCarta3Jogador2, c3);

                // carta4
                JButton btnCarta4Jogador2 = new JButton("Carta4");
                btnCarta4Jogador2.setFont(new Font("Uncial Antiqua", Font.BOLD, 12));
                btnCarta4Jogador2.setBackground(new Color(0, 128, 0));
                btnCarta4Jogador2.setBorderPainted(true);
                btnCarta4Jogador2.setPreferredSize(new Dimension(62, 80));

                c3.gridx = 3;

                maoJogador2Painel.add(btnCarta4Jogador2, c3);

                // carta5
                JButton btnCarta5Jogador2 = new JButton("Carta5");
                btnCarta5Jogador2.setFont(new Font("Uncial Antiqua", Font.BOLD, 12));
                btnCarta5Jogador2.setBackground(new Color(0, 128, 0));
                btnCarta5Jogador2.setBorderPainted(true);
                btnCarta5Jogador2.setPreferredSize(new Dimension(62, 80));

                c3.gridx = 4;

                maoJogador2Painel.add(btnCarta5Jogador2, c3);

        // adiciona a mão ao painel de jogador 2
        jogador2Painel.add(maoJogador2Painel);

        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.NORTH;
        gamePanel.add(jogador2Painel, c);

        //adiciona o painel principal ao JFrame
        this.add(gamePanel);
        this.setVisible(true);
    }
}
