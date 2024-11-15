package ElementosGraficos.UiElements;

import ElementosGraficos.UiElements.CartaUI;
import MecanicasDeJogo.Abstract.Carta;
import MecanicasDeJogo.Exceptions.ManaInsuficienteException;
import MecanicasDeJogo.Jogador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
public class MaoUI{
public void atualizarMao(JPanel maoPainel, Jogador jogador) {
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
                        jogador.jogarCartaNoCampo(carta);

                        // Verifica se o deck não está vazio e então compra uma carta
                        if (!jogador.getDeck().isEmpty()) {
                            Carta novaCarta = jogador.getDeck().comprarCarta();

                            // Adiciona a nova carta à mão do jogador
                            jogador.getMao().getCartas().add(novaCarta);
                        }
                        atualizarMao(maoPainel, jogador);
                    } catch (ManaInsuficienteException ex) {
                        throw new RuntimeException(ex);
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
}}

