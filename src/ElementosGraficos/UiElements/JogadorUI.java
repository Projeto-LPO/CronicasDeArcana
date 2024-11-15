package ElementosGraficos.UiElements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Feiticos.Feitiço;
import MecanicasDeJogo.Jogador;
import MecanicasDeJogo.Abstract.Carta;
import MecanicasDeJogo.Exceptions.ManaInsuficienteException;
import Personagens.Criatura;
import Encantamento.*;

public class JogadorUI extends JPanel {
    private Jogador jogador;
    private JLabel vidaLabel;
    private JLabel manaLabel;
    private JLabel nomeLabel;
    private JLabel nivelLabel;
    private JPanel maoPanel;
    private JPanel campoPanel;
    private JButton comprarCartaButton;

    public JogadorUI(Jogador jogador){
        this.jogador = jogador;
        inicializarComponentes();
        atualizarInformacoes();
    }

    private void inicializarComponentes(){
        setLayout(new BorderLayout());

        JPanel infoPanel = new JPanel(new GridLayout(4,1));
        nomeLabel = new JLabel();
        vidaLabel = new JLabel();
        manaLabel = new JLabel();
        nivelLabel = new JLabel();

        infoPanel.add(nomeLabel);
        infoPanel.add(vidaLabel);
        infoPanel.add(manaLabel);
        infoPanel.add(nivelLabel);

        maoPanel = new JPanel();
        maoPanel.setLayout(new FlowLayout());
        maoPanel.setBorder(BorderFactory.createTitledBorder("Mão"));

        campoPanel = new JPanel();
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
        nomeLabel.setText("Nome: " + jogador.getNome());
        vidaLabel.setText("Vida: " + jogador.getVida());
        manaLabel.setText("Mana: " + jogador.getManaAtual() + "/" + jogador.getMana());
        nivelLabel.setText("Nível: " + jogador.getNivel());
    }

    private void atualizarMao() {
        maoPanel.removeAll();
        for (Carta carta : jogador.getMao().getCartas()) {
            CartaUI cartaUI;

            if (carta instanceof Encantamento ) {
                cartaUI = new EncantamentoUI((Encantamento) carta, carta, jogador);
            } else if (carta instanceof Criatura) {
                cartaUI = new CriaturaUI((Criatura) carta, jogador);
            } else if (carta instanceof Feitiço) {
                cartaUI = new FeitiçoUI((Feitiço) carta, jogador);
            } else {
                continue; // Ignora cartas de tipos desconhecidos
            }

            // Configura a ação de clique para jogar a carta
            cartaUI.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cartaUI.jogarCarta(); // Chama a ação de jogar a carta
                    atualizarCampo();      // Atualiza o campo de batalha
                    atualizarInformacoes();// Atualiza as informações do jogador
                }
            });

            maoPanel.add(cartaUI); // Adiciona a carta na interface de mão
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

