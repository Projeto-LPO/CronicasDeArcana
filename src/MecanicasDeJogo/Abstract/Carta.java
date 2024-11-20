package MecanicasDeJogo.Abstract;

import MecanicasDeJogo.Interfaces.Jogavel;

public abstract class Carta implements Jogavel {
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
    @Override
    public abstract void efeito(); // Método abstrato a ser implementado por subclasses
    @Override
    public abstract void jogar(); // Outro método abstrato

    public abstract String gerarDescricao();

}
