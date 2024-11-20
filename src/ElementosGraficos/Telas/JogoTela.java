package ElementosGraficos.Telas;

import ElementosGraficos.UiElements.CartaUI;
import ElementosGraficos.UiElements.CemiterioUI;
import ElementosGraficos.UiElements.GerenciadorDeCombate;
import ElementosGraficos.UiElements.MaoUI;
import Encantamento.Encantamento;
import Encantamento.EncantamentoDano;
import MecanicasDeJogo.Abstract.Carta;
import MecanicasDeJogo.Exceptions.ManaInsuficienteException;
import MecanicasDeJogo.FluxodeCartas.Decks;
import MecanicasDeJogo.FluxodeCartas.InstanciaCartas;
import MecanicasDeJogo.Jogador;
import Personagens.Criatura;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
    private JPanel cemiterioPainel;
    private JLabel textoDescricao;


    public JogoTela(Jogador jogador1, Jogador jogador2, GerenciadorDeCombate gerenciador) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.gerenciador = gerenciador;

        List<Carta> cartasCriadas = InstanciaCartas.gerarCartas();

        // Inicializando decks dos jogadores com as cartas geradas
        this.deckJogador1 = new Decks(cartasCriadas);
        this.deckJogador2 = new Decks(cartasCriadas);

        // Embaralhando os decks dos jogadores
        this.deckJogador1.embaralhar();
        this.deckJogador2.embaralhar();

        MaoUI maoUI = new MaoUI();

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
        campoJogador1.setOpaque(false);

        //campo jogador 2
        JPanel campoJogador2 = new JPanel();
        campoJogador2.setPreferredSize(new Dimension(540, 180));
        campoJogador2.setOpaque(false);

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
        cemiterioJogador1.setOpaque(false);

        //cemiterio jogador 2
        JPanel cemiterioJogador2 = new JPanel();
        cemiterioJogador2.setPreferredSize(new Dimension(180, 200));
        cemiterioJogador2.setOpaque(false);

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

            //criação da label da descrição
            textoDescricao = new JLabel();
            textoDescricao.setHorizontalAlignment(SwingConstants.CENTER);
            textoDescricao.setForeground(Color.BLACK);
            textoDescricao.setFont(new Font("Uncial Antiqua", Font.PLAIN, 12));
            textoDescricao.setBounds(10,10,180,230);

        descricaoPainel.add(textoDescricao, BorderLayout.CENTER);
        
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
                        if (!turnoJogador1) {
                            System.out.println("Não é o turno do Jogador 1!");
                            return;
                        }
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
                ((CartaUI)(cartaUI)).addMouseMotionListener(new MouseMotionAdapter() {
                   public void mouseMoved(MouseEvent e) {                                  //funcao para adicionar
                                                                                           //o escutador de mouse passando
                       atualizarDescricao(carta);
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

// Botão de deck (compra)
        JButton btnCompra1 = new JButton("Deck 1");
        btnCompra1.setBackground(Color.WHITE);
        btnCompra1.setPreferredSize(new Dimension(100, 150));
        btnCompra1.setFont(new Font("Uncial Antiqua", Font.BOLD, 10));  // Definindo a fonte
        btnCompra1.setForeground(Color.BLACK);  // Definindo a cor do texto

        btnCompra1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jogador1.getMao().adicionarCartasMao(deckJogador1.comprarCarta());
            }
        });

// Adiciona o botão de compra ao painel
        deckJogador1Painel.add(btnCompra1, BorderLayout.CENTER);

