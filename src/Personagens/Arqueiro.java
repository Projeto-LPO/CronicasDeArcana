package Personagens;

import MecanicasDeJogo.Interfaces.Atacavel;
import MecanicasDeJogo.Interfaces.Jogavel;
import MecanicasDeJogo.Jogador;

public class Arqueiro extends Criatura implements Jogavel, Atacavel {

    public Arqueiro(String nome, int custoMana, int poder, int resistencia) {
        super(nome, custoMana, poder, resistencia, "Atira em tropas aéreas", false);  // Define a habilidade especial
    }

    @Override
    public void atacar(Criatura alvo) {
        if (alvo == null) {
            System.out.println(getNome() + " não encontra criaturas para atacar e direciona sua flecha ao jogador.");
            return;  // Se não houver alvo, não faz nada
        }

        // Verifica se o alvo é uma criatura que voa
        if (alvo.isVoa()) {
            System.out.println(getNome() + " usa sua habilidade especial e dispara uma flecha no ar, atacando " + alvo.getNome() + ", causando " + getPoder() + " de dano.");
        } else {
            System.out.println(getNome() + " ataca " + alvo.getNome() + ", causando " + getPoder() + " de dano.");
        }
        alvo.receberDano(getPoder());  // Aplica o dano ao alvo
    }

    @Override
    public void atacarJogador(Jogador jogador) {
        System.out.println(getNome() + " não encontra criaturas para atacar e direciona sua flecha ao jogador " + jogador.getNome() + ", causando " + getPoder() + " de dano.");
        jogador.receberDano(getPoder());  // Aplica o dano ao jogador
    }

    @Override
    public void setPoder(int novoPoder) {
        this.setPoder(novoPoder);  // Chama o método da superclasse
        System.out.println(getNome() + " agora tem " + novoPoder + " de poder.");  // Mensagem personalizada
    }

    @Override
    public void setResistencia(int novaVida) {
        this.setResistencia(novaVida);  // Chama o método da superclasse
        System.out.println(getNome() + " agora tem " + novaVida + " de resistência.");  // Mensagem personalizada
    }

    @Override
    public void receberDano(int dano) {
        System.out.println(getNome() + " recebeu " + dano + " de dano.");
        setResistencia(getResistencia() - dano);  // Aplica o dano ao Arqueiro

        if (getResistencia() <= 0) {
            System.out.println(getNome() + " foi derrotado.");
        } else {
            System.out.println(getNome() + " tem " + getResistencia() + " de vida restante.");
        }
    }

    @Override
    public void receberCura(int cura) {
        setResistencia(getResistencia() + cura);
        if (getResistencia() > getResistenciaInicial()) {
            setResistencia(getResistenciaInicial());  // Garante que a resistência não ultrapasse a resistência inicial
        }
        System.out.println(getNome() + " foi curado. Vida atual: " + getResistencia());
    }

    @Override
    public void jogar() {
        System.out.println(getNome() + " entrou no campo de batalha com a habilidade especial: " + getHabilidadeEspecial());
    }
}
