package Personagens;

import MecanicasDeJogo.Abstract.Carta;

public abstract class Criatura extends Carta {
    private int poder;
    private int resistencia;
    private int resistenciaInicial; // Atributo para armazenar a resistência inicial
    private String habilidadeEspecial;
    private boolean voa;

    public Criatura(String nome, int custoMana, int poder, int resistencia, String habilidadeEspecial, boolean voa) {
        super(nome, custoMana);
        this.poder = poder;
        this.resistencia = resistencia;
        this.resistenciaInicial = resistencia; // Armazena a resistência inicial
        this.habilidadeEspecial = habilidadeEspecial;
        this.voa = voa;
    }

    public int getPoder() {
        return poder;
    }

    public int getResistencia() {
        return resistencia;
    }

    public int getResistenciaInicial() {
        return resistenciaInicial; // Método para acessar a resistência inicial
    }

    public String getHabilidadeEspecial() {
        return habilidadeEspecial;
    }

    public boolean isVoa() {
        return voa;
    }

    public void setVoa(boolean voa) {
        this.voa = voa;
    }


    @Override
    public void efeito() {
        System.out.println("Habilidade especial de " + getNome() + ": " + habilidadeEspecial);
    }

    @Override
    public void jogar() {
        System.out.println(getNome() + " entrou no campo de batalha.");
        if (voa) {
            System.out.println(getNome() + " está voando.");
        }
    }

    // Novo método para verificar se a criatura pode atacar um alvo
    public void atacar(Criatura alvo) {
        if (this.voa && !alvo.isVoa()) {
            System.out.println(getNome() + " ataca diretamente o jogador inimigo, ignorando criaturas terrestres.");
        } else {
            System.out.println(getNome() + " ataca " + alvo.getNome() + " causando " + poder + " de dano.");
            alvo.receberDano(poder);
        }
    }

    public void receberDano(int dano) {
        resistencia -= dano;
        if (resistencia <= 0) {
            System.out.println(getNome() + " foi destruída.");
        }


        }
    public void receberCura(int cura) {
        resistencia += cura;
        System.out.println(getNome() + " foi curada em " + cura + " pontos de resistência.");
    }

}

