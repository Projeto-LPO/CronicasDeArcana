package ElementosGraficos.UiElements;

import javax.swing.*;
import MecanicasDeJogo.FluxodeCartas.CampodeBatalha;
import MecanicasDeJogo.Abstract.Carta;
import MecanicasDeJogo.Jogador;
import Personagens.Criatura;
import Encantamento.Encantamento;
import java.awt.*;
import java.util.List;

public class CampodeBatalhaUI{

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

