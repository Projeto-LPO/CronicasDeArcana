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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private JButton btnFinalizarTurno1;
    private JButton btnFinalizarTurno2;
    private GerenciadorDeCombate gerenciador;
    private MaoUI maoUI;;
    private JLabel textoDescricao;
    private Map<Jogador, JPanel> mapaCampos;
    private Map<Jogador, JPanel> mapaCemiterios;
    private JPanel painelJogador1;
    private JPanel painelJogador2;


    public JogoTela(Jogador jogador1, Jogador jogador2, GerenciadorDeCombate gerenciador) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.gerenciador = gerenciador;

        mapaCampos = new HashMap<>();
        mapaCemiterios = new HashMap<>();
        maoUI = new MaoUI();

        // Cria as cartas para os decks
        List<Carta> cartasCriadas = InstanciaCartas.gerarCartas();

        // Inicializando e embaralhando decks dos jogadores
        this.deckJogador1 = new Decks(cartasCriadas);
        this.deckJogador2 = new Decks(cartasCriadas);
        this.deckJogador1.embaralhar();
        this.deckJogador2.embaralhar();

        // Adiciona cartas iniciais às mãos dos jogadores
        for (int i = 0; i < 5; i++) {
            adicionarCartaInicial(jogador1, deckJogador1);
            adicionarCartaInicial(jogador2, deckJogador2);
        }

        // Determina qual jogador começa
        turnoJogador1 = new Random().nextBoolean();
        String primeiroJogador = turnoJogador1 ? jogador1.getNome() : jogador2.getNome();
        JOptionPane.showMessageDialog(this, "O jogador " + primeiroJogador + " começa!");

        // Configura a interface gráfica
        inicializarTela();
        inicializarComponentes();
    }

    private void adicionarCartaInicial(Jogador jogador, Decks deck) {
        if (jogador != null && jogador.getMao() != null && deck != null) {
            Carta carta = deck.comprarCarta();
            if (carta != null) {
                jogador.getMao().adicionarCartasMao(carta);
            }
        }
    }

    private void inicializarTela() {
        setTitle("Partida iniciada! | " + jogador1.getNome() + " versus " + jogador2.getNome());
        setResizable(false);
        setBounds(100, 100, 1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        JPanel gamePanel = new JPanel(new GridBagLayout());
        gamePanel.setBackground(new Color(0, 0, 128));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        // Adiciona os componentes principais
        adicionarComponente(gamePanel, inicializarCampoDeBatalha(), c, 1, 1, 2, 1, GridBagConstraints.BOTH);
        adicionarComponente(gamePanel, inicializarCemiterio(), c, 3, 1, 2, 1, GridBagConstraints.CENTER);
        adicionarComponente(gamePanel, inicializarPainelDescricao(), c, 0, 1, 1, 1, GridBagConstraints.CENTER);

        // Adiciona os painéis dos jogadores
        adicionarComponente(gamePanel, inicializarPainelJogador(jogador1, true), c, 1, 2, 2, 1, GridBagConstraints.SOUTH);
        adicionarComponente(gamePanel, inicializarPainelJogador(jogador2, false), c, 1, 0, 2, 1, GridBagConstraints.NORTH);

        this.add(gamePanel);
        revalidate();
        repaint();
    }

    private void adicionarComponente(JPanel painel, JComponent componente, GridBagConstraints c, int x, int y, int largura, int altura, int preenchimento) {
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = largura;
        c.gridheight = altura;
        c.fill = preenchimento;
        painel.add(componente, c);
    }

    private JPanel inicializarCampoDeBatalha() {
        JPanel campoDeBatalhaPainel = new JPanel(new BorderLayout());
        campoDeBatalhaPainel.setPreferredSize(new Dimension(500, 400));
        campoDeBatalhaPainel.setBackground(new Color(139, 69, 19));

        JPanel campoJogador1 = new JPanel();
        campoJogador1.setPreferredSize(new Dimension(540, 180));
        campoJogador1.setOpaque(false);

        JPanel campoJogador2 = new JPanel();
        campoJogador2.setPreferredSize(new Dimension(540, 180));
        campoJogador2.setOpaque(false);

        campoDeBatalhaPainel.add(campoJogador1, BorderLayout.SOUTH);
        campoDeBatalhaPainel.add(campoJogador2, BorderLayout.NORTH);

        mapaCampos.put(jogador1, campoJogador1);
        mapaCampos.put(jogador2, campoJogador2);

        return campoDeBatalhaPainel;
    }

    private JPanel inicializarCemiterio() {
        JPanel cemiterioPainel = new JPanel(new BorderLayout());
        cemiterioPainel.setPreferredSize(new Dimension(200, 300));
        cemiterioPainel.setBackground(Color.GRAY);

        JPanel cemiterioJogador1 = new JPanel();
        cemiterioJogador1.setPreferredSize(new Dimension(180, 200));
        cemiterioJogador1.setOpaque(false);

        JPanel cemiterioJogador2 = new JPanel();
        cemiterioJogador2.setPreferredSize(new Dimension(180, 200));
        cemiterioJogador2.setOpaque(false);

        cemiterioPainel.add(cemiterioJogador1, BorderLayout.SOUTH);
        cemiterioPainel.add(cemiterioJogador2, BorderLayout.NORTH);

        mapaCemiterios.put(jogador1, cemiterioJogador1);
        mapaCemiterios.put(jogador2, cemiterioJogador2);

        return cemiterioPainel;
    }

    private JPanel inicializarPainelDescricao() {
        JPanel descricaoPainel = new JPanel(new BorderLayout());
        descricaoPainel.setPreferredSize(new Dimension(200, 250));
        descricaoPainel.setBackground(new Color(255, 228, 181));

        textoDescricao = new JLabel("Descrição da carta");
        textoDescricao.setHorizontalAlignment(SwingConstants.CENTER);

        descricaoPainel.add(textoDescricao, BorderLayout.CENTER);
        return descricaoPainel;
    }

    private JPanel inicializarPainelJogador(Jogador jogador, boolean turnoDoJogador) {
        JPanel jogadorPainel = new JPanel(new BorderLayout());
        jogadorPainel.setPreferredSize(new Dimension(700, 150));
        jogadorPainel.setOpaque(false);

        // Painel da mão do jogador
        JPanel maoPainel = criarPainelMaoGenerico(jogador, turnoDoJogador);

        // Painel de informações do jogador
        JPanel infoPainel = criarPainelInfo(jogador);

        // Botão para finalizar turno
        JButton btnFinalizarTurno = new JButton("Finalizar Turno");
        btnFinalizarTurno.setBackground(Color.LIGHT_GRAY);
        btnFinalizarTurno.setPreferredSize(new Dimension(120, 50));
        btnFinalizarTurno.addActionListener(e -> alternarTurno());

        // Adiciona componentes ao painel do jogador
        jogadorPainel.add(maoPainel, BorderLayout.CENTER);
        jogadorPainel.add(infoPainel, BorderLayout.WEST);
        jogadorPainel.add(btnFinalizarTurno, turnoDoJogador ? BorderLayout.SOUTH : BorderLayout.NORTH);

        return jogadorPainel;
    }

    private JPanel criarPainelMaoGenerico(Jogador jogador, boolean turnoDoJogador) {
        JPanel maoPainel = new JPanel(new GridBagLayout());
        maoPainel.setPreferredSize(new Dimension(350, 150));
        maoPainel.setOpaque(false);

        if (jogador == null || jogador.getMao() == null) {
            return maoPainel; // Retorna painel vazio se o jogador ou a mão forem nulos
        }

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.gridy = 0;

        for (int i = 0; i < 5; i++) {
            Component cartaUI;

            if (i < jogador.getMao().getCartas().size()) {
                Carta carta = jogador.getMao().getCartas().get(i);
                cartaUI = new CartaUI(carta, jogador);
                ((CartaUI) cartaUI).addActionListener(e -> {
                    if (turnoDoJogador == turnoJogador1) {
                        try {
                            jogador.jogarCartaNoCampo(carta, turnoDoJogador ? jogador2 : jogador1);
                            maoUI.atualizarCampoDeBatalha(mapaCampos.get(jogador), jogador);
                            maoUI.atualizarMao(maoPainel, mapaCampos.get(jogador), jogador, turnoDoJogador ? jogador2 : jogador1);
                        } catch (ManaInsuficienteException ex) {
                            JOptionPane.showMessageDialog(null, "Mana insuficiente!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Não é o turno do jogador!");
                    }
                });

                ((CartaUI) cartaUI).addMouseMotionListener(new MouseMotionAdapter() {
                    public void mouseMoved(MouseEvent e) {
                        atualizarDescricao(carta);
                    }
                });
            } else {
                cartaUI = new JButton("Vazio");
            }

            c.gridx = i;
            maoPainel.add(cartaUI, c);
        }

        return maoPainel;
    }


    private JPanel criarPainelMaoJogador1() {
        return criarPainelMaoGenerico(jogador1, true);
    }

    // Método para criar o painel da mão de jogador 2
    private JPanel criarPainelMaoJogador2() {
        return criarPainelMaoGenerico(jogador2, false);
    }



    private JPanel criarPainelInfo(Jogador jogador) {
        if (jogador == null) {
            System.err.println("Jogador é nulo! Verifique a inicialização.");
            return new JPanel(); // Retorna um painel vazio para evitar erros
        }

        JPanel infoPainel = new JPanel(new GridLayout(2, 1));
        infoPainel.setPreferredSize(new Dimension(150, 100));
        infoPainel.setBackground(new Color(242, 213, 174));

        JLabel lblNome = new JLabel("Nome: " + jogador.getNome());
        JLabel lblVida = new JLabel("Vida: " + jogador.getVida());
        JLabel lblMana = new JLabel("Mana: " + jogador.getManaAtual() + "/" + jogador.getMana());
        JLabel lblNivel = new JLabel("Nível: " + jogador.getNivel());

        lblNome.setHorizontalAlignment(SwingConstants.CENTER);
        lblVida.setHorizontalAlignment(SwingConstants.CENTER);
        lblMana.setHorizontalAlignment(SwingConstants.CENTER);
        lblNivel.setHorizontalAlignment(SwingConstants.CENTER);

        infoPainel.add(lblNome);
        infoPainel.add(lblVida);
        infoPainel.add(lblMana);
        infoPainel.add(lblNivel);

        return infoPainel;
    }

    //-----------------------------------------------------------------------------------

    //logica de jogo
    private boolean jogoAtivo = true;
    private boolean turnoFinalizado = false;


    public void iniciarJogo() {
        System.out.println("Iniciando o jogo...");

        if (jogador1 == null || jogador2 == null) {
            throw new IllegalStateException("Jogadores não foram inicializados!");
        }

        // Inicialize os painéis antes do início do jogo
        painelJogador1 = criarPainelInfo(jogador1);
        painelJogador2 = criarPainelInfo(jogador2);

        // SwingWorker para rodar o jogo em segundo plano
        SwingWorker<Void, Void> jogoWorker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                while (jogoAtivo) {
                    Jogador jogadorAtual = turnoJogador1 ? jogador1 : jogador2;

                    // Log para acompanhar o fluxo
                    System.out.println("Turno do jogador atual: " + jogadorAtual.getNome());



                    // Executa o turno do jogador atual
                    SwingUtilities.invokeLater(() -> iniciarTurno());

                    // Aguarda o turno ser finalizado
                    esperarFinalizarTurno();

                    // Verifica condição de vitória
                    boolean vitoriaJogador1 = verificarVitoria(jogador1);
                    boolean vitoriaJogador2 = verificarVitoria(jogador2);

                    if (vitoriaJogador1 || vitoriaJogador2) {
                        System.out.println("Fim do jogo detectado.");
                        jogoAtivo = false;
                        break;
                    }

                    // Alterna o turno
                    SwingUtilities.invokeLater(() -> alternarTurno());
                }
                return null;
            }

            @Override
            protected void done() {
                System.out.println("Jogo finalizado.");
            }
        };

        jogoWorker.execute();
    }
        private void esperarFinalizarTurno() {
            while (!turnoFinalizado) {
                try {
                    Thread.sleep(100); // Aguarda brevemente
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
            turnoFinalizado = false; // Reseta para o próximo turno
        }

        private void finalizarTurno() {
            turnoFinalizado = true;
            alternarTurno();
        }

        private void iniciarTurno() {
            System.out.println("Iniciando turno...");
            Jogador jogadorAtual = turnoJogador1 ? jogador1 : jogador2;

            executarFaseDeCombate(jogadorAtual, turnoJogador1 ? jogador2 : jogador1);

            boolean vitoriaJogador1 = verificarVitoria(jogador1);
            boolean vitoriaJogador2 = verificarVitoria(jogador2);

            painelJogador1 = criarPainelInfo(jogador1);
            painelJogador2 = criarPainelInfo(jogador2);



                atualizarPainelDoJogador(painelJogador1, jogador1);
                atualizarPainelDoJogador(painelJogador2, jogador2);



            verificarEncantamentos();

            if (vitoriaJogador1 || vitoriaJogador2) {
                System.out.println("Fim do jogo detectado.");
                jogoAtivo = false;

            }

            // configurarBotoesDeTurno(turnoJogador1);

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
                return;
            }

            for (Criatura atacante : criaturasAtacantes) {
                if (!criaturasDefensoras.isEmpty()) {
                    Criatura alvo = criaturasDefensoras.get(0);
                    System.out.println(atacante.getNome() + " ataca " + alvo.getNome());
                    atacarCriatura(atacante, alvo);

                    if (alvo.getResistencia() <= 0) {
                        removerCriaturaDoCampo(jogadorDefensor, alvo);
                    }
                } else {
                    System.out.println(atacante.getNome() + " ataca diretamente " + jogadorDefensor.getNome());
                    atacarJogador(atacante, jogadorDefensor);
                }
            }

            atualizarCampoDeBatalha(jogadorAtacante);
            atualizarCampoDeBatalha(jogadorDefensor);
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
                    aplicarEncantamentoDano(jogador, (EncantamentoDano) encantamento);
                } else if (encantamento instanceof EncantamentoCura) {
                    aplicarEncantamentoCura(jogador, (EncantamentoCura) encantamento);
                }
            }
            for (Encantamento encantamento : encantamentos) {
                encantamento.reduzirDuracao();

                if (encantamento.getDuracao() <= 0) {
                    jogador.getCampoDeBatalha().removerCartaDoCampo(encantamento);
                    jogador.getCemiterio().adicionarCartasNoCemiterio(encantamento);
                    atualizarCampoDeBatalha(jogador);
                    atualizarCemiterio(jogador);


                    System.out.println("Encantamento " + encantamento.getNome() + " foi movido para o cemitério.");
                }
            }
        }

        private void removerCriaturaDoCampo(Jogador jogador, Criatura criatura) {
            jogador.getCampoDeBatalha().removerCartaDoCampo(criatura);
            jogador.getCemiterio().adicionarCartasNoCemiterio(criatura);

            atualizarCampoDeBatalha(jogador);
            atualizarCemiterio(jogador);

            System.out.println(criatura.getNome() + " foi removida do campo e adicionada ao cemitério.");
        }

    private boolean verificarVitoria(Jogador jogador) {

        try {
            System.out.println("AAAA");
            if (jogador.getVida() <= 0) {
                System.out.println(jogador.getNome() + " perdeu o jogo por ter vida igual ou menor a 0.");
                encerrarJogo(jogador.getNome());
                return true;
            } else if (jogador.getDeck().isEmpty()) {
                System.out.println(jogador.getNome() + " perdeu o jogo por não ter mais cartas no deck.");
                encerrarJogo(jogador.getNome());
                return true;
            }
        } catch (NullPointerException e) {
            System.err.println("Erro ao verificar vitória: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }


    private void encerrarJogo(String perdedor) {
        jogoAtivo = false;
        SwingUtilities.invokeLater(() -> {
            TelaFinal telaFinal = new TelaFinal();
            telaFinal.setVisible(true);
        });
    }


        public void atualizarDescricao(Carta carta){
            textoDescricao.setText(carta.gerarDescricao());
        }

    public void atualizarPainelDoJogador(JPanel infoJogadorPanel, Jogador jogador) {
        System.out.println("BoraBilll!!!");
        if (infoJogadorPanel == null || jogador == null) {
            System.err.println("Painel ou jogador está nulo! Verifique a inicialização.");
            return;
        }


            infoJogadorPanel.removeAll();

            infoJogadorPanel.add(new JLabel("Nome: " + jogador.getNome()));
            infoJogadorPanel.add(new JLabel("Vida: " + jogador.getVida()));
            infoJogadorPanel.add(new JLabel("Mana: " + jogador.getManaAtual() + "/" + jogador.getMana()));
            infoJogadorPanel.add(new JLabel("Nível: " + jogador.getNivel()));

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

        private void atualizarCampoDeBatalha(Jogador jogador) {
            JPanel painelCampo = mapaCampos.get(jogador);
            if (painelCampo == null) return;

            SwingUtilities.invokeLater(() -> {
                painelCampo.removeAll();

                List<Criatura> criaturas = jogador.getCampoDeBatalha().getCriaturasNoCampo(jogador);
                GridBagConstraints c = new GridBagConstraints();
                c.gridy = 0;

                for (int i = 0; i < criaturas.size(); i++) {
                    Criatura criatura = criaturas.get(i);
                    Component criaturaUI = new CartaUI(criatura, jogador);
                    c.gridx = i;
                    painelCampo.add(criaturaUI, c);
                }

                painelCampo.revalidate();
                painelCampo.repaint();
            });
        }

        private void atualizarCemiterio(Jogador jogador) {
            JPanel painelCemiterio = mapaCemiterios.get(jogador);
            if (painelCemiterio == null) return;

            SwingUtilities.invokeLater(() -> {
                painelCemiterio.removeAll();

                List<Carta> cartasNoCemiterio = jogador.getCemiterio().getCartasNoCemiterio();
                GridBagConstraints c = new GridBagConstraints();
                c.gridy = 0;

                for (int i = 0; i < cartasNoCemiterio.size(); i++) {
                    Carta carta = cartasNoCemiterio.get(i);
                    Component cartaUI = new CartaUI(carta, jogador);
                    c.gridx = i;
                    painelCemiterio.add(cartaUI, c);
                }

                painelCemiterio.revalidate();
                painelCemiterio.repaint();
            });
        }



    public  void aplicarEncantamentoDano(Jogador jogadorAlvo, EncantamentoDano encantamentoDano){
        System.out.println("Encantamento de Dano Aplicado");
        for (Criatura criatura: jogadorAlvo.getCampoDeBatalha().getCriaturasNoCampo(jogadorAlvo)){
            encantamentoDano.aplicarEfeitoDano(criatura);
        }
    }
    public  void aplicarEncantamentoCura(Jogador jogadorAlvo, Encantamento encantamentoCura){
        System.out.println("Encantamento de Cura Aplicado");
        for(Criatura criatura: jogadorAlvo.getCampoDeBatalha().getCriaturasNoCampo(jogadorAlvo)){
            encantamentoCura.aplicarEfeitoCura(criatura);
        }

    }



}