package ElementosGraficos.UiElements;

import Feiticos.Feitiço;
import MecanicasDeJogo.Abstract.Carta;
import MecanicasDeJogo.Jogador;

import javax.swing.*;
import java.awt.*;

public class FeitiçoUI extends CartaUI{
    private Feitiço feitiço;
    private JLabel nomeLabel;
    private JLabel custoManaLabel;
    private JLabel efeitoLabel;
    private  JLabel valorDanoLabel;
    private JLabel valorCuraLabel;

    public FeitiçoUI(Feitiço feitiço, Carta carta, Jogador jogador) {
        super(carta, jogador);
        this.feitiço = feitiço;
        configurarComponentes();
    }
    private void configurarComponentes(){
        setLayout(new BorderLayout());

        nomeLabel = new JLabel("Nome: " + feitiço.getNome());
        custoManaLabel = new JLabel("Custo de Mana: " + feitiço.getCustoMana());
        efeitoLabel = new JLabel("Efeito: " + feitiço.getEfeito());
        valorDanoLabel = new JLabel("Dano: " + feitiço.getValorDano());
        valorCuraLabel = new JLabel("Cura: " + feitiço.getValorCura());

        JPanel infoPanel = new JPanel(new GridLayout(5,1));

        infoPanel.add(nomeLabel);
        infoPanel.add(custoManaLabel);
        infoPanel.add(efeitoLabel);
        infoPanel.add(valorDanoLabel);
        infoPanel.add(valorCuraLabel);

        add(infoPanel, BorderLayout.CENTER);
        configurarAcaoClique();

    }

}
