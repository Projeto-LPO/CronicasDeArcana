package Personagens;

import java.util.List;
import MecanicasDeJogo.Interfaces.Atacavel;
import MecanicasDeJogo.Interfaces.Jogavel;
import MecanicasDeJogo.Jogador;
import javax.swing.*;

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
            JOptionPane.showMessageDialog(null, getNome() + " não encontrou nenhuma criatura para atacar.");
            return;
        }

        if (alvo.isVoa()) {
            JOptionPane.showMessageDialog(null,getNome() + " ataca " + alvo.getNome() + " no ar, causando " + getPoder() + " de dano.");
        } else {
            JOptionPane.showMessageDialog(null,getNome() + " ataca " + alvo.getNome() + " no chão, causando " + getPoder() + " de dano.");
        }
        alvo.receberDano(getPoder());
    }

    @Override
    public void receberDano(int dano) {
        JOptionPane.showMessageDialog(null,getNome() + " recebeu " + dano + " de dano.");
        int novaResistencia = Math.max(0, getResistencia() - dano);
        setResistencia(novaResistencia);

        if (novaResistencia == 0) {
            JOptionPane.showMessageDialog(null,getNome() + " foi derrotado.");
        } else {
            JOptionPane.showMessageDialog(null,getNome() + " tem " + novaResistencia + " de vida restante.");
        }
    }

    @Override
    public void receberCura(int cura) {
        setResistencia(getResistencia() + cura);
        if (getResistencia() > getResistenciaInicial()) {
            setResistencia(getResistenciaInicial());  // Garante que a resistência não ultrapasse a resistência inicial
        }
        JOptionPane.showMessageDialog(null,getNome() + " foi curado. Vida atual: " + getResistencia());
    }

    @Override
    public void atacarJogador(Jogador jogadorAlvo) {
        if (isVoa()) {
            JOptionPane.showMessageDialog(null,getNome() + " ataca diretamente o jogador " + jogadorAlvo.getNome() + " pelo ar, causando " + getPoder() + " de dano.");
        } else {
            JOptionPane.showMessageDialog(null,getNome() + " ataca diretamente o jogador " + jogadorAlvo.getNome() + ", causando " + getPoder() + " de dano.");
        }
        jogadorAlvo.receberDano(getPoder());
    }

    @Override
    public void setPoder(int novoPoder) {
        super.setPoder(novoPoder);  // Altera o poder do dragão
        System.out.println(getNome() + " agora tem " + getPoder() + " de poder.");
    }

    @Override
    public void setResistencia(int novaResistencia) {
        // Altera a resistência do dragão

        super.setResistencia(novaResistencia);
        System.out.println(getNome() + " agora tem " + getResistencia() + " de resistência.");
    }

}
