package Personagens;

import MecanicasDeJogo.Interfaces.Atacavel;
import MecanicasDeJogo.Interfaces.Jogavel;
import MecanicasDeJogo.Jogador;

public class Mago extends Criatura implements Jogavel, Atacavel {
    private boolean escudoMagicoAtivo;

    public Mago(String nome, int custoMana, int poder, int resistencia) {
        super(nome, custoMana, poder, resistencia, "Escudo Mágico", false);
        this.escudoMagicoAtivo = false;
    }

    public void ativarEscudoMagico() {
        this.escudoMagicoAtivo = true;
        System.out.println(getNome() + " ativou o Escudo Mágico, reduzindo o dano recebido pela metade.");
    }

    private void verificarEscudo() {
        if (getResistencia() <= getResistenciaInicial() / 2 && !escudoMagicoAtivo) {
            ativarEscudoMagico();
        }
    }

    @Override
    public void receberDano(int dano) {
        verificarEscudo(); // Verifica se o escudo deve ser ativado

        if (escudoMagicoAtivo) {
            dano /= 2; // Reduz o dano pela metade se o escudo estiver ativo
            System.out.println(getNome() + " está protegido pelo Escudo Mágico, recebendo apenas " + dano + " de dano.");
        } else {
            System.out.println(getNome() + " recebeu " + dano + " de dano.");
        }


        int novaResistencia = Math.max(0, getResistencia() - dano);
        setResistencia(novaResistencia);


        if (novaResistencia == 0) {
            System.out.println(getNome() + " foi derrotado.");
        } else {
            System.out.println(getNome() + " agora tem " + novaResistencia + " de vida.");
        }
    }

    @Override
    public void receberCura(int cura) {
        int novaResistencia = getResistencia() + cura;
        setResistencia(Math.min(novaResistencia, getResistenciaInicial()));
        System.out.println(getNome() + " foi curado. Vida atual: " + getResistencia());
    }

    @Override
    public void jogar() {
        super.jogar();
        System.out.println(getNome() + " está preparado com o Escudo Mágico.");
    }

    @Override
    public void atacar(Criatura alvo) {
        System.out.println(getNome() + " lança um feitiço em " + alvo.getNome() + ", causando " + getPoder() + " de dano.");
        alvo.receberDano(getPoder());
    }

    // Método para atacar diretamente o jogador
    public void atacarJogador(Jogador jogador) {
        System.out.println(getNome() + " não encontra criaturas para atacar e lança um feitiço diretamente no jogador " + jogador.getNome() + ", causando " + getPoder() + " de dano.");
        jogador.receberDano(getPoder());
    }

    @Override
    public void setPoder(int novoPoder) {
        super.setPoder(novoPoder);
        System.out.println(getNome() + " agora tem " + novoPoder + " de poder.");
    }

    @Override
    public void setResistencia(int novaResistencia) {

        super.setResistencia(novaResistencia);
        System.out.println(getNome() + " agora tem " + novaResistencia + " de resistência.");
    }


}
