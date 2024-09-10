public abstract class Carta {
    private String nome;
    private int custoMana;

    public Carta(String nome, int custoMana) {
        this.nome = nome;
        this.custoMana = custoMana;
    }

    public String getNome() {
        return nome;
    }

    public int getCustoMana() {
        return custoMana;
    }

    public abstract void efeito(); // Método abstrato a ser implementado por subclasses

    public abstract void jogar(); // Outro método abstrato
}
