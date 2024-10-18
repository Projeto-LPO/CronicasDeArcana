package MecanicasDeJogo;

import MecanicasDeJogo.Interfaces.Jogavel;
import Personagens.Criatura;

public class EncantamentoCura extends Encantamento implements Jogavel {


    public EncantamentoCura(String nome, int custoMana, String efeitoContínuo, int aumentoCura) {
        super(nome, custoMana, efeitoContínuo,  aumentoCura);
    }

    @Override
    public void aplicarEfeitoCura(Criatura criatura) {
        System.out.println("O encantamento " + getNome() + " está aumentando a cura da criatura " + criatura.getNome() + " em " + getAumentoCura() + ".");

        int vidaAtual = criatura.getResistencia();
        int novaVida = vidaAtual + getAumentoCura();
        criatura.setResistencia(novaVida);
        System.out.println(criatura.getNome() + " agora tem " + novaVida + " de vida.");
    }


    @Override
    public void aplicarEfeitoCura(Jogador jogador) {
        System.out.println("O encantamento " + getNome() + " está aumentando a cura do jogador " + jogador.getNome() + " em " + getAumentoCura() + ".");
        int vidaAtual = jogador.getVida();
        int novaVida = vidaAtual + getAumentoCura();
        jogador.setVida(novaVida);
        System.out.println(jogador.getNome() + " agora tem " + novaVida + " de vida.");
    }


    @Override
    public void aplicarEfeitoDano(Criatura criatura) {
        // Não faz nada, pois este encantamento é de cura
    }

    @Override
    public void aplicarEfeitoDano(Jogador oponente) {
        // Não faz nada, pois este encantamento é de cura
    }
}

