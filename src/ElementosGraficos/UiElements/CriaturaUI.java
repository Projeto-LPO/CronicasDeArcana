package ElementosGraficos.UiElements;

import javax.swing.*;
import java.awt.*;
import MecanicasDeJogo.Abstract.Carta;
import Personagens.Criatura; // Importando a classe Criatura
import MecanicasDeJogo.Jogador; // Importando a classe Jogador

public class CriaturaUI extends CartaUI {
    private Criatura criatura; // Referência à criatura
    private JLabel nomeLabel;
    private JLabel custoManaLabel;
    private JLabel poderLabel;
    private JLabel resistenciaLabel;
    private JLabel habilidadeLabel;

    public CriaturaUI(Criatura criatura, Jogador jogador) {
        super(criatura, jogador); // Chama o construtor da classe pai
        this.criatura = criatura;
        configurarComponentes();
    }

    private void configurarComponentes() {
        setLayout(new BorderLayout());

        // Cria labels para exibir informações da criatura
        nomeLabel = new JLabel("Nome: " + criatura.getNome());
        custoManaLabel = new JLabel("Custo de Mana: " + criatura.getCustoMana());
        poderLabel = new JLabel("Poder: " + criatura.getPoder());
        resistenciaLabel = new JLabel("Resistência: " + criatura.getResistencia());
        habilidadeLabel = new JLabel("Habilidade Especial: " + criatura.getHabilidadeEspecial());

        // Cria um painel para organizar as labels verticalmente
        JPanel infoPanel = new JPanel(new GridLayout(5, 1));
        infoPanel.add(nomeLabel);
        infoPanel.add(custoManaLabel);
        infoPanel.add(poderLabel);
        infoPanel.add(resistenciaLabel);
        infoPanel.add(habilidadeLabel);

        // Adiciona o painel de informações ao painel principal
        add(infoPanel, BorderLayout.CENTER);


        configurarAcaoClique();
    }

    @Override
    public void jogarCarta() {
        super.jogarCarta();

    }

    public Criatura getCriatura() {
        return criatura;
    }
}
