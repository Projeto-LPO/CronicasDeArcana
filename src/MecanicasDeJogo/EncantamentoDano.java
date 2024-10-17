package MecanicasDeJogo;

import Personagens.Criatura;


public class EncantamentoDano extends Encantamento {


    public EncantamentoDano(String nome, int custoMana, String efeitoContínuo, int aumentoDano) {
        super(nome, custoMana, efeitoContínuo, aumentoDano);  // Reutilizando o campo 'aumentoDano' da classe Encantamento
    }


    @Override
    public void aplicarEfeitoDano(Criatura criatura) {
        System.out.println("O encantamento " + getNome() + " está multiplicando o dano da criatura " + criatura.getNome() + " por " + getAumentoDano() + ".");

        int danoAtual = criatura.getPoder();
        int novoDano = danoAtual * getAumentoDano();
        criatura.setPoder(novoDano);
        System.out.println(criatura.getNome() + " agora causa " + novoDano + " de dano.");
    }

    @Override
    public void aplicarEfeitoDano(Jogador oponente) {
        System.out.println("O encantamento " + getNome() + " está aumentando o dano ao jogador " + oponente.getNome() + ".");
    }

    @Override
    public void aplicarEfeitoCura(Jogador jogador) {
    }

    @Override
    public void aplicarEfeitoCura(Criatura criatura) {
    }
}
