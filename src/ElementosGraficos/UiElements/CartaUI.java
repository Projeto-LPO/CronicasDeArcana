package ElementosGraficos.UiElements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import MecanicasDeJogo.Abstract.Carta;
import MecanicasDeJogo.Jogador; // Importando a classe Jogador
import MecanicasDeJogo.Exceptions.ManaInsuficienteException; // Importando exceção

public class CartaUI extends JButton {
    private Carta carta;
    private Jogador jogador; // Referência ao jogador

    public CartaUI(Carta carta, Jogador jogador) {
        this.carta = carta;
        this.jogador = jogador; // Armazena a referência ao jogador
        configurarBotaoCarta();
        configurarAcaoClique();
    }

    private void configurarBotaoCarta() {
        setText("<html><center>" + carta.getNome() + "<br>Custo de Mana: " + carta.getCustoMana() + "</center></html>");
        setPreferredSize(new Dimension(100, 150));
        setFont(new Font("Arial", Font.BOLD, 12));
        setBackground(new Color(200, 180, 150));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }

    protected void configurarAcaoClique() {
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jogarCarta(); // Chama o método para jogar a carta
            }
        });
    }

    protected void jogarCarta() {
        try {
            jogador.jogarCartaNoCampo(carta); // Jogar a carta utilizando a lógica do jogador
            setEnabled(false); // Desabilita o botão após jogar
            System.out.println(carta.getNome() + " foi jogada."); // Feedback no console
        } catch (ManaInsuficienteException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); // Mensagem de erro
        }
    }

    public Carta getCarta() {
        return carta;
    }
}
