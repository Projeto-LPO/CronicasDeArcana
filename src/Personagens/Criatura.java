package Personagens;

import MecanicasDeJogo.Abstract.Carta;
import MecanicasDeJogo.Jogador; // Certifique-se de ter a classe Jogador no package correto

public abstract class Criatura extends Carta {
    private int poder;
    private int resistencia;
    private int resistenciaInicial; // Atributo para armazenar a resistência inicial
    private String habilidadeEspecial;
    private boolean voa;
    private String tipo;

    public Criatura(String nome, int custoMana, int poder, int resistencia, String habilidadeEspecial, boolean voa) {
        super(nome, custoMana);
        this.poder = poder;
        this.resistencia = resistencia;
        this.resistenciaInicial = resistencia; // Armazena a resistência inicial
        this.habilidadeEspecial = habilidadeEspecial;
        this.voa = voa;
    }

    // Getters e Setters
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

    // Método sobre efeito da criatura
    @Override
    public void efeito() {
        System.out.println("Habilidade especial de " + getNome() + ": " + habilidadeEspecial);
    }

    // Método para entrar no campo de batalha
    @Override
    public void jogar() {
        System.out.println(getNome() + " entrou no campo de batalha.");
        if (voa) {
            System.out.println(getNome() + " está voando.");
        }
    }

    // Método para permitir que a criatura ataque diretamente o jogador
    public void atacarJogador(Jogador jogadorAlvo) {
        if (this.voa) {
            System.out.println(getNome() + " ataca diretamente o jogador " + jogadorAlvo.getNome() + " pelo ar, causando " + poder + " de dano.");
        } else {
            System.out.println(getNome() + " ataca diretamente o jogador " + jogadorAlvo.getNome() + ", causando " + poder + " de dano.");
        }
        jogadorAlvo.receberDano(poder);
    }

    // Método para a criatura receber dano
    public void receberDano(int dano) {
        this.resistencia -= dano;
        System.out.println(getNome() + " recebeu " + dano + " de dano. Resistência atual: " + resistencia);
        if (resistencia <= 0) {
            System.out.println(getNome() + " foi derrotado!");
        }
    }

    // Método para a criatura receber cura
    public void receberCura(int cura) {
        resistencia += cura;
        System.out.println(getNome() + " foi curada em " + cura + " pontos de resistência.");
    }


}
