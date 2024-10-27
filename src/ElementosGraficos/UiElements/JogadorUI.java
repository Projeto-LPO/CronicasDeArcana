package ElementosGraficos.UiElements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import MecanicasDeJogo.Jogador;
import MecanicasDeJogo.Abstract.Carta;
import MecanicasDeJogo.Exceptions.ManaInsuficienteException;

public class JogadorUI extends JPanel {
    private Jogador jogador;
    private JLabel vidaLabel;
    private JLabel manaLabel;
    private JLabel maoPanel;
    private JLabel campoPanel;
    private JButton comprarCartaButton;

    public JogadorUI(Jogador jogador){
        this.jogador = jogador;
        inicializarComponentes();
        atualizarInformacoes();
    }
    private void inicializarComponentes(){
        setLayout(new BorderLayout());

        JPanel infoPanel = new JPanel(new GridLayout(1,2));
        vidaLabel = new JLabel();
        manaLabel = new JLabel();
        infoPanel.add(vidaLabel);
        infoPanel.add(manaLabel);

        maoPanel = new JLabel();
        maoPanel.setLayout(new FlowLayout());
        maoPanel.setBorder(BorderFactory.createTitledBorder("Mão"));

        campoPanel = new JLabel();
        campoPanel.setLayout(new FlowLayout());
        campoPanel.setBorder(BorderFactory.createTitledBorder("Campo de Batalha"));

        comprarCartaButton = new JButton("Comprar Carta");
        comprarCartaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jogador.comprarCartas();
                atualizarMao();
                atualizarInformacoes();
            }
        });

        // Adiciona os componentes ao painel principal
        add(infoPanel, BorderLayout.NORTH);
        add(maoPanel, BorderLayout.CENTER);
        add(campoPanel, BorderLayout.SOUTH);
        add(comprarCartaButton, BorderLayout.EAST);
    }

    private void atualizarInformacoes(){
        vidaLabel.setText("Vida: " + jogador.getVida());
        manaLabel.setText("Mana: " + jogador.getManaAtual() + "/" + jogador.getMana());
    }

    private void atualizarMao() {
        maoPanel.removeAll();
        for (Carta carta : jogador.getMao().getCartas()) {
            JButton cartaButton = new JButton(carta.getNome());
            cartaButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        jogador.jogarCartaNoCampo(carta);
                        atualizarCampo();
                        atualizarInformacoes();
                    } catch (ManaInsuficienteException ex) {
                        JOptionPane.showMessageDialog(null, "Mana insuficiente para jogar " + carta.getNome());
                    }
                }
            });
            maoPanel.add(cartaButton);
        }
        maoPanel.revalidate();
        maoPanel.repaint();
    }

    private void atualizarCampo() {
        campoPanel.removeAll();
        for (Carta carta : jogador.getCampoDeBatalha().getCampo()) {
            JLabel cartaLabel = new JLabel(carta.getNome());
            campoPanel.add(cartaLabel);
        }
        campoPanel.revalidate();
        campoPanel.repaint();
    }

}
