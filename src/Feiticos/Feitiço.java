package Feiticos;

import MecanicasDeJogo.Abstract.Carta;
import MecanicasDeJogo.Interfaces.Jogavel;
import MecanicasDeJogo.Jogador;
import Personagens.Criatura;

public abstract class Feitiço extends Carta implements Jogavel {
    private String efeito;
    private int valorCura;
    private int valorDano;


    public Feitiço(String nome, int custoMana, String efeito, int valorDano) {
        super(nome, custoMana);
        this.efeito = efeito;
        this.valorDano = valorDano;
        this.valorCura = 0;
    }


    public Feitiço(String nome, int custoMana, String efeito, int valorCura, boolean cura) {
        super(nome, custoMana);
        this.efeito = efeito;
        this.valorCura = valorCura;
        this.valorDano = 0;
    }


    public abstract void aplicarEfeitoDano(Jogador alvo);
    public abstract  void aplicarEfeitoDano(Criatura criatura);
    public  abstract  void aplicarEfeitoCura(Criatura criatura);
    public abstract void aplicarEfeitoCura(Jogador alvo);

    @Override
    public void jogar() {
        System.out.println(getNome() + " foi conjurado com o efeito: " + efeito);
        efeito();
    }

    @Override
    public void efeito() {
        System.out.println("Efeito do feitiço " + getNome() + ": " + efeito);
    }

    // Getters
    public int getValorDano() {
        return valorDano;
    }

    public int getValorCura() {
        return valorCura;
    }

    public String getEfeito() {
        return efeito;
    }
}
