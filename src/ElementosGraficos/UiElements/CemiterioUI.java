package ElementosGraficos.UiElements;

import javax.swing.*;
import MecanicasDeJogo.FluxodeCartas.Cemiterio;
import MecanicasDeJogo.Abstract.Carta;
import MecanicasDeJogo.Jogador;
import java.util.List;
import java.util.ArrayList;
import java.awt.*;


public class CemiterioUI{
    public void atualizarCemiterio(JPanel cemiterioPainel,  List<Carta> cartas) {
        cemiterioPainel.removeAll();
        for(Carta carta : cartas){

            CartaUI cartaUI = new CartaUI(carta, null);
            cartaUI.setPreferredSize(new Dimension(50, 70));
            cemiterioPainel.add(cartaUI);

        }
        cemiterioPainel.revalidate();
        cemiterioPainel.repaint();

    }}