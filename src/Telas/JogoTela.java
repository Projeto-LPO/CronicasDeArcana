package Telas;

import javax.swing.*;
import java.awt.*;

public class JogoTela extends JFrame {
    public JogoTela() {
        //painel principal
        JFrame janelaJogo = new JFrame();
        janelaJogo.setTitle("Partida executada!");
        janelaJogo.setResizable(false);
        janelaJogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janelaJogo.setLocationRelativeTo(null);
        janelaJogo.setVisible(true);

        //painel de descrição da carta
        JPanel descricaoPanel = new JPanel();
        descricaoPanel.setLayout(new BoxLayout(descricaoPanel, BoxLayout.Y_AXIS));
        descricaoPanel.setPreferredSize(new Dimension(250, 350));
        descricaoPanel.setMaximumSize(new Dimension(250, 350));
        descricaoPanel.setBackground(new Color(255, 255, 255));


    }
}