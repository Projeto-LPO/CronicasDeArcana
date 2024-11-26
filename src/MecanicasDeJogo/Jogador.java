package MecanicasDeJogo;

import Feiticos.FeitiçoCura;
import Feiticos.FeitiçoDano;
import MecanicasDeJogo.Exceptions.ManaInsuficienteException;
import MecanicasDeJogo.FluxodeCartas.CampodeBatalha;
import MecanicasDeJogo.FluxodeCartas.Cemiterio;
import MecanicasDeJogo.FluxodeCartas.Decks;
import MecanicasDeJogo.FluxodeCartas.Mao;
import MecanicasDeJogo.Abstract.Carta;
import MecanicasDeJogo.Interfaces.Atacavel;
import MecanicasDeJogo.Progressão.Nivel;
import Personagens.Criatura;
import Encantamento.Encantamento;

import java.util.ArrayList;
import java.util.List;

public class Jogador implements Atacavel {
    private String nome;
    private Decks deck;
    private Mao mao;
    private Cemiterio cemiterio;
    private CampodeBatalha campoDeBatalha;
    private int vida;
    private int vidaInicial;
    private List<Carta> cartasDisponiveis;
    private int mana;
    private int manaAtual;
    private int manaInicial = 10;
    private Nivel nivel;

    private static final int MAXIMO_DECK = 30;
    private static  int MANA_MAXIMA = 10;

    public Jogador(String nome, Decks deck, int vida, int mana, int vidaInicial) {
        this.nome = nome;
        this.deck = deck;
        this.mao = new Mao();
        this.cartasDisponiveis = new ArrayList<>();
        this.cemiterio = new Cemiterio();
        this.campoDeBatalha = new CampodeBatalha(mao, cemiterio, deck);
        this.vida = vidaInicial;
        this.mana = manaInicial;
        this.manaAtual = mana;
        this.nivel = new Nivel();
        this.vidaInicial = vidaInicial;
    }

    public void jogarCartaNoCampo(Carta carta, Jogador jogadorAlvo) throws ManaInsuficienteException {
        if (!mao.temCarta(carta)) {
            System.out.println("A carta " + carta.getNome() + " não está na mão de " + nome + ".");
            return;
        }

        if (carta.getCustoMana() > this.manaAtual) {
            throw new ManaInsuficienteException("Mana insuficiente para jogar a carta: " + carta.getNome());
        }

        usarMana(carta.getCustoMana());
        mao.removerCartaMao(carta);


        if (carta instanceof FeitiçoCura) {
            FeitiçoCura feitiçoCura = (FeitiçoCura) carta;
            aplicarFeitiçoDeCura(feitiçoCura, jogadorAlvo);
            cemiterio.adicionarCartasNoCemiterio(feitiçoCura);

            System.out.println(nome + " lançou o feitiço de cura: " + carta.getNome());

        } else if (carta instanceof FeitiçoDano) {
            FeitiçoDano feitiçoDano = (FeitiçoDano) carta;
            aplicarFeitiçoDeDano(feitiçoDano, jogadorAlvo);
            cemiterio.adicionarCartasNoCemiterio(feitiçoDano);
            System.out.println(nome + " lançou o feitiço de dano: " + carta.getNome());
            //atualizar cemiterio
        } else if (carta instanceof Encantamento) {
            Encantamento encantamento = (Encantamento) carta;
            campoDeBatalha.adicionarCartasAoCampo(encantamento);
            System.out.println(nome + " jogou o encantamento: " + carta.getNome());
            // atualizar cemiterio
        } else {
            campoDeBatalha.adicionarCartasAoCampo(carta);
            System.out.println(nome + " jogou a carta: " + carta.getNome());
        }

        System.out.println("Mana restante de " + nome + ": " + this.manaAtual);
    }


    @Override
    public void receberDano(int dano) {
        this.vida -= dano;
        if (this.vida <= 0) {
            this.vida = 0;
            System.out.println(nome + " foi derrotado!");
        } else {
            System.out.println(nome + " recebeu " + dano + " de dano. Vida restante: " + this.vida);
        }
    }

