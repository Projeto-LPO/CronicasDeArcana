import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TelaInicial extends JPanel {
    private JButton iniciarJogoButton;
    private JButton sairButton;

    public TelaInicial() {
        setPreferredSize(new Dimension(GamePanel.WIDTH, GamePanel.HEIGHT));
        setLayout(new GridBagLayout());
        setBackground(Color.DARK_GRAY);

        // Inicializa os botões
        iniciarJogoButton = new JButton("Iniciar Jogo");
        sairButton = new JButton("Sair");

        // Aumenta o tamanho da fonte dos botões
        Font buttonFont = new Font("Arial", Font.BOLD, 24);
        iniciarJogoButton.setFont(buttonFont);
        sairButton.setFont(buttonFont);

        // Define o tamanho dos botões
        Dimension buttonSize = new Dimension(300, 80);
        iniciarJogoButton.setPreferredSize(buttonSize);
        sairButton.setPreferredSize(buttonSize);

        // Configura o layout dos botões
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);  // Margens ao redor dos botões

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(iniciarJogoButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(sairButton, gbc);

        // Ação do botão de Iniciar Jogo
        iniciarJogoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Código para iniciar o jogo
                System.out.println("Iniciando o jogo...");
                JFrame janela = (JFrame) SwingUtilities.getWindowAncestor(TelaInicial.this);
                janela.setContentPane(new GamePanel());
                janela.revalidate();
            }
        });

        // Ação do botão de Sair
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Sai do programa
            }
        });
    }
}
