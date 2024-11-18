package ElementosGraficos.Telas;

import ElementosGraficos.UiElements.CartaUI;
import ElementosGraficos.UiElements.CemiterioUI;
import ElementosGraficos.UiElements.GerenciadorDeCombate;
import ElementosGraficos.UiElements.MaoUI;
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
import Encantamento.EncantamentoCura;

public class JogoTela extends JFrame {
    private Jogador jogador1;
    private Jogador jogador2;
    private Decks deckJogador1;
    private Decks deckJogador2;
    private boolean turnoJogador1; //(true para jogador 1, false para jogador 2)
    private JButton btnCompra1;
    private JButton btnCompra2;
    private JButton cartaUI;
    private JButton btnFinalizarTurno1;
    private JButton btnFinalizarTurno2;
    private GerenciadorDeCombate gerenciador;


    public JogoTela(Jogador jogador1, Jogador jogador2) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;

        //criação dos decks
        GerenciadorDeCombate GerenciadorDeCombate = new GerenciadorDeCombate();

        List<Carta> cartasCriadas = InstanciaCartas.gerarCartas();

        this.deckJogador1 = new Decks(cartasCriadas);
        this.deckJogador2 = new Decks(cartasCriadas);
        this.deckJogador1.embaralhar();
        this.deckJogador2.embaralhar();
        MaoUI maoUI = new MaoUI();
        CemiterioUI cemiterioUI= new CemiterioUI();

        //adiciona as cartas na mão dos jogadores no inicio do jogo
        for (int i = 0; i < 5; i++) {
            jogador1.getMao().adicionarCartasMao(deckJogador1.comprarCarta());
            jogador2.getMao().adicionarCartasMao(deckJogador2.comprarCarta());
        }

        //cria a dinamica de turnos
        Random random = new Random();
        turnoJogador1 = random.nextBoolean();
        String primeiroJogador = turnoJogador1 ? jogador1.getNome() : jogador2.getNome();
        JOptionPane.showMessageDialog(this, "O jogador " + primeiroJogador + " começa!");

        //---------------------------------------------------------------------------------------------------------------

        //INCIO DAS CONFIGURAÇÕES VISUAIS + BOTOES DE CARTA E COMPRA DE CARTA

