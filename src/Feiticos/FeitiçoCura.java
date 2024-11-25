package Feiticos;

import MecanicasDeJogo.Interfaces.Jogavel;
import MecanicasDeJogo.Jogador;
import Personagens.Criatura;

import javax.swing.*;

public class FeitiçoCura extends Feitiço implements Jogavel {

    public FeitiçoCura(String nome, int custoMana, String efeito, int valorCura) {
        super(nome, custoMana, efeito, valorCura, true);
    }

    @Override
    public void aplicarEfeitoDano(Jogador alvo) {

        System.out.println(getNome() + " não pode causar dano a jogadores.");
    }

    @Override
    public void aplicarEfeitoDano(Criatura criatura) {

        System.out.println(getNome() + " não pode causar dano a criaturas.");
    }

    @Override
    public void aplicarEfeitoCura(Criatura criatura) {
        System.out.println(getNome() + " foi lançado e curou " + getValorCura() + " pontos de vida de " + criatura.getNome());
        criatura.receberCura(getValorCura());
        JOptionPane.showMessageDialog(null, getNome() + " foi lançado e curou " + getValorCura() + " pontos de vida de " + criatura.getNome());
    }

    @Override
    public void aplicarEfeitoCura(Jogador alvo) {
        System.out.println(getNome() + " foi lançado e curou " + getValorCura() + " pontos de vida de " + alvo.getNome());
        alvo.receberCura(getValorCura());
        JOptionPane.showMessageDialog(null, getNome() + " foi lançado e curou " + getValorCura() + " pontos de vida de " + alvo.getNome());
    }

    @Override
    public void efeito() {
        System.out.println("Feitiço de cura " + getNome() + " tem o efeito: " + getEfeito());
    }

    // Método sobrecarregado para conjurar o feitiço e curar um jogador
    public void jogar(Jogador alvo) {
        System.out.println(getNome() + " foi conjurado e restaurou " + getValorCura() + " pontos de resistência de " + alvo.getNome());
        aplicarEfeitoCura(alvo);  // Cura o jogador alvo
        JOptionPane.showMessageDialog(null, getNome() + " foi conjurado e restaurou " + getValorCura() + " pontos de resistência de " + alvo.getNome());
    }

    //metodo sobrescrito de geração de descrição do feitiço de cura
    @Override
    public String gerarDescricao() {
        return String.format("<html><b>Tipo:</b> Encantamento de Cura<br><b>Nome:</b> %s<br><b>Cura:</b> %d<br><b>Mana:</b> %d</html>",
        getNome(), getValorCura(), getCustoMana());
    }

}