    @Override
    public void receberCura(int cura) {
        if (vida < vidaInicial) { // Permite a cura apenas se a vida for menor que o máximo
            this.vida = Math.min(this.vida + cura, vidaInicial); // Limita a cura ao valor máximo
            System.out.println(nome + " foi curado em " + cura + " pontos de vida. Vida atual: " + this.vida);
        } else {
            System.out.println(nome + " já está com a vida máxima: " + this.vida);
        }
    }

    public void incrementarMana() {
        if (manaAtual < MANA_MAXIMA) {
            manaAtual++;
        }
    }

    public CampodeBatalha getCampoDeBatalha() {
        return campoDeBatalha;
    }

    public void setVida(int novaVida) {
        this.vida = novaVida;
        System.out.println(getNome() + " agora tem " + this.vida + " de vida.");
    }

    public  void aplicarFeitiçoDeCura(FeitiçoCura feitiçoCura, Jogador jogadorAlvo) {
        System.out.println("Aplicando feitiço cura");
        feitiçoCura.aplicarEfeitoCura(jogadorAlvo);

        for (Criatura criatura : jogadorAlvo.getCampoDeBatalha().getCriaturasNoCampo()) {
            feitiçoCura.aplicarEfeitoCura(criatura);
        }
    }

    public  void aplicarFeitiçoDeDano(FeitiçoDano feitiçoDano, Jogador jogadorAlvo){
        System.out.println("Aplicando feitiço de dano");
        feitiçoDano.aplicarEfeitoDano(jogadorAlvo);
        for (Criatura criatura : jogadorAlvo.getCampoDeBatalha().getCriaturasNoCampo()) {
            feitiçoDano.aplicarEfeitoDano(criatura);
        }
    }

    public void reiniciarAtributos(){
        this.vida= vidaInicial;
        this.mana= manaInicial;
        this.manaAtual= manaInicial;

    }

    //função para adicionar cartas ao DECK da tela de inventário dos jogadores (cartas disponiveis --> deck)
    public boolean adicionarCartaAoDeck(Carta carta) {
        if (deck.getTamanho() >= MAXIMO_DECK) {
            return false; // Limite de cartas no deck alcançado
        }
        if (cartasDisponiveis.contains(carta)) {
            cartasDisponiveis.remove(carta);
            deck.adicionarCarta(carta);
            return true;
        }
        return false;
    }

    public boolean removerCartaDoDeck(Carta carta) {
        if (deck.getCartas().contains(carta)) {
            deck.getCartas().remove(carta);
            cartasDisponiveis.add(carta);
            return true;
        }
        return false;
    }

    //função para salvar alterações no deck na tela de inventário
    public void salvarDeck() {
        System.out.println("Deck salvo com sucesso para o jogador " + nome);

    }




    //função que descarta as alterações e adiciona todas as cartas para o painel de cartas disponiveis
    public void descartarDeck() {
        cartasDisponiveis.addAll(deck.getCartas());
        deck = new Decks();
        System.out.println("Alterações descartadas para o jogador " + nome);
    }


    public void reiniciarMana() {
        this.manaAtual = 1; }

    public void usarMana(int quantidade) {
        this.manaAtual -= quantidade;
    }

    public void subirNivel() {
        nivel.ganharNivel();
    }

    public int getNivel() {
        return nivel.getNivelAtual();
    }

    public void mostrarNivel() {
        nivel.mostrarNivel();
    }

    public String getNome() {
        return nome;
    }

    public Decks getDeck() {
        return deck;
    }

    public List<Carta> getCartasDisponiveis() {
        return cartasDisponiveis;
    }

    public Mao getMao() {
        return mao;
    }

    public Cemiterio getCemiterio() {
        return cemiterio;
    }

    public int getVida() {
        return vida;
    }

    public int getMana() {
        return mana;
    }

    public int getManaAtual() {
        return manaAtual;
    }

    public int getVidaInicial() {
        return this.vidaInicial;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}