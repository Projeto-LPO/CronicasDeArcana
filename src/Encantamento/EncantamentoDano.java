package Encantamento;

import MecanicasDeJogo.Interfaces.Jogavel;
import MecanicasDeJogo.Jogador;
import Personagens.Criatura;

public class EncantamentoDano extends Encantamento implements Jogavel {

    public EncantamentoDano(String nome, int custoMana, String efeitoContínuo, int aumentoDano) {
        super(nome, custoMana, efeitoContínuo, aumentoDano);  // Reutilizando o campo 'aumentoDano' da classe Encantamento
    }

    // Método da interface Jogavel para jogar o encantamento
    @Override
    public void jogar() {
        System.out.println("O encantamento " + getNome() + " foi jogado com o efeito: " + getEfeitoContínuo());
    }

    // Método da interface Jogavel para exibir o efeito do encantamento
    @Override
    public void efeito() {
        System.out.println("Encantamento " + getNome() + " tem o efeito contínuo: " + getEfeitoContínuo());
    }

    // Aplicar efeito de dano a uma criatura
    @Override
    public void aplicarEfeitoDano(Criatura criatura) {
        System.out.println("O encantamento " + getNome() + " está multiplicando o dano da criatura " + criatura.getNome() + " por " + getAumentoDano() + ".");

        int danoAtual = criatura.getPoder();
        int novoDano = danoAtual * getAumentoDano();
        criatura.setPoder(novoDano);
        System.out.println(criatura.getNome() + " agora causa " + novoDano + " de dano.");
    }

    // Aplicar efeito de dano a um jogador
    @Override
    public void aplicarEfeitoDano(Jogador oponente) {
        System.out.println("O encantamento " + getNome() + " está aumentando o dano ao jogador " + oponente.getNome() + ".");
    }

    // Métodos de cura não usados
    @Override
    public void aplicarEfeitoCura(Jogador jogador) {
        System.out.println("Método de cura não utilizado para " + getNome());
    }

    @Override
    public void aplicarEfeitoCura(Criatura criatura) {
        System.out.println("Método de cura não utilizado para " + getNome());
    }
}
