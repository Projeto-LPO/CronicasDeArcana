package Personagens;

import MecanicasDeJogo.Jogador; // Certifique-se de ter a classe Jogador no package correto

public class Arqueiro extends Criatura {

    public Arqueiro(String nome, int custoMana, int poder, int resistencia) {
        super(nome, custoMana, poder, resistencia, "Atira em tropas aéreas", false);  // Define a habilidade especial
    }


    public void atacar(Criatura alvo) {
        if (alvo == null) {
            System.out.println(getNome() + " não encontra criaturas para atacar e direciona sua flecha ao jogador.");
            return;  // Se não houver alvo, não faz nada
        }

        // Verifica se o alvo é uma criatura
        if (alvo.isVoa()) {
            System.out.println(getNome() + " usa sua habilidade especial e dispara uma flecha no ar, atacando " + alvo.getNome() + ", causando " + getPoder() + " de dano.");
        } else {
            System.out.println(getNome() + " ataca " + alvo.getNome() + ", causando " + getPoder() + " de dano.");
        }
        alvo.receberDano(getPoder());  // Aplica o dano ao alvo
    }

    @Override
    public void receberDano(int dano) {
        
    }

    @Override
    public void receberCura(int cura) {

    }

    public void atacarJogador(Jogador jogador) {
        System.out.println(getNome() + " não encontra criaturas para atacar e direciona sua flecha ao jogador " + jogador.getNome() + ", causando " + getPoder() + " de dano.");
        jogador.receberDano(getPoder());  // Aplica o dano ao jogador
    }

    @Override
    public void jogar() {
        System.out.println(getNome() + " entrou no campo de batalha com a habilidade especial: " + getHabilidadeEspecial());
    }
}
