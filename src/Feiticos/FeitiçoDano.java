package Feiticos;

import MecanicasDeJogo.Interfaces.Jogavel;
import MecanicasDeJogo.Jogador;
import Personagens.Criatura;

public class FeitiçoDano extends Feitiço implements Jogavel {

    public FeitiçoDano(String nome, int custoMana, String efeito, int valorDano) {
        super(nome, custoMana, efeito, valorDano);
    }

    @Override
    public void aplicarEfeitoDano(Jogador alvo) {

    }

    @Override
    public void aplicarEfeitoDano(Criatura criatura) {

    }

    @Override
    public void aplicarEfeitoCura(Criatura criatura) {

    }

    @Override
    public void aplicarEfeitoCura(Jogador alvo) {

    }

    // Método para lançar o feitiço e causar dano a uma criatura
    public void lançarFeitiçoDano(Criatura alvo) {
        System.out.println(getNome() + " foi lançado e causou " + getValorDano() + " de dano a " + alvo.getNome());
        alvo.receberDano(getValorDano());
    }

    @Override
    public void efeito() {
        System.out.println("Feiticos.Feitiço de dano " + getNome() + " tem o efeito: " + getEfeito());
    }

    @Override
    public void jogar() {
        System.out.println(getNome() + " foi conjurado com o efeito: " + getEfeito());
    }


    public void aplicarDano(Criatura alvo) {
        System.out.println(getNome() + " está aplicando " + getValorDano() + " de dano a " + alvo.getNome());
        lançarFeitiçoDano(alvo);
    }
}
