package ElementosGraficos.UiElements;

import ElementosGraficos.Telas.TelaFinal;
import ElementosGraficos.UiElements.CartaUI;
import Encantamento.Encantamento;
import Encantamento.EncantamentoDano;
import Feiticos.Feitiço;
import Feiticos.FeitiçoCura;
import Feiticos.FeitiçoDano;
import MecanicasDeJogo.Abstract.Carta;
import MecanicasDeJogo.Exceptions.ManaInsuficienteException;
import MecanicasDeJogo.FluxodeCartas.Decks;
import MecanicasDeJogo.FluxodeCartas.InstanciaCartas;
import MecanicasDeJogo.Jogador;
import Personagens.Criatura;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;


public class JogoUI extends Component {
    private Jogador jogador1;
    private Jogador jogador2;
    private boolean turnoJogador1;
    private GerenciadorDeCombate gerenciadorDeCombate;
    private Decks deckJogador1;
    private Decks deckJogador2;
    private JButton btnCompra1;
    private JButton btnCompra2;
    private JButton cartaUI;
    private JButton btnFinalizarTurno1;
    private JButton btnFinalizarTurno2;

    public JogoUI(Jogador jogador1, Jogador jogador2) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;

        List<Carta> cartasCriadas = InstanciaCartas.gerarCartas();

        this.deckJogador1 = new Decks(cartasCriadas);
        this.deckJogador2 = new Decks(cartasCriadas);
        deckJogador1.embaralhar();
        deckJogador2.embaralhar();
        this.gerenciadorDeCombate = new GerenciadorDeCombate();
    }

    public void adicionarCartas() {
        for (int i = 0; i < 5; i++) {
            jogador1.getMao().adicionarCartasMao(deckJogador1.comprarCarta());
            jogador2.getMao().adicionarCartasMao(deckJogador2.comprarCarta());
        }
    }

    public void definirPrimeiroJogador() {
        Random random = new Random();
        turnoJogador1 = random.nextBoolean();
        String primeiroJogador = turnoJogador1 ? jogador1.getNome() : jogador2.getNome();
        JOptionPane.showMessageDialog(this, "O jogador " + primeiroJogador + " começa!");
    }

    public void alternarTurno() {
        turnoJogador1 = !turnoJogador1;
    }

    public void iniciarTurno() {
        Jogador jogadorAtual = turnoJogador1 ? jogador1 : jogador2;


        if (jogadorAtual.getMana() < 10) {
            jogadorAtual.incrementarMana();
        }

        configurarBotoesDeTurno(turnoJogador1);


        verificarEncantamentos();


        executarFaseDeCombate(jogadorAtual, turnoJogador1 ? jogador2 : jogador1);


        if (verificarVitoria(jogadorAtual)) {
            return;
        }


        atualizarInterface();
    }

    private boolean verificarVitoria(Jogador jogador) {
        if (jogador.getVida() <= 0 || jogador.getDeck().isEmpty()) {
            System.out.println(jogador.getNome() + " perdeu o jogo.");

            SwingUtilities.invokeLater(() -> {
                TelaFinal telaFinal = new TelaFinal();
                telaFinal.setVisible(true);
            });
            return true;
        }
        return false;
    }

    private void configurarBotoesDeTurno(boolean turnoJogador1) {
        btnCompra1.setEnabled(turnoJogador1);
        btnCompra2.setEnabled(!turnoJogador1);
        btnFinalizarTurno1.setEnabled(turnoJogador1);
        btnFinalizarTurno2.setEnabled(!turnoJogador1);
    }

    public void verificarEncantamentos() {
        List<Encantamento> encantamentosJogador1 = jogador1.getCampoDeBatalha().getEncantamentosNoCampo();
        gerenciadorDeCombate.verificarDuracaoEncantamentos(encantamentosJogador1, jogador1);

        List<Encantamento> encantamentosJogador2 = jogador2.getCampoDeBatalha().getEncantamentosNoCampo();
        gerenciadorDeCombate.verificarDuracaoEncantamentos(encantamentosJogador2, jogador2);
    }

    private void executarFaseDeCombate(Jogador jogadorAtual, Jogador jogadorAdversario) {
        gerenciadorDeCombate.executarFaseDeCombate(jogadorAtual, jogadorAdversario);
    }

    public void atualizarInterface() {

    }

    public void iniciarLoopDeJogo() {

        definirPrimeiroJogador();
        while (true) {
            iniciarTurno();
            if (verificarVitoria(jogador1) || verificarVitoria(jogador2)) {
                break;
            }
            alternarTurno();
        }
    }

    public void atualizarPainelDoJogador(JPanel infoJogadorPanel, Jogador jogador) {
        infoJogadorPanel.removeAll();
        infoJogadorPanel.add(new JLabel("Nome: " + jogador.getNome()));
        infoJogadorPanel.add(new JLabel(" Vida: " + jogador.getVida()));
        infoJogadorPanel.add(new JLabel("Mana: " + jogador.getMana()));
        infoJogadorPanel.revalidate();
        infoJogadorPanel.repaint();
    }
}