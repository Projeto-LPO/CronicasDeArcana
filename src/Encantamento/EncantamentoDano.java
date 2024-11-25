package Encantamento;

import MecanicasDeJogo.Interfaces.Jogavel;
import MecanicasDeJogo.Jogador;
import Personagens.Criatura;

import javax.swing.*;

public class EncantamentoDano extends Encantamento implements Jogavel {

    public EncantamentoDano(String nome, int custoMana, String efeitoContínuo, int aumentoDano, int duracaoInicial) {
        super(nome, custoMana, efeitoContínuo, aumentoDano, duracaoInicial);
    }

    @Override
    public void jogar() {
        System.out.println("O encantamento " + getNome() + " foi jogado com o efeito: " + getEfeitoContínuo());
    }

    @Override
    public void efeito() {
        System.out.println("Encantamento " + getNome() + " tem o efeito contínuo: " + getEfeitoContínuo());
    }

    // Aplicar efeito de dano a uma criatura
    @Override
    public void aplicarEfeitoDano(Criatura criatura) {
        System.out.println("O encantamento " + getNome() + " está multiplicando o dano da criatura " + criatura.getNome() + " por " + getAumentoDano() + ".");
        JOptionPane.showMessageDialog(null, "O encantamento " + getNome() + " está multiplicando o dano da criatura " + criatura.getNome() + " por " + getAumentoDano() + ".");
        int danoAtual = criatura.getPoder();
        int novoDano = danoAtual * getAumentoDano();
        criatura.setPoder(novoDano);
        System.out.println(criatura.getNome() + " agora causa " + novoDano + " de dano.");
    }

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

    //metodo sobrescrito de geração de descrição do encantamento de dano
    @Override
    public String gerarDescricao(){
        return String.format( "<html><b>Tipo:</b> Encantamento de Dano<br><b>Nome:</b> %s<br><b>Dano:</b> %d<br><b>Mana:</b> %d</html>",
                getNome(), getAumentoDano(), getCustoMana());
    }


}
