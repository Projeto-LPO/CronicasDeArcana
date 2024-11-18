package ElementosGraficos.UiElements;

import ElementosGraficos.UiElements.CartaUI;
import Encantamento.Encantamento;
import MecanicasDeJogo.Abstract.Carta;
import MecanicasDeJogo.Exceptions.ManaInsuficienteException;
import MecanicasDeJogo.Jogador;
import Personagens.Criatura;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
public class MaoUI {
    public void atualizarMao(JPanel maoPainel, JPanel campoPainel, Jogador jogador, Jogador jogadorOponente) {
        maoPainel.removeAll();

        GridBagConstraints c2 = new GridBagConstraints();
        c2.insets = new Insets(10, 10, 10, 10);
        c2.gridy = 0;

        for (int i = 0; i < 5; i++) {
            Component cartaUI;

            if (i < jogador.getMao().getCartas().size()) {
                Carta carta = jogador.getMao().getCartas().get(i);
                cartaUI = new CartaUI(carta, jogador);

                ((CartaUI) cartaUI).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            // Joga a carta no campo
                            jogador.jogarCartaNoCampo(carta, jogadorOponente);

                            // Atualiza o campo de batalha usando o método existente
                            atualizarCampoDeBatalha(campoPainel, jogador);

                            // Verifica se o deck não está vazio e então compra uma carta
                            if (!jogador.getDeck().isEmpty()) {
                                Carta novaCarta = jogador.getDeck().comprarCarta();
                                jogador.getMao().getCartas().add(novaCarta);
                            }

                            // Atualiza o painel da mão
                            atualizarMao(maoPainel, campoPainel, jogador, jogadorOponente);
                        } catch (ManaInsuficienteException ex) {
                            JOptionPane.showMessageDialog(null, "Mana insuficiente para jogar esta carta!");
                        }
                    }
                });
            } else {
                cartaUI = new JButton("Vazio");
            }

            c2.gridx = i;
            maoPainel.add(cartaUI, c2);
        }

        maoPainel.revalidate();
        maoPainel.repaint();
    }

    public void atualizarCampoDeBatalha(JPanel campoJogador, Jogador jogador) {
        campoJogador.removeAll();  // Limpa o painel do campo de batalha do jogador

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.gridy = 0;

        // Adiciona cada criatura ativa no campo de batalha do jogador ao painel
        List<Criatura> criaturas = jogador.getCampoDeBatalha().getCriaturasNoCampo(jogador);
        for (int i = 0; i < criaturas.size(); i++) {
            Criatura criatura = criaturas.get(i);
            Component criaturaUI = new CartaUI(criatura, jogador);

            c.gridx = i;
            campoJogador.add(criaturaUI, c);
        }

        // Adiciona cada encantamento ativo no campo de batalha do jogador ao painel
        List<Encantamento> encantamentos = jogador.getCampoDeBatalha().getEncantamentosNoCampo();
        for (int i = 0; i < encantamentos.size(); i++) {
            Encantamento encantamento = encantamentos.get(i);
            Component encantamentoUI = new CartaUI(encantamento, jogador);

            c.gridx = criaturas.size() + i;
            campoJogador.add(encantamentoUI, c);
        }

        // Atualiza o painel
        campoJogador.revalidate();
        campoJogador.repaint();
    }}


