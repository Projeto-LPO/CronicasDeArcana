package ElementosGraficos.Telas;

import javax.swing.*;
import java.awt.*;

public class JogoTela extends JFrame {
    public JogoTela() {
        //painel principal
        this.setTitle("Partida executada!");
        this.setResizable(false);
        this.setPreferredSize(new Dimension(1000, 750));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        JPanel gamePanel = new JPanel();
        GridBagConstraints c = new GridBagConstraints();

        //painel do campo de batalha
        JPanel campoDeBatalhaPainel = new JPanel();
        campoDeBatalhaPainel.setBackground(Color.BLUE);

            //posicionamento
            c.gridx = 5;
            c.gridy = 4;
            c.gridwidth = 5;
            c.gridheight = 5;

        gamePanel.add(campoDeBatalhaPainel,c);

        //painel do cemiterio
        JPanel cemiterioPainel = new JPanel();
        cemiterioPainel.setBackground(Color.RED);



        gamePanel.add(cemiterioPainel,c);

        //painel da mão do jogador 1 (painel de baixo)
        JPanel maoJogador1Painel = new JPanel();
        maoJogador1Painel.setBackground(Color.GREEN);




        gamePanel.add(maoJogador1Painel,c);

        //painel da mão do jogador 2 (painel de cima);
        JPanel maoJogador2Painel = new JPanel();
        maoJogador2Painel.setBackground(Color.YELLOW);



        gamePanel.add(maoJogador2Painel,c);









        this.add(gamePanel);
    }
}