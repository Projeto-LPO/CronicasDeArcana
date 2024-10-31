package ElementosGraficos.UiElements;

import javax.swing.*;
import java.awt.*;
import MecanicasDeJogo.Abstract.Carta;
import MecanicasDeJogo.Jogador;
import Encantamento.Encantamento;

public class EncantamentoUI extends CartaUI {
    private Encantamento encantamento;
    private JLabel nomeLabel;
    private JLabel custoManaLabel;
    private JLabel efeitoContinuoLabel;
    private JLabel aumentoDanoLabel;
    private JLabel aumentoCuraLabel;

    public EncantamentoUI(Encantamento encantamento, Carta carta, Jogador jogador) {
        super(carta, jogador);
        this.encantamento = encantamento;
        configurarComponentes();
    }

    private void configurarComponentes() {
        setLayout(new BorderLayout());

        nomeLabel = new JLabel("Nome: " + encantamento.getNome());
        custoManaLabel = new JLabel("Custo de Mana: " + encantamento.getCustoMana());
        efeitoContinuoLabel = new JLabel("Efeito Contínuo: " + encantamento.getEfeitoContínuo());
        aumentoDanoLabel = new JLabel("Aumento de Dano: " + encantamento.getAumentoDano());
        aumentoCuraLabel = new JLabel("Aumento de Cura: " + encantamento.getAumentoCura());

        JPanel infoPanel = new JPanel(new GridLayout(5, 1));
        infoPanel.add(nomeLabel);
        infoPanel.add(custoManaLabel);
        infoPanel.add(efeitoContinuoLabel);
        infoPanel.add(aumentoDanoLabel);
        infoPanel.add(aumentoCuraLabel);

        add(infoPanel, BorderLayout.CENTER); // Certifique-se de adicionar o painel ao layout principal
    }

    @Override
    protected void jogarCarta() {
        super.jogarCarta();
        // Lógica específica para o encantamento, se necessário
    }

    public Encantamento getEncantamento() {
        return encantamento;
    }
}
