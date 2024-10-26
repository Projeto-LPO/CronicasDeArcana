package Telas;

import MecanicasDeJogo.Jogador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class MenuInicial extends JFrame {

    public MenuInicial() {
        //painel principal
        this.setTitle("Cronicas de Arcana");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 1000, 750);
        this.setVisible(true);
        this.setResizable(true);

        JPanel menuInicial = new JPanel(new BorderLayout()) {
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

        //titulo inicial
        JLabel tituloPrincipal = new JLabel("Cronicas de Arcana");
        tituloPrincipal.setFont(new Font("Uncial Antiqua", Font.BOLD, 45));
        tituloPrincipal.setForeground(Color.GRAY);
        menuInicial.add(tituloPrincipal, BorderLayout.NORTH);
        tituloPrincipal.setPreferredSize(new Dimension(100, 100));
        tituloPrincipal.setHorizontalAlignment(SwingConstants.CENTER);

        //painel dos botoes
        JPanel btnPainel = new JPanel(new GridLayout(3,1,30,30));
        btnPainel.setPreferredSize(new Dimension(200, 200));
        btnPainel.setBorder(BorderFactory.createEmptyBorder(50, 150, 50, 150));
        btnPainel.setOpaque(false);
        menuInicial.add(btnPainel, BorderLayout.CENTER);

        //botoes da tela de inicio (jogar, inventario e sair)
        //jogar
        JButton btnJogar = new JButton("Jogar");
        btnJogar.setBackground(Color.lightGray);
        btnJogar.setForeground(Color.BLACK);
        btnJogar.setPreferredSize(new Dimension(70, 50));
        btnJogar.setFont(new Font("Uncial Antiqua", Font.BOLD, 20));
        btnJogar.setFocusPainted(false);
        btnPainel.add(btnJogar);

        //inventario
        JButton btnInventario = new JButton("Inventário");
        btnInventario.setBackground(Color.lightGray);
        btnInventario.setForeground(Color.BLACK);
        btnInventario.setPreferredSize(new Dimension(70, 50));
        btnInventario.setFont(new Font("Uncial Antiqua", Font.BOLD, 20));
        btnInventario.setFocusPainted(false);
        btnPainel.add(btnInventario);

        //sair
        JButton btnSair = new JButton("Sair");
        btnSair.setBackground(Color.lightGray);
        btnSair.setForeground(Color.BLACK);
        btnSair.setPreferredSize(new Dimension(70, 50));
        btnSair.setFont(new Font("Uncial Antiqua", Font.BOLD, 20));
        btnSair.setFocusPainted(false);
        btnPainel.add(btnSair);

        //metodos action listener
        //jogar
        btnJogar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Iniciando o jogo...");
                dispose();                                      //fecha a tela de titulo
                new JogoTela().setVisible(true);                //abre a tela de jogo
            }
        });

        //inventario
        btnInventario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new TelaInventario().setVisible(true);
            }
        });

        //sair
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        this.add(menuInicial);

    }
}