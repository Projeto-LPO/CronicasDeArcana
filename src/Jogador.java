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

        for (int i = 0; i < 5; i++) {
            Carta cartaComprada = deck.comprarCarta();
            mao.adicionarCartas(cartaComprada);
        }
    }

    // Método para jogar uma carta no campo de batalha
    public void jogarCartaNoCampo(Carta carta) {
        if (mao.temCarta(carta)) {
            campoDeBatalha.adicionarCartasAoCampo(carta);
        } else {
            System.out.println("A carta " + carta.getNome() + " não está na mão.");
        }
    }


    public void comprarCartas() {
        Carta cartaComprada = deck.comprarCarta();
        if (cartaComprada != null) {
            mao.adicionarCartas(cartaComprada);
            System.out.println(nome + " comprou a carta: " + cartaComprada.getNome());
        }
    }

    public void jogarCarta(Carta carta) {
        mao.removerCarta(carta);
        System.out.println(nome + " jogou a carta: " + carta.getNome());
    }

    public void enviarAoCemiterio(Carta carta) {
        cemiterio.adicionarCartasNoCemiterio(carta);
        System.out.println(carta.getNome() + " foi enviada ao cemitério.");
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

    public int getMana() {return mana;}

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

}
