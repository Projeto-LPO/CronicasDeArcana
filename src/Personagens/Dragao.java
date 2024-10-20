package Personagens;

import java.util.List;

import MecanicasDeJogo.Interfaces.Atacavel;
import MecanicasDeJogo.Interfaces.Jogavel;
import MecanicasDeJogo.Jogador;

public class Dragao extends Criatura implements Jogavel, Atacavel {

    public Dragao(String nome, int custoMana, int poder, int resistencia) {
        super(nome, custoMana, poder, resistencia, "Voar", true);
    }

    @Override
    public void jogar() {

        System.out.println(getNome() + " entrou no campo de batalha com a habilidade especial: " + getHabilidadeEspecial());
    }

    @Override
    public void atacar(Criatura alvo) {
        if (alvo == null) {
            System.out.println(getNome() + " não encontrou nenhuma criatura para atacar.");
            return;
        }

        if (alvo.isVoa()) {
            System.out.println(getNome() + " ataca " + alvo.getNome() + " no ar, causando " + getPoder() + " de dano.");
        } else {
            System.out.println(getNome() + " ataca " + alvo.getNome() + " no chão, causando " + getPoder() + " de dano.");
        }
        alvo.receberDano(getPoder());  // Aplica o dano ao alvo
    }

    @Override
    public void receberDano(int dano) {
        System.out.println(getNome() + " recebeu " + dano + " de dano.");
        setResistencia(getResistencia() - dano);  // Aplica o dano ao Dragão

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


    public void atacar(List<Criatura> criaturas, Jogador jogadorAlvo) {
        boolean atacouCriaturaAerea = false;


        for (Criatura criatura : criaturas) {
            if (criatura.isVoa()) {
                System.out.println(getNome() + " ataca " + criatura.getNome() + " no ar, causando " + getPoder() + " de dano.");
                criatura.receberDano(getPoder());
                atacouCriaturaAerea = true;
                break;
            }
        }


        if (!atacouCriaturaAerea) {
            System.out.println(getNome() + " ataca diretamente o jogador " + jogadorAlvo.getNome() + ", causando " + getPoder() + " de dano.");
            jogadorAlvo.receberDano(getPoder());
        }
    }
}
