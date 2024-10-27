package ElementosGraficos.Telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaInventario extends JFrame {

    public TelaInventario() {
        this.setTitle("Customização de Deck");
        this.setSize(1000, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        //painel principal
        JPanel mainPanel = new JPanel(new BorderLayout()) {
            //metodo sobrescrito que cria um gradiente de cores no background do painel principal
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();

                GradientPaint gradiente = new GradientPaint(0, 0, Color.decode("#004aad"), width, height, Color.decode("#271629"));
                g2d.setPaint(gradiente);
                g2d.fillRect(0, 0, width, height);
            }
        };

        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        //painel esquerdo
        JPanel painelPrincipalInventario = new JPanel(new BorderLayout());
        painelPrincipalInventario.setOpaque(false);

        //painel das cartas disponiveis
        JPanel cartasDisponiveis = new JPanel(new BorderLayout());
        cartasDisponiveis.setPreferredSize(new Dimension(200, 200));
        cartasDisponiveis.setBackground(new Color(242, 213, 174));

        JLabel tituloCartasDisponiveis = new JLabel("Cartas disponíveis", JLabel.CENTER);
        tituloCartasDisponiveis.setFont(new Font("Uncial Antiqua", Font.BOLD, 17));
        cartasDisponiveis.add(tituloCartasDisponiveis, BorderLayout.NORTH);

        JScrollPane scrollPainel = new JScrollPane(cartasDisponiveis);    //painel para scrollagem das cartas
        painelPrincipalInventario.add(scrollPainel, BorderLayout.CENTER);

        mainPanel.add(painelPrincipalInventario, BorderLayout.WEST);

        //painel central (Deck)
        JPanel deckPainelPrincipal = new JPanel(new BorderLayout());            //painel do fundo
        JPanel deckCartasPainel = new JPanel(new BorderLayout());               //painel das cartas do deck

        deckPainelPrincipal.setPreferredSize(new Dimension(300, 300));
        deckPainelPrincipal.add(deckCartasPainel, BorderLayout.CENTER);
        deckPainelPrincipal.setOpaque(false);
        deckCartasPainel.setBackground(new Color(149, 124, 90));

        //painel canto inferior direito (botoes de salvar ou descartar o deck)
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelInferior.setOpaque(false);

        JButton btnSalvar = new JButton("Salvar deck");
        btnSalvar.setBackground(new Color(242, 213, 174));

        JButton btnDescartar = new JButton("Descartar deck");
        painelInferior.setOpaque(false);
        btnDescartar.setBackground(new Color(242, 213, 174));

        painelInferior.add(btnSalvar);
        painelInferior.add(btnDescartar);

        deckPainelPrincipal.add(painelInferior, BorderLayout.SOUTH);
        mainPanel.add(deckPainelPrincipal, BorderLayout.CENTER);

        //painel canto superior direito (nome e nivel)
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.setBackground(new Color(242, 213, 174));
        topPanel.setPreferredSize(new Dimension(50,50));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            //painel que contem as informações do jogador
            JPanel playerInfoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            playerInfoPanel.setOpaque(false);

            JLabel playerInfoLabel = new JLabel("JOGADOR 1 - NIVEL 1");

            playerInfoPanel.add(playerInfoLabel);

        topPanel.add(playerInfoPanel, BorderLayout.CENTER);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        //botão de voltar
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(new Font("Uncial Antiqua", Font.PLAIN, 24));
        btnVoltar.setBackground(new Color(242, 213, 174));
        btnVoltar.setPreferredSize(new Dimension(25,25));
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MenuInicial().setVisible(true);
            }
        });

        mainPanel.add(btnVoltar, BorderLayout.SOUTH);

        this.add(mainPanel);
    }


}