        //configuração da tela principal
        this.setTitle("Partida iniciada! | " + this.jogador1.getNome() + " versus " + this.jogador2.getNome());
        this.setResizable(false);
        this.setBounds(100, 100, 1200, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel gamePanel = new JPanel(new GridBagLayout());
        gamePanel.setBackground(new Color(0, 0, 128));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        // Campo de batalha
        JPanel campoDeBatalhaPainel = new JPanel(new BorderLayout());
        campoDeBatalhaPainel.setPreferredSize(new Dimension(500, 400));
        campoDeBatalhaPainel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        campoDeBatalhaPainel.setBackground(new Color(139, 69, 19));

        //campo jogador 1
        JPanel campoJogador1 = new JPanel();
        campoJogador1.setPreferredSize(new Dimension(540, 180));
        campoJogador1.setBackground(Color.WHITE);

        //campo jogador 2
        JPanel campoJogador2 = new JPanel();
        campoJogador2.setPreferredSize(new Dimension(540, 180));
        campoJogador2.setBackground(Color.WHITE);

        //adiciona os campos individuais ao campo principal
        campoDeBatalhaPainel.add(campoJogador1, BorderLayout.SOUTH);
        campoDeBatalhaPainel.add(campoJogador2, BorderLayout.NORTH);

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        gamePanel.add(campoDeBatalhaPainel, c);

        // Cemitério
        JPanel cemiterioPainel = new JPanel(new BorderLayout());
        cemiterioPainel.setPreferredSize(new Dimension(200, 300));
        cemiterioPainel.setBackground(Color.GRAY);
        cemiterioPainel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        //cemiterio jogador 1
        JPanel cemiterioJogador1 = new JPanel();
        cemiterioJogador1.setPreferredSize(new Dimension(180, 200));
        cemiterioJogador1.setBackground(Color.WHITE);

        //cemiterio jogador 2
        JPanel cemiterioJogador2 = new JPanel();
        cemiterioJogador2.setPreferredSize(new Dimension(180, 200));
        cemiterioJogador2.setBackground(Color.WHITE);

        //adiciona os cemiterios
        cemiterioPainel.add(cemiterioJogador1, BorderLayout.SOUTH);
        cemiterioPainel.add(cemiterioJogador2, BorderLayout.NORTH);

        c.gridx = 3;
        c.gridy = 1;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.CENTER;

        //adiciona o cemiterio ao painel principal
        gamePanel.add(cemiterioPainel, c);

        //descrição de carta
        JPanel descricaoPainel = new JPanel();
        descricaoPainel.setPreferredSize(new Dimension(200, 250));
        descricaoPainel.setBackground(new Color(255, 228, 181));
        descricaoPainel.setLayout(new BorderLayout());

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.CENTER;
        gamePanel.add(descricaoPainel, c);

        //painel do Jogador 1
        JPanel jogador1Painel = new JPanel(new BorderLayout());
        jogador1Painel.setPreferredSize(new Dimension(700, 150));
        jogador1Painel.setOpaque(false);

        //painel da MÃO do jogador 1
        JPanel maoJogador1Painel = new JPanel(new GridBagLayout());
        maoJogador1Painel.setPreferredSize(new Dimension(350, 150));
        maoJogador1Painel.setOpaque(false);
        GridBagConstraints c2 = new GridBagConstraints();
        c2.insets = new Insets(10, 10, 10, 10);
        c2.gridy = 0;

        //criação dos slots de carta
        for (int i = 0; i < 5; i++) {
            Component cartaUI; //variavel container
            //verifica se há cartas na mão antes de acessar
            if (i < jogador1.getMao().getCartas().size()) {
                Carta carta = jogador1.getMao().getCartas().get(i);
                cartaUI = new CartaUI(carta, jogador1);
                ((CartaUI)(cartaUI)).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            jogador1.jogarCartaNoCampo(carta, jogador2);
                            maoUI.atualizarCampoDeBatalha(campoJogador1, jogador1);
                            maoUI.atualizarMao(maoJogador1Painel, campoJogador1, jogador1, jogador2);

                        } catch (ManaInsuficienteException ex) {
                            throw new RuntimeException(ex);
                        }
                        System.out.println("Jogador 1 jogou a carta: " + carta.getNome());

                    }
                });
            } else {
                cartaUI = new JButton("Vazio"); //adiciona um botão vazio caso não haja carta na posição i
            }
            //define a posição do componente e adiciona ao painel
            c2.gridx = i;
            maoJogador1Painel.add(cartaUI, c2);
        }

        //adiciona o painel da MÃO ao painel do jogador1
        jogador1Painel.add(maoJogador1Painel, BorderLayout.CENTER);

        //painel do DECK do jogador1
        JPanel deckJogador1Painel = new JPanel(new BorderLayout());
        deckJogador1Painel.setPreferredSize(new Dimension(100, 150));
        deckJogador1Painel.setOpaque(false);
        deckJogador1Painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel nomeDeck = new JLabel();
        deckJogador1Painel.add(nomeDeck, BorderLayout.CENTER);

        //botão de deck (compra)
        JButton btnCompra1 = new JButton();
        btnCompra1.setBackground(Color.WHITE);
        btnCompra1.setPreferredSize(new Dimension(100, 150));

        JLabel nomeDeck1 = new JLabel("Deck1");
        nomeDeck1.setHorizontalAlignment(SwingConstants.CENTER);
        nomeDeck1.setFont(new Font("Uncial Antiqua", Font.BOLD, 10));
        nomeDeck1.setForeground(Color.BLACK);

        btnCompra1.add(nomeDeck1);

        btnCompra1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jogador1.getMao().adicionarCartasMao(deckJogador1.comprarCarta());
            }
        });

        deckJogador1Painel.add(btnCompra1, BorderLayout.CENTER);

        //adiciona o painel do deck no painel de jogador
        jogador1Painel.add(deckJogador1Painel, BorderLayout.EAST);

        JPanel infoJogador1 = new JPanel(new GridLayout(3, 1));
        infoJogador1.setPreferredSize(new Dimension(100, 150));
        infoJogador1.setBackground(Color.CYAN);
        infoJogador1.add(new JLabel("Nome: " + jogador1.getNome()));
        infoJogador1.add(new JLabel("Vida: " + jogador1.getVida()));
        infoJogador1.add(new JLabel("Mana: " + jogador1.getMana()));

        jogador1Painel.add(infoJogador1, BorderLayout.WEST);

        //botao de finalizar o turno do jogador 1
        JButton btnFinalizarTurno1 = new JButton("Finalizar Turno");
        btnFinalizarTurno1.setBackground(Color.LIGHT_GRAY);
        btnFinalizarTurno1.setPreferredSize(new Dimension(120, 50));
        btnFinalizarTurno1.addActionListener(e -> alternarTurno());

        jogador1Painel.add(btnFinalizarTurno1, BorderLayout.SOUTH);

        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.SOUTH;
        gamePanel.add(jogador1Painel, c);

        //painel do Jogador 2
        JPanel jogador2Painel = new JPanel(new BorderLayout());
        jogador2Painel.setPreferredSize(new Dimension(500, 150));
        jogador2Painel.setOpaque(false);

        //painel da MÃO do jogador 2
        JPanel maoJogador2Painel = new JPanel(new GridBagLayout());
        maoJogador2Painel.setPreferredSize(new Dimension(350, 150));
        maoJogador2Painel.setOpaque(false);
        GridBagConstraints c3 = new GridBagConstraints();
        c3.insets = new Insets(10, 10, 10, 10);
        c3.gridy = 0;

        //adicionando as 5 cartas à mão
        for (int i = 0; i < 5; i++) {
            Component cartaUI;
            if (i < jogador2.getMao().getCartas().size()) {
                Carta carta = jogador2.getMao().getCartas().get(i);
                cartaUI = new CartaUI(carta, jogador2);
                ((CartaUI)(cartaUI)).addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            jogador2.jogarCartaNoCampo(carta, jogador1);
                            maoUI.atualizarCampoDeBatalha(campoJogador2, jogador2);
                            maoUI.atualizarMao(maoJogador2Painel, campoJogador2, jogador2, jogador1 );

                        } catch (ManaInsuficienteException ex) {
                            throw new RuntimeException(ex);
                        }
                        System.out.println("Jogador 2 jogou a carta: " + carta.getNome());
                    }
                });
                // Se houver carta, cria CartaUI
            } else {
                cartaUI = new JButton("Vazio"); // Adiciona um botão vazio caso não haja carta na posição i
            }

            // Define a posição do componente e adiciona ao painel
            c3.gridx = i;
            maoJogador2Painel.add(cartaUI, c3);
        }

        //adiciona o painel da MÃO ao painel do jogador 2
        jogador2Painel.add(maoJogador2Painel, BorderLayout.CENTER);

        //painel do DECK do jogador2
        JPanel deckJogador2Painel = new JPanel(new BorderLayout());
        deckJogador2Painel.setPreferredSize(new Dimension(100, 150));
        deckJogador2Painel.setOpaque(false);
        deckJogador2Painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nomeDeck2 = new JLabel("Deck 2");
        deckJogador2Painel.add(nomeDeck2, BorderLayout.CENTER);

        //botao de deck (compra)
        JButton btnCompra2 = new JButton("Deck2");
        btnCompra2.setBackground(Color.WHITE);
        btnCompra2.setPreferredSize(new Dimension(100, 150));

        btnCompra2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jogador2.getMao().adicionarCartasMao(deckJogador2.comprarCarta());
            }
        });

        deckJogador2Painel.add(btnCompra2, BorderLayout.CENTER);

        //adiciona o painel do deck no painel de jogador2
        jogador2Painel.add(deckJogador2Painel, BorderLayout.EAST);

        //painel de informações do jogador 2
        JPanel infoJogador2 = new JPanel(new GridLayout(3, 1));
        infoJogador2.setPreferredSize(new Dimension(100, 150));
        infoJogador2.setBackground(Color.CYAN);
        infoJogador2.add(new JLabel("Nome: " + jogador2.getNome()));
        infoJogador2.add(new JLabel("Vida: " + jogador2.getVida()));
        infoJogador2.add(new JLabel("Mana: " + jogador2.getMana()));

        jogador2Painel.add(infoJogador2, BorderLayout.WEST);

        //botão de finalizar o turno
        JButton btnFinalizarTurno2 = new JButton("Finalizar Turno");
        btnFinalizarTurno2.setBackground(Color.LIGHT_GRAY);
        btnFinalizarTurno2.setPreferredSize(new Dimension(120, 50));
        btnFinalizarTurno2.addActionListener(e -> alternarTurno());

        jogador2Painel.add(btnFinalizarTurno2, BorderLayout.NORTH);

        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.NORTH;
        gamePanel.add(jogador2Painel, c);



        this.add(gamePanel);
    }

    private boolean jogoAtivo = true;
    private boolean turnoFinalizado = false;

    public void iniciarJogo() {
        while (jogoAtivo) {

            Jogador jogadorAtual = turnoJogador1 ? jogador1 : jogador2;


            iniciarTurno();


            esperarFinalizarTurno();


            if (verificarVitoria(jogador1) || verificarVitoria(jogador2)) {
                jogoAtivo = false;
            }


            alternarTurno();
        }


        encerrarJogo();
    }

    private void esperarFinalizarTurno() {

        while (jogoAtivo) {
            try {
                Thread.sleep(100); //
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }


            if (turnoFinalizado) {
                turnoFinalizado = false;
                break;
            }
        }
    }

    private void finalizarTurno() {
        turnoFinalizado = true;
        alternarTurno();
    }

    private void encerrarJogo() {

        SwingUtilities.invokeLater(() -> {
            TelaFinal telaFinal = new TelaFinal();
            telaFinal.setVisible(true);
            dispose();
        });
    }

    private void iniciarTurno() {
        Jogador jogadorAtual = turnoJogador1 ? jogador1 : jogador2;

        if (jogadorAtual.getMana() < 10) {
            jogadorAtual.incrementarMana();
        }

        configurarBotoesDeTurno(turnoJogador1);

        verificarEncantamentos();

        executarFaseDeCombate(jogadorAtual, turnoJogador1 ? jogador2 : jogador1);

        atualizarInterface();
    }

    private void configurarBotoesDeTurno(boolean turnoJogador1) {
        btnCompra1.setEnabled(turnoJogador1);
        btnCompra2.setEnabled(!turnoJogador1);
        btnFinalizarTurno1.setEnabled(turnoJogador1);
        btnFinalizarTurno2.setEnabled(!turnoJogador1);
    }

    private void alternarTurno() {
        turnoJogador1 = !turnoJogador1;
        String jogadorAtualNome = turnoJogador1 ? jogador1.getNome() : jogador2.getNome();
        JOptionPane.showMessageDialog(this, "Agora é a vez de " + jogadorAtualNome + "!");

        iniciarTurno();
    }

    private void executarFaseDeCombate(Jogador jogadorAtacante, Jogador jogadorDefensor) {
        List<Criatura> criaturasAtacantes = jogadorAtacante.getCampoDeBatalha().getCriaturasNoCampo(jogadorAtacante);
        List<Criatura> criaturasDefensoras = jogadorDefensor.getCampoDeBatalha().getCriaturasNoCampo(jogadorDefensor);

        for (Criatura atacante : criaturasAtacantes) {
            if (!criaturasDefensoras.isEmpty()) {
                Criatura alvo = criaturasDefensoras.get(0);
                System.out.println(atacante.getNome() + " ataca " + alvo.getNome());
                gerenciador.atacarCriatura(atacante, alvo);

                if (alvo.getResistencia() <= 0) {
                    gerenciador.removerCriaturaDoCampo(jogadorDefensor, alvo);
                }
            } else {
                System.out.println(atacante.getNome() + " ataca " + jogadorDefensor.getNome());
               gerenciador.atacarJogador(atacante, jogadorDefensor);
            }
        }
    }

    private void verificarEncantamentos() {
        List<Encantamento> encantamentosJogador1 = jogador1.getCampoDeBatalha().getEncantamentosNoCampo();
        verificarDuracaoEncantamentos(encantamentosJogador1, jogador1);

        List<Encantamento> encantamentosJogador2 = jogador2.getCampoDeBatalha().getEncantamentosNoCampo();
        verificarDuracaoEncantamentos(encantamentosJogador2, jogador2);
    }


    public void verificarDuracaoEncantamentos(List<Encantamento> encantamentos, Jogador jogador) {
        for(Encantamento encantamento: encantamentos){
        if (encantamento instanceof EncantamentoDano) {
            gerenciador.aplicarEncantamentoDano(jogador, (EncantamentoDano) encantamento);
        } else if (encantamento instanceof EncantamentoCura) {
            gerenciador.aplicarEncantementoCura(jogador, (EncantamentoCura) encantamento);
        }
        }
        for (Encantamento encantamento : encantamentos) {
            encantamento.reduzirDuracao();

            if (encantamento.getDuracao() <= 0) {
                jogador.getCampoDeBatalha().removerCartaDoCampo(encantamento);
                jogador.getCemiterio().adicionarCartasNoCemiterio(encantamento);
                //Atualizar cemitério
                System.out.println("Encantamento " + encantamento.getNome() + " foi movido para o cemitério.");
            }
        }
    }

    private void removerCriaturaDoCampo(Jogador jogador, Criatura criatura) {
        jogador.getCampoDeBatalha().removerCartaDoCampo(criatura);
        jogador.getCemiterio().adicionarCartasNoCemiterio(criatura);
        System.out.println(criatura.getNome() + " foi removida do campo e adicionada ao cemitério.");
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

public void atualizarInterface(){
}

public void atualizarPainelDoJogador(JPanel infoJogadorPanel, Jogador jogador){
        infoJogadorPanel.removeAll();

        infoJogadorPanel.add(new JLabel("Nome: " + jogador.getNome()));
        infoJogadorPanel.add(new JLabel(" Vida " + jogador.getVida()));
        infoJogadorPanel.add(new JLabel("Mana: " + jogador.getNome()));

        infoJogadorPanel.revalidate();
        infoJogadorPanel.repaint();

}

}




