package Feiticos;

import MecanicasDeJogo.Abstract.Carta;
import MecanicasDeJogo.Jogador;

public abstract class Feitiço extends Carta {
    private String efeito;
    private int valorCura;
    private int valorDano;

    public Feitiço(String nome, int custoMana, String efeito, int valorDano) {
        super(nome, custoMana);
        this.efeito = efeito;
        this.valorDano = valorDano;
        this.valorCura = 0; // Sem cura para feitiços de dano
    }

    public Feitiço(String nome, int custoMana, String efeito, int valorCura, boolean cura) {
        super(nome, custoMana);
        this.efeito = efeito;
        this.valorCura = valorCura;
        this.valorDano = 0; // Sem dano para feitiços de cura
    }

    public void lançarFeitiçoDano(Jogador alvo) {
        System.out.println(getNome() + " foi lançado e causou " + valorDano + " de dano a " + alvo.getNome());
        alvo.receberDano(valorDano);
    }

    // Método para lançar feitiço de cura em um jogador
    public void lançarFeitiçoCura(Jogador alvo) {
        System.out.println(getNome() + " foi lançado e curou " + valorCura + " pontos de vida de " + alvo.getNome());
        alvo.receberCura(valorCura);
    }

    @Override
    public void efeito() {
        // Implementação do efeito do feitiço
        System.out.println("Efeito de " + getNome() + ": " + efeito);
    }

    @Override
    public void jogar() {
        System.out.println(getNome() + " foi conjurado com o efeito: " + efeito);
    }


    public void aplicarEfeito(Jogador alvo) {
        if (valorDano > 0) {
            lançarFeitiçoDano(alvo);
        } else if (valorCura > 0) {
            lançarFeitiçoCura(alvo);
        } else {
            System.out.println("Efeito especial do feitiço " + getNome() + " foi ativado.");
        }
    }

    public  int getValorDano(){
        return  valorDano;
    }

    public int getValorCura() {
        return valorCura;
    }

    public String getEfeito(){
        return  efeito;
    }
}
