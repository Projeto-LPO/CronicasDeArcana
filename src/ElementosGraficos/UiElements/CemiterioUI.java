package ElementosGraficos.UiElements;

import javax.swing.*;
import MecanicasDeJogo.FluxodeCartas.Cemiterio;
import MecanicasDeJogo.Abstract.Carta;
import MecanicasDeJogo.Jogador;
import Personagens.Criatura;
import java.util.List;
import java.util.ArrayList;
import java.awt.*;
import Encantamento.Encantamento;


public class CemiterioUI {
    private JPanel cemiterioPainel;
    private JPanel campoJogador;

    // Construtor para receber os painéis
    public CemiterioUI(JPanel cemiterioPainel, JPanel campoJogador) {
        this.cemiterioPainel = cemiterioPainel;
        this.campoJogador = campoJogador;
    }

    public void atualizarCemiterio(Jogador jogador) {
        if (cemiterioPainel == null) {
            System.out.println("Erro: cemiterioPainel não foi inicializado.");
            return;
        }

        cemiterioPainel.removeAll();

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.gridy = 0;

        List<Carta> cartasNoCemiterio = jogador.getCemiterio().getCartasNoCemiterio();
        for (int i = 0; i < cartasNoCemiterio.size(); i++) {
            Carta carta = cartasNoCemiterio.get(i);
            Component cartaUI = new CartaUI(carta, jogador);
            c.gridx = i;
            cemiterioPainel.add(cartaUI, c);
        }

        cemiterioPainel.revalidate();
        cemiterioPainel.repaint();
    }

    public void atualizarCampoDeBatalha(Jogador jogador) {
        if (campoJogador == null) {
            System.out.println("Erro: campoJogador não foi inicializado.");
            return;
        }

        campoJogador.removeAll();

        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;

        List<Criatura> criaturas = jogador.getCampoDeBatalha().getCriaturasNoCampo(jogador);
        for (int i = 0; i < criaturas.size(); i++) {
            Criatura criatura = criaturas.get(i);
            Component criaturaUI = new CartaUI(criatura, jogador);

            c.gridx = i;
            campoJogador.add(criaturaUI, c);
        }

        List<Encantamento> encantamentos = jogador.getCampoDeBatalha().getEncantamentosNoCampo();
        for (int i = 0; i < encantamentos.size(); i++) {
            Encantamento encantamento = encantamentos.get(i);
            Component encantamentoUI = new CartaUI(encantamento, jogador);

            c.gridx = criaturas.size() + i;
            campoJogador.add(encantamentoUI, c);
        }

        campoJogador.revalidate();
        campoJogador.repaint();
    }

    public void removerCriaturaDoCampo(Jogador jogador, Criatura criatura) {
        jogador.getCampoDeBatalha().removerCartaDoCampo(criatura);
        jogador.getCemiterio().adicionarCartasNoCemiterio(criatura);

        atualizarCampoDeBatalha(jogador);
        atualizarCemiterio(jogador);

        System.out.println(criatura.getNome() + " foi removida do campo e adicionada ao cemitério.");
    }
}

