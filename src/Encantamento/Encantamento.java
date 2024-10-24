package Encantamento;

import MecanicasDeJogo.Abstract.Carta;
import MecanicasDeJogo.Interfaces.Jogavel;
import MecanicasDeJogo.Jogador;
import Personagens.Criatura;

public abstract class Encantamento extends Carta implements Jogavel {
    private String efeitoContínuo;
    private int aumentoDano;
    private int aumentoCura;

    // Construtor para Encantamentos de dano
    public Encantamento(String nome, int custoMana, String efeitoContínuo, int aumentoDano) {
        super(nome, custoMana);
        this.efeitoContínuo = efeitoContínuo;
        this.aumentoDano = aumentoDano;
    }

    // Construtor para Encantamentos de cura
    public Encantamento(String nome, int custoMana, String efeitoContínuo, int aumentoCura, boolean isCura) {
        super(nome, custoMana);
        this.efeitoContínuo = efeitoContínuo;
        this.aumentoCura = aumentoCura;
    }

    @Override
    public void efeito() {

        System.out.println("Efeito contínuo de " + getNome() + ": " + efeitoContínuo);
    }

    @Override
    public void jogar() {
        System.out.println(getNome() + " foi colocado no campo de batalha");
    }
    // efeito em jogadores
   public  abstract void aplicarEfeitoDano(Jogador Oponente);
    public  abstract  void aplicarEfeitoCura(Jogador Jogador);
    // efeito em criaturas
    public  abstract void aplicarEfeitoDano(Criatura criatura);
    public  abstract  void aplicarEfeitoCura(Criatura cratura);

    public String getEfeitoContínuo() {
        return efeitoContínuo;
    }

    public int getAumentoDano() {
        return aumentoDano;
    }

    public int getAumentoCura() {
        return aumentoCura;
    }
}
