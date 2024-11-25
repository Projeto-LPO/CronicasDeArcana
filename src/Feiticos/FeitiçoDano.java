package Feiticos;

import MecanicasDeJogo.Interfaces.Jogavel;
import MecanicasDeJogo.Jogador;
import Personagens.Criatura;

import javax.swing.*;

public class FeitiçoDano extends Feitiço implements Jogavel {

    public FeitiçoDano(String nome, int custoMana, String efeito, int valorDano) {
        super(nome, custoMana, efeito, valorDano);
    }

    // Aplicar dano a um jogador
    @Override
    public void aplicarEfeitoDano(Jogador alvo) {
        System.out.println(getNome() + " foi lançado e causou " + getValorDano() + " de dano ao jogador " + alvo.getNome());
        alvo.receberDano(getValorDano());
        JOptionPane.showMessageDialog(null,getNome() + " foi lançado e causou " + getValorDano() + " de dano ao jogador " + alvo.getNome());
    }

    // Aplicar dano a uma criatura
    @Override
    public void aplicarEfeitoDano(Criatura criatura) {
        System.out.println("Aderito!!!");
        System.out.println(getNome() + " foi lançado e causou " + getValorDano() + " de dano à criatura " + criatura.getNome());
        criatura.receberDano(getValorDano());
        JOptionPane.showMessageDialog(null,getNome() + " foi lançado e causou " + getValorDano() + " de dano à criatura " + criatura.getNome());
    }

    @Override
    public void efeito() {
        System.out.println("Feiticos.Feitiço de dano " + getNome() + " tem o efeito: " + getEfeito());
    }

    @Override
    public void jogar() {
        System.out.println(getNome() + " foi conjurado com o efeito: " + getEfeito());
    }

    // Métodos de cura não utilizados, mas obrigatórios por causa da classe abstrata
    @Override
    public void aplicarEfeitoCura(Criatura criatura) {
        System.out.println("Método de cura não é utilizado para " + getNome());
    }

    @Override
    public void aplicarEfeitoCura(Jogador alvo) {
        System.out.println("Método de cura não é utilizado para " + getNome());
    }

    //metodo sobrescrito de geração de descrição do feitiço de dano
    @Override
    public String gerarDescricao(){
        return String.format("<html><b>Tipo:</b> Feitiço de Dano<br><b>Nome:</b> %s<br><b>Dano Mágico:</b> %d<br><b>Mana:</b> %d</html>",
        getNome(), getValorCura(), getCustoMana());
    }

}
