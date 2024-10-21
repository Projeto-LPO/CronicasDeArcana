package Telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuInicial extends JFrame {

    public MenuInicial() {
        //painel principal
        this.setTitle("Cronicas de Arcana");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 700, 600);
        this.setVisible(true);
        this.setResizable(false);

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

        tituloPrincipal.setFont(new Font("Uncial Antiqua", Font.BOLD, 15));
        tituloPrincipal.setForeground(Color.GRAY);
        menuInicial.add(tituloPrincipal, BorderLayout.NORTH);

        //painel dos botoes
        JPanel btnPainel = new JPanel(new GridLayout(3,1));
        btnPainel.setBackground(new Color(0xFFFFF));
        menuInicial.add(btnPainel, BorderLayout.CENTER);

        //botoes da tela de inicio (jogar, inventario e sair)
            //jogar
            JButton btnJogar = new JButton("Jogar");
            btnJogar.setBackground(new Color(0xFFFFF));
            btnJogar.setForeground(Color.GRAY);
            btnJogar.setFont(new Font("Uncial Antiqua", Font.BOLD, 15));
            btnPainel.add(btnJogar);

            //inventario
            JButton btnInventario = new JButton("Inventário");
            btnInventario.setBackground(new Color(0xFFFFF));
            btnInventario.setForeground(Color.GRAY);
            btnInventario.setFont(new Font("Uncial Antiqua", Font.BOLD, 15));
            btnPainel.add(btnInventario);

            //sair
            JButton btnSair = new JButton("Sair");
            btnSair.setBackground(new Color(0xFFFFF));
            btnSair.setForeground(Color.GRAY);
            btnSair.setFont(new Font("Uncial Antiqua", Font.BOLD, 15));
            btnPainel.add(btnSair);

        //metodos action listener
            //jogar
            // btnJogar.addActionListener(new ActionListener() {})

            //inventario
            btnInventario.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose(); // Fecha a tela de título
                    new TelaInventario().setVisible(true); //abre a tela de inventario
            }
            });

            //sair
            btnSair.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });


    }
}
