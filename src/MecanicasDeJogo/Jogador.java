package MecanicasDeJogo;

import MecanicasDeJogo.Abstract.Carta;

public class Jogador {
    private String nome;
    private Decks deck;
    private Mao mao;
    private Cemiterio cemiterio;
    private CampodeBatalha campoDeBatalha;
    private int vida;
    private int mana;
    private int manaAtual;

    public Jogador(String nome, Decks deck, int vida, int mana) {
        this.nome = nome;
        this.deck = deck;
        this.mao = new Mao();
        this.cemiterio = new Cemiterio();
        this.campoDeBatalha = new CampodeBatalha(mao, cemiterio, deck);
        this.vida = vida;
        this.mana = mana;
        this.manaAtual = mana;

        // Comprando 3 cartas no início
        System.out.println(nome + " está comprando cartas iniciais...");
        for (int i = 0; i < 3; i++) {
            comprarCartas();
        }
    }

    // Método para comprar uma carta do deck e adicioná-la à mão
    public void comprarCartas() {
        // Verifique se há cartas no deck antes de tentar comprar
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

    // Método para jogar uma carta no campo de batalha
    public void jogarCartaNoCampo(Carta carta) {
        if (mao.temCarta(carta)) {
            campoDeBatalha.adicionarCartasAoCampo(carta);
            mao.removerCartaMao(carta); // Remove a carta da mão
            System.out.println(nome + " jogou a carta: " + carta.getNome());
        } else {
            System.out.println("A carta " + carta.getNome() + " não está na mão de " + nome + ".");
        }
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

    public void receberDano(int dano) {
        this.vida -= dano;
        if (this.vida <= 0) {
            this.vida = 0;
            System.out.println(nome + " foi derrotado!");
        } else {
            System.out.println(nome + " recebeu " + dano + " de dano. Vida restante: " + this.vida);
        }
    }

    public void receberCura(int cura) {
        this.vida += cura;
        System.out.println(nome + " foi curado em " + cura + " pontos de vida. Vida atual: " + this.vida);
    }

    public void reiniciarMana() {
        this.manaAtual = mana;
    }

    public void usarMana(int quantidade) {
        this.manaAtual -= quantidade;
    }

    public void defender(Carta atacante) {
        // Aqui, você pode implementar a lógica de defesa.
        System.out.println(getNome() + " defende com " + atacante.getNome());
        // Lógica de dano, destruição de criaturas, etc.
    }

    public CampodeBatalha getCampoDeBatalha() {
        return  campoDeBatalha;
    }
}
