package MecanicasDeJogo;

import ElementosGraficos.UiElements.GerenciadorDeCombate;
import Feiticos.Feitiço;
import Feiticos.FeitiçoCura;
import Feiticos.FeitiçoDano;
import MecanicasDeJogo.Exceptions.ManaInsuficienteException;
import MecanicasDeJogo.Exceptions.VidaInsuficienteException;
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
    private int mana;
    private int manaAtual;
    private Nivel nivel;
    private GerenciadorDeCombate gerenciador;

    private static  int MANA_MAXIMA = 10;

    public Jogador(String nome, Decks deck, int vida, int mana) {
        this.nome = nome;
        this.deck = deck;
        this.mao = new Mao();
        this.cemiterio = new Cemiterio();
        this.campoDeBatalha = new CampodeBatalha(mao, cemiterio, deck);
        this.vida = vida;
        this.mana = mana;
        this.manaAtual = mana;
        this.nivel = new Nivel();

        // Comprando 3 cartas no início
        System.out.println(nome + " está comprando cartas iniciais...");
        for (int i = 0; i < 3; i++) {
            comprarCartas();
        }
    }

    // Método para comprar uma carta do deck e adicioná-la à mão
    public void comprarCartas() {

        if (deck.isEmpty()) {
            System.out.println("Não há mais cartas no deck para " + nome + "!");
            return; // Não faz nada se o deck estiver vazio
        }

        Carta cartaComprada = deck.comprarCarta();
        if (cartaComprada != null) {
            mao.adicionarCartasMao(cartaComprada);
            System.out.println(nome + " comprou a carta: " + cartaComprada.getNome());
        } else {
            System.out.println("Não foi possível comprar uma carta.");
        }
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
            gerenciador.aplicarFeitiçoDeCura(feitiçoCura, jogadorAlvo);
            cemiterio.adicionarCartasNoCemiterio(feitiçoCura);

            System.out.println(nome + " lançou o feitiço de cura: " + carta.getNome());

        } else if (carta instanceof FeitiçoDano) {
            FeitiçoDano feitiçoDano = (FeitiçoDano) carta;
            gerenciador.aplicarFeitiçoDeDano(feitiçoDano, jogadorAlvo);
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
        this.vida += cura;
        System.out.println(nome + " foi curado em " + cura + " pontos de vida. Vida atual: " + this.vida);
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



    public void setNome(String nome) {
        this.nome = nome;
    }




}
