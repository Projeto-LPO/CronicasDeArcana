package ElementosGraficos.UiElements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import MecanicasDeJogo.Abstract.Carta;
import MecanicasDeJogo.Jogador;
import MecanicasDeJogo.Exceptions.ManaInsuficienteException;

public class CartaUI extends JButton {
    private Carta carta;
    private Jogador jogador;

    public CartaUI(Carta carta, Jogador jogador) {
        this.carta = carta;
        this.jogador = jogador;
        configurarBotaoCarta();
        configurarAcaoClique();
    }

    private void configurarBotaoCarta() {
        setPreferredSize(new Dimension(62, 80));
        setFont(new Font("Arial", Font.PLAIN, 9));
        setBackground(new Color(200, 180, 150));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        atualizarTextoBotao();
    }

    private void atualizarTextoBotao() {
        setText("<html><center>" + carta.getNome() + "</center></html>");
    }

    protected void configurarAcaoClique() {
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jogarCarta();
            }
        });
    }

    protected void jogarCarta() {
        try {
            jogador.jogarCartaNoCampo(carta);
            setEnabled(false);
            System.out.println(carta.getNome() + " foi jogada.");
        } catch (ManaInsuficienteException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Carta getCarta() {
        return carta;
    }
}
