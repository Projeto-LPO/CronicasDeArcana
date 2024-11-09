package ElementosGraficos.Telas;

import ElementosGraficos.UiElements.CartaUI;
import ElementosGraficos.UiElements.JogadorUI;
import MecanicasDeJogo.Abstract.Carta;
import MecanicasDeJogo.Exceptions.ManaInsuficienteException;
import MecanicasDeJogo.FluxodeCartas.Decks;
import MecanicasDeJogo.FluxodeCartas.InstanciaCartas;
import MecanicasDeJogo.Jogador;
import Personagens.Criatura;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

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

    public JogoTela(Jogador jogador1, Jogador jogador2) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;

        //criação dos decks
        List<Carta> cartasCriadas = InstanciaCartas.gerarCartas();

        this.deckJogador1 = new Decks(cartasCriadas);
        this.deckJogador2 = new Decks(cartasCriadas);
        this.deckJogador1.embaralhar();
        this.deckJogador2.embaralhar();

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
                            jogador1.jogarCartaNoCampo(carta);
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

        //painel de informações do jogador 1
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
                            jogador2.jogarCartaNoCampo(carta);
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


        //adiciona o painel principal ao frame
        this.add(gamePanel);
    }

    private void iniciarTurno(){
        if(turnoJogador1){
            btnCompra1.setEnabled(true);
            btnCompra2.setEnabled(false);
            btnFinalizarTurno1.setEnabled(true);
            btnFinalizarTurno2.setEnabled(false);

        } else{
            btnCompra1.setEnabled(false);
            btnCompra2.setEnabled(true);
            btnFinalizarTurno1.setEnabled(false);
            btnFinalizarTurno2.setEnabled(true);
        }
    }

    private void alternarTurno(){

        turnoJogador1 = !turnoJogador1;
        String jogadorAtual = turnoJogador1 ? jogador1.getNome() : jogador2.getNome();
        JOptionPane.showMessageDialog(this, "Agora é a vez de " + jogadorAtual + "!");

        iniciarTurno();
    }



    private void combate(Jogador jogadorAtivo, Jogador jogadorOponente) {

        List<Criatura> criaturasAtacantes = jogadorAtivo.getCampoDeBatalha().getCriaturasNoCampo(jogadorAtivo);
        List<Criatura> criaturasDefensoras = jogadorOponente.getCampoDeBatalha().getCriaturasNoCampo(jogadorOponente);


        if (criaturasAtacantes.isEmpty()) {
            System.out.println(jogadorAtivo.getNome() + " não tem criaturas para atacar.");
            return;
        }


        for (Criatura atacante : criaturasAtacantes) {
            if (!criaturasDefensoras.isEmpty()) {

                Criatura alvo = criaturasDefensoras.get(0);
                System.out.println(atacante.getNome() + " ataca " + alvo.getNome());
                atacarCriatura(atacante, alvo); // Ataque à criatura do oponente


                if (alvo.getResistencia() <= 0) {
                    removerCriaturaDoCampo(jogadorOponente, alvo);
                }
            } else {

                System.out.println(atacante.getNome() + " ataca " + jogadorOponente.getNome());
                atacarJogador(atacante, jogadorOponente);
            }
        }


        verificarVitoria(jogadorOponente);
    }
    private void atacarCriatura(Criatura atacante, Criatura alvo){
        atacante.atacar(alvo);
    }
    private void atacarJogador(Criatura atacante, Jogador jogadorOponente){
        atacante.atacarJogador(jogadorOponente);
    }
    private void aplicarFeitiçoDeCura(){

    }
    private void aplicarFeitiçoDeDano(){

    }


    private void removerCriaturaDoCampo(Jogador jogador, Criatura criatura) {

        jogador.getCampoDeBatalha().removerCartaDoCampo(criatura);

        jogador.getCemiterio().adicionarCartasNoCemiterio(criatura);
        System.out.println(criatura.getNome() + " foi removida do campo e adicionada ao cemitério.");
    }

    private boolean verificarVitoria(Jogador jogador) {
        if (jogador.getVida() <= 0) {
            System.out.println(jogador.getNome() +" Ficou sem vida");
            return true;
        }

        if (jogador.getDeck().isEmpty()) {
            System.out.println(jogador.getNome() + "Ficou sem cartas");
            return true;
        }

        return false;
    }
}