// Adiciona o painel do deck no painel de jogador
        jogador1Painel.add(deckJogador1Painel, BorderLayout.EAST);

        JPanel infoJogador1 = new JPanel(new GridLayout(3, 1));
        infoJogador1.setPreferredSize(new Dimension(150, 150));
        infoJogador1.setBackground(Color.CYAN);
        infoJogador1.add(new JLabel("Nome: " + jogador1.getNome()));
        infoJogador1.add(new JLabel("Vida: " + jogador1.getVida()));
        infoJogador1.add(new JLabel("Mana: " + jogador1.getMana()));
        infoJogador1.add(new JLabel("Nível: " + jogador1.getNivel()));

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
                        if (turnoJogador1) {
                            System.out.println("Não é o turno do Jogador 2!");
                            return;
                        }
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
                ((CartaUI)(cartaUI)).addMouseMotionListener(new MouseMotionAdapter() {
                    public void mouseMoved(MouseEvent e) {
                        atualizarDescricao(carta);
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

        // Inicializa o botão de compra
        btnCompra2 = new JButton("Deck2");
        btnCompra2.setBackground(Color.WHITE);
        btnCompra2.setPreferredSize(new Dimension(100, 150));
        btnCompra2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jogador2.getMao().adicionarCartasMao(deckJogador2.comprarCarta());
            }
        });

        deckJogador2Painel.add(btnCompra2, BorderLayout.CENTER);

        // Adiciona o painel do deck no painel de jogador2
        jogador2Painel.add(deckJogador2Painel, BorderLayout.EAST);

        // Verifique se o painel de informações foi inicializado corretamente
        JPanel infoJogador2 = new JPanel(new GridLayout(3, 1));
        infoJogador2.setPreferredSize(new Dimension(150, 150));
        infoJogador2.setBackground(Color.CYAN);
        infoJogador2.add(new JLabel("Nome: " + jogador2.getNome()));
        infoJogador2.add(new JLabel("Vida: " + jogador2.getVida()));
        infoJogador2.add(new JLabel("Mana: " + jogador2.getMana()));
        infoJogador2.add(new JLabel("Nível: "+jogador2.getNivel()));

        jogador2Painel.add(infoJogador2, BorderLayout.WEST);

        // Finalizar turno
        JButton btnFinalizarTurno2 = new JButton("Finalizar Turno");
        btnFinalizarTurno2.setBackground(Color.LIGHT_GRAY);
        btnFinalizarTurno2.setPreferredSize(new Dimension(120, 50));
        btnFinalizarTurno2.addActionListener(e -> alternarTurno());
        jogador2Painel.add(btnFinalizarTurno2, BorderLayout.NORTH);

        // Configura o painel na interface
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.NORTH;
        gamePanel.add(jogador2Painel, c);
        this.add(gamePanel);

        // Atualiza a interface
        this.revalidate();
        this.repaint();

    }

    private boolean jogoAtivo = true;
    private boolean turnoFinalizado = false;

    public void iniciarJogo() {
        System.out.println("Iniciando o jogo...");
        while (jogoAtivo) {
            Jogador jogadorAtual = turnoJogador1 ? jogador1 : jogador2;

            iniciarTurno();
            esperarFinalizarTurno();

            if (verificarVitoria(jogador1) || verificarVitoria(jogador2)) {
                jogoAtivo = false;
            }

            if (!jogoAtivo) {
                break;
            }

            alternarTurno();
        }

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

    private void iniciarTurno() {
        System.out.println("Iniciando turno...");
        Jogador jogadorAtual = turnoJogador1 ? jogador1 : jogador2;

        executarFaseDeCombate(jogadorAtual, turnoJogador1 ? jogador2 : jogador1);

        verificarEncantamentos();
        configurarBotoesDeTurno(turnoJogador1);

    }

   public void configurarBotoesDeTurno (boolean turnoJogador1) {
         btnCompra1.setEnabled(turnoJogador1);
        btnCompra2.setEnabled(!turnoJogador1);
         btnFinalizarTurno1.setEnabled(turnoJogador1);
         btnFinalizarTurno2.setEnabled(!turnoJogador1);
    }


    private void alternarTurno() {
        System.out.println("Alternando turno...");
        turnoJogador1 = !turnoJogador1;
        String jogadorAtualNome = turnoJogador1 ? jogador1.getNome() : jogador2.getNome();
        JOptionPane.showMessageDialog(this, "Agora é a vez de " + jogadorAtualNome + "!");
        jogador1.incrementarMana();
        jogador2.incrementarMana();

        iniciarTurno();
    }

    private void executarFaseDeCombate(Jogador jogadorAtacante, Jogador jogadorDefensor) {
        System.out.println("Iniciando fase de combate...");
        List<Criatura> criaturasAtacantes = jogadorAtacante.getCampoDeBatalha().getCriaturasNoCampo(jogadorAtacante);
        List<Criatura> criaturasDefensoras = jogadorDefensor.getCampoDeBatalha().getCriaturasNoCampo(jogadorDefensor);

        if (criaturasAtacantes.isEmpty() && criaturasDefensoras.isEmpty()) {
            System.out.println("Nenhum ataque pode ser realizado, ambos os jogadores não possuem criaturas.");
            return; // Sem combate, pode terminar a fase de combate aqui.
        }

        for (Criatura atacante : criaturasAtacantes) {
            if (!criaturasDefensoras.isEmpty()) {
                Criatura alvo = criaturasDefensoras.get(0);
                System.out.println(atacante.getNome() + " ataca " + alvo.getNome());
                atacarCriatura(atacante, alvo);

                if (alvo.getResistencia() <= 0) {
                    removerCriaturaDoCampo(jogadorDefensor, alvo);
                    System.out.println("Removido do campo de batalha");
                }
            } else {
                System.out.println(atacante.getNome() + " ataca " + jogadorDefensor.getNome());
                atacarJogador(atacante, jogadorDefensor);
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
                CemiterioUI cemiterioUI = new CemiterioUI();
                SwingUtilities.invokeLater(()->cemiterioUI.atualizarCemiterio(cemiterioPainel, jogador) );
                System.out.println("Encantamento " + encantamento.getNome() + " foi movido para o cemitério.");
            }
        }
    }

    private void removerCriaturaDoCampo(Jogador jogador, Criatura criatura) {
        jogador.getCampoDeBatalha().removerCartaDoCampo(criatura);
        jogador.getCemiterio().adicionarCartasNoCemiterio(criatura);

        CemiterioUI cemiterioUI = new CemiterioUI();
        SwingUtilities.invokeLater(()-> cemiterioUI.atualizarCemiterio(cemiterioPainel, jogador));

        System.out.println(criatura.getNome() + " foi removida do campo e adicionada ao cemitério.");
    }

    private boolean verificarVitoria(Jogador jogador) {
        if (jogador.getVida() <= 0 || jogador.getDeck().isEmpty()) {
            System.out.println(jogador.getNome() + " perdeu o jogo.");

            SwingUtilities.invokeLater(() -> {
                TelaFinal telaFinal = new TelaFinal();
                telaFinal.setVisible(true);
            });

            jogoAtivo = false;
            return true;
        }
        return false;
    }


    public void atualizarDescricao(Carta carta){
        textoDescricao.setText(carta.gerarDescricao());
    }

    public void atualizarPainelDoJogador(JPanel infoJogadorPanel, Jogador jogador){
        infoJogadorPanel.removeAll();

        infoJogadorPanel.add(new JLabel("Nome: " + jogador.getNome()));
        infoJogadorPanel.add(new JLabel(" Vida " + jogador.getVida()));
        infoJogadorPanel.add(new JLabel("Mana: " + jogador.getNome()));
        infoJogadorPanel.add(new JLabel(" Nível: " + jogador.getNivel()));

        infoJogadorPanel.revalidate();
        infoJogadorPanel.repaint();

}


    public void atacarCriatura(Criatura atacante, Criatura alvo) {
        atacante.atacar(alvo);
        System.out.println("Ataque");

    }

    public  void atacarJogador(Criatura atacante, Jogador jogador) {
        atacante.atacarJogador(jogador);
        System.out.println("Ataque ao jogador");

    }

}




