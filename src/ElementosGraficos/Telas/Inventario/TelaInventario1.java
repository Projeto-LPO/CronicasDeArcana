package ElementosGraficos.Telas.Inventario;

import ElementosGraficos.Telas.MenuInicial;
import MecanicasDeJogo.Abstract.Carta;
import MecanicasDeJogo.Jogador;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaInventario1 extends JFrame {
    private Jogador jogador1;
    private Jogador jogador2;

    private JPanel painelCartasDisponiveis;
    private JPanel painelCartasDeck;

    public TelaInventario1(Jogador jogador1, Jogador jogador2) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;

        setTitle("Inventário - " + jogador1.getNome());
        setSize(1000, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painelPrincipal.setBackground(new Color(0, 74, 173));

        // Painel superior com informações
        JPanel painelSuperior = criarPainelSuperior();
        painelPrincipal.add(painelSuperior, BorderLayout.NORTH);

        // Painéis centrais com cartas
        JPanel painelCentral = criarPainelCentral();
        painelPrincipal.add(painelCentral, BorderLayout.CENTER);

        // Painel inferior com botões
        JPanel painelInferior = criarPainelInferior();
        painelPrincipal.add(painelInferior, BorderLayout.SOUTH);

        add(painelPrincipal);
    }

    private JPanel criarPainelSuperior() {
        JPanel painelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelSuperior.setBackground(new Color(242, 213, 174));
        painelSuperior.setPreferredSize(new Dimension(1000, 50));

        JLabel lblInfoJogador = new JLabel("Jogador: " + jogador1.getNome() + " | Nível: " + jogador1.getNivel());
        lblInfoJogador.setFont(new Font("Arial", Font.BOLD, 18));
        painelSuperior.add(lblInfoJogador);

        return painelSuperior;
    }

    private JPanel criarPainelCentral() {
        JPanel painelCentral = new JPanel(new GridLayout(1, 2, 20, 0));
        painelCentral.setBackground(new Color(0, 74, 173));

        // Painel de cartas disponíveis (com grade 8x5 fixa)
        painelCartasDisponiveis = new JPanel(new GridLayout(8, 5, 5, 5));
        painelCartasDisponiveis.setBackground(new Color(242, 213, 174));
        inicializarPainelCartasDisponiveis();

        JScrollPane scrollDisponiveis = new JScrollPane(painelCartasDisponiveis);
        scrollDisponiveis.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollDisponiveis.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollDisponiveis.setBorder(BorderFactory.createTitledBorder("Cartas Disponíveis"));
        painelCentral.add(scrollDisponiveis);

        // Painel de cartas no deck (com grade 6x5 fixa)
        painelCartasDeck = new JPanel(new GridLayout(6, 5, 5, 5));
        painelCartasDeck.setBackground(new Color(149, 124, 90));
        atualizarPainelCartasDeck();

        JScrollPane scrollDeck = new JScrollPane(painelCartasDeck);
        scrollDeck.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollDeck.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollDeck.setBorder(BorderFactory.createTitledBorder("Deck Atual"));
        painelCentral.add(scrollDeck);

        return painelCentral;
    }

    private JPanel criarPainelInferior() {
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelInferior.setBackground(new Color(242, 213, 174));

        JButton btnSalvar = new JButton("Salvar Deck");
        btnSalvar.addActionListener(e -> {
            jogador1.salvarDeck();
            JOptionPane.showMessageDialog(this, "Deck salvo com sucesso!");
        });
        painelInferior.add(btnSalvar);

        JButton btnDescartar = new JButton("Descartar Alterações");
        btnDescartar.addActionListener(e -> atualizarPainel());
        painelInferior.add(btnDescartar);

        JButton btnTrocarJogador = new JButton("Trocar Jogador");
        btnTrocarJogador.addActionListener(e -> {
            dispose();
            new TelaInventario1(jogador2, jogador1).setVisible(true);
        });
        painelInferior.add(btnTrocarJogador);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            new MenuInicial(jogador1, jogador2).setVisible(true);
            this.dispose();
        });
        painelInferior.add(btnVoltar);

        return painelInferior;
    }

    private void inicializarPainelCartasDisponiveis() {
        painelCartasDisponiveis.removeAll();

        for (int i = 0; i < 40; i++) {
            JButton btnVazio = criarBotaoVazio();
            painelCartasDisponiveis.add(btnVazio);
        }

        painelCartasDisponiveis.revalidate();
        painelCartasDisponiveis.repaint();
    }

    private void atualizarPainelCartasDisponiveis() {
        painelCartasDisponiveis.removeAll();

        List<Carta> cartasDisponiveis = jogador1.getCartasDisponiveis();
        if (cartasDisponiveis == null) {
            cartasDisponiveis = List.of();
        }

        int index = 0;
        for (Carta carta : cartasDisponiveis) {
            JButton btnCarta = criarBotaoCarta(carta, true);
            painelCartasDisponiveis.add(btnCarta);
            index++;
        }

        for (int i = index; i < 40; i++) {
            JButton btnVazio = criarBotaoVazio();
            painelCartasDisponiveis.add(btnVazio);
        }

        painelCartasDisponiveis.revalidate();
        painelCartasDisponiveis.repaint();
    }

    private void atualizarPainelCartasDeck() {
        painelCartasDeck.removeAll();

        List<Carta> cartasDeck = jogador1.getDeck().getCartas();
        if (cartasDeck == null) {
            cartasDeck = List.of();
        }

        int index = 0;
        for (Carta carta : cartasDeck) {
            JButton btnCarta = criarBotaoCarta(carta, false);
            painelCartasDeck.add(btnCarta);
            index++;
        }

        for (int i = index; i < 30; i++) {
            JButton btnVazio = criarBotaoVazio();
            painelCartasDeck.add(btnVazio);
        }

        painelCartasDeck.revalidate();
        painelCartasDeck.repaint();
    }

    private JButton criarBotaoVazio() {
        JButton btnVazio = new JButton();
        btnVazio.setPreferredSize(new Dimension(70, 50));
        btnVazio.setBackground(new Color(242, 213, 174));
        btnVazio.setEnabled(false);
        return btnVazio;
    }

    private JButton criarBotaoCarta(Carta carta, boolean disponivel) {
        JButton btnCarta = new JButton("<html><center>" + carta.getNome() + "</center></html>");
        btnCarta.setToolTipText("Custo de Mana: " + carta.getCustoMana());

        // Ajuste no layout do botão
        btnCarta.setPreferredSize(new Dimension(70, 50)); // Tamanho fixo do botão
        btnCarta.setBackground(new Color(143, 116, 84));
        btnCarta.setForeground(Color.WHITE);
        btnCarta.setFont(new Font("Arial", Font.PLAIN, 12)); // Fonte reduzida para melhor adaptação
        btnCarta.setFocusPainted(false);
        btnCarta.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        // Alinha o texto no centro e permite múltiplas linhas
        btnCarta.setHorizontalAlignment(SwingConstants.CENTER);
        btnCarta.setVerticalAlignment(SwingConstants.CENTER);

        btnCarta.addActionListener(e -> {
            if (disponivel) {
                if (jogador1.getDeck().getTamanho() < 30) {
                    jogador1.getDeck().adicionarCarta(carta);
                    jogador1.getCartasDisponiveis().remove(carta);
                    atualizarPainel();
                } else {
                    JOptionPane.showMessageDialog(this, "O deck já contém 30 cartas!");
                }
            } else {
                jogador1.getDeck().getCartas().remove(carta);
                jogador1.getCartasDisponiveis().add(carta);
                atualizarPainel();
            }
        });

        return btnCarta;
    }


    private void atualizarPainel() {
        atualizarPainelCartasDisponiveis();
        atualizarPainelCartasDeck();
    }
}
