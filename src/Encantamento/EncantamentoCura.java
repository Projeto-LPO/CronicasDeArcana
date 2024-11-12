package Encantamento;

import MecanicasDeJogo.Interfaces.Jogavel;
import MecanicasDeJogo.Jogador;
import Personagens.Criatura;

public class EncantamentoCura extends Encantamento implements Jogavel {

    public EncantamentoCura(String nome, int custoMana, String efeitoContínuo, int aumentoCura, int duracaoInicial) {
        super(nome, custoMana, efeitoContínuo, aumentoCura, duracaoInicial);
    }

    // Método da interface Jogavel para jogar o encantamento
    @Override
    public void jogar() {
        System.out.println("O encantamento " + getNome() + " foi jogado com o efeito: " + getEfeitoContínuo());
    }


    @Override
    public void efeito() {
        System.out.println("Encantamento " + getNome() + " tem o efeito contínuo: " + getEfeitoContínuo());
    }

    // Aplicar efeito de cura a uma criatura
    @Override
    public void aplicarEfeitoCura(Criatura criatura) {
        System.out.println("O encantamento " + getNome() + " está aumentando a vida da criatura " + criatura.getNome() + " em " + getAumentoCura() + ".");

        int vidaAtual = criatura.getResistencia();
        int novaVida = vidaAtual + getAumentoCura();
        criatura.setResistencia(novaVida);
        System.out.println(criatura.getNome() + " agora tem " + novaVida + " de vida.");
    }

    // Aplicar efeito de cura a um jogador
    @Override
    public void aplicarEfeitoCura(Jogador jogador) {
        System.out.println("O encantamento " + getNome() + " está aumentando a cura do jogador " + jogador.getNome() + " em " + getAumentoCura() + ".");

        int vidaAtual = jogador.getVida();
        int novaVida = vidaAtual + getAumentoCura();
        jogador.setVida(novaVida);
        System.out.println(jogador.getNome() + " agora tem " + novaVida + " de vida.");
    }

    // Métodos de dano não usados, pois este encantamento é de cura
    @Override
    public void aplicarEfeitoDano(Criatura criatura) {
        System.out.println("Método de dano não utilizado para " + getNome() + ", pois este encantamento é de cura.");
    }

    @Override
    public void aplicarEfeitoDano(Jogador oponente) {
        System.out.println("Método de dano não utilizado para " + getNome() + ", pois este encantamento é de cura.");
    }
}
