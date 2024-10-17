package Personagens;

import MecanicasDeJogo.Abstract.Carta;
import MecanicasDeJogo.Jogador; // Certifique-se de que a classe Jogador esteja no pacote correto

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

    // Getters
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

    // Método sobre efeito da criatura
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

    // Métodos abstratos a serem implementados pelas subclasses
    public abstract void atacar(Criatura alvo); // Método abstrato para atacar outra criatura
    public abstract void receberDano(int dano); // Método abstrato para receber dano
    public abstract void receberCura(int cura); // Método abstrato para receber cura

    // Método para a criatura atacar diretamente o jogador
    public void atacarJogador(Jogador jogadorAlvo) {
        if (this.voa) {
            System.out.println(getNome() + " ataca diretamente o jogador " + jogadorAlvo.getNome() + " pelo ar, causando " + poder + " de dano.");
        } else {
            System.out.println(getNome() + " ataca diretamente o jogador " + jogadorAlvo.getNome() + ", causando " + poder + " de dano.");
        }
        jogadorAlvo.receberDano(poder);
    }

    public void setPoder(int novoPoder) {
        this.poder = novoPoder;
        System.out.println(getNome() + " agora tem " + this.poder + " de poder.");
    }


    public void setResistencia(int novaVida) {
    }
}
