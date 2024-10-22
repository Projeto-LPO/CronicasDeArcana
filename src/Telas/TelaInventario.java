package Telas;

import javax.swing.*;
        import java.awt.*;

public class TelaInventario extends JFrame {

    public TelaInventario() {
        setTitle("Customização de Deck");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //painel principal
        JPanel mainPanel = new JPanel(new BorderLayout());

        //painel esquerdo
        JPanel telaInventario = new JPanel(new BorderLayout());
        telaInventario.setBackground(new Color(240, 224, 208));

        JLabel tituloInventario = new JLabel("Cartas disponíveis", JLabel.CENTER);
        telaInventario.add(tituloInventario, BorderLayout.NORTH);

        //painel das cartas disponiveis
        JPanel cartasDisponiveis = new JPanel(new GridLayout(1, 1, 10, 10));
        for (int i = 0; i < 20; i++) {
            JButton btnCarta = new JButton("Carta " + (i + 1));
            cartasDisponiveis.add(btnCarta);
        }

        JScrollPane scrollPainel = new JScrollPane(cartasDisponiveis);    //painel para scrollagem das cartas
        telaInventario.add(scrollPainel, BorderLayout.CENTER);

        mainPanel.add(telaInventario, BorderLayout.WEST);

        //painel central (Deck)
        JPanel deckPrincipalPainel = new JPanel(new BorderLayout());                                //painel do fundo
        JPanel deckCartasPainel = new JPanel(new GridLayout(6, 5, 10, 10));   //painel das cartas do deck
        for (int i = 0; i < 30; i++) {
            JButton deckSlot = new JButton();
            deckCartasPainel.add(deckSlot);
        }

        deckPrincipalPainel.add(deckCartasPainel, BorderLayout.CENTER);

        //painel canto inferior direito (botoes de salvar ou descartar o deck)
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnSalvar = new JButton("Salvar deck");
        JButton btnDescartar = new JButton("Descartar deck");

        painelInferior.add(btnSalvar);
        painelInferior.add(btnDescartar);

        deckPrincipalPainel.add(painelInferior, BorderLayout.SOUTH);
        mainPanel.add(deckPrincipalPainel, BorderLayout.CENTER);

        //painel canto superior direito (nome e nivel)
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel playerInfo = new JLabel("Jogador 1 ou \"nome\" - Nível 1", JLabel.CENTER);
        topPanel.add(playerInfo, BorderLayout.CENTER);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        add(mainPanel);
    }


}