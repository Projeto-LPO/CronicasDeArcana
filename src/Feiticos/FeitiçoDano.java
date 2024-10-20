package Feiticos;

import MecanicasDeJogo.Interfaces.Jogavel;
import MecanicasDeJogo.Jogador;
import Personagens.Criatura;

public class FeitiçoDano extends Feitiço implements Jogavel {

    public FeitiçoDano(String nome, int custoMana, String efeito, int valorDano) {
        super(nome, custoMana, efeito, valorDano);
    }

    // Aplicar dano a um jogador
    @Override
    public void aplicarEfeitoDano(Jogador alvo) {
        System.out.println(getNome() + " foi lançado e causou " + getValorDano() + " de dano ao jogador " + alvo.getNome());
        alvo.receberDano(getValorDano());
    }

    // Aplicar dano a uma criatura
    @Override
    public void aplicarEfeitoDano(Criatura criatura) {
        System.out.println(getNome() + " foi lançado e causou " + getValorDano() + " de dano à criatura " + criatura.getNome());
        criatura.receberDano(getValorDano());
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

    // Método auxiliar para aplicar dano a uma criatura
    public void aplicarDano(Criatura alvo) {
        System.out.println(getNome() + " está aplicando " + getValorDano() + " de dano a " + alvo.getNome());
        lançarFeitiçoDano(alvo);
    }

    // Métodos de cura não utilizados, mas obrigatórios por causa da classe abstrata
    @Override
    public void aplicarEfeitoCura(Criatura criatura) {
        System.out.println("Método de cura não é utilizado para " + getNome());
    }

    @Override
    public void aplicarEfeitoCura(Jogador alvo) {
        System.out.println("Método de cura não é utilizado para " + getNome());
    }


}
