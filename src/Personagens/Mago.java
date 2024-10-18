package Personagens;

import MecanicasDeJogo.Jogador; // Certifique-se de importar corretamente a classe Jogador

public class Mago extends Criatura {
    private boolean escudoMagicoAtivo;

    public Mago(String nome, int custoMana, int poder, int resistencia) {
        super(nome, custoMana, poder, resistencia, "Escudo Mágico", false); // Mago não voa
        this.escudoMagicoAtivo = false;
    }

    public void ativarEscudoMagico() {
        this.escudoMagicoAtivo = true;
        System.out.println(getNome() + " ativou o Escudo Mágico, reduzindo o dano recebido pela metade.");
    }

    private void verificarEscudo() {
        // Ativa o escudo mágico se a resistência estiver abaixo de metade da inicial
        if (getResistencia() <= getResistenciaInicial() / 2 && !escudoMagicoAtivo) {
            ativarEscudoMagico();
        }
    }

    @Override
    public void receberDano(int dano) {
        verificarEscudo(); // Verifica se o escudo deve ser ativado
        if (escudoMagicoAtivo) {
            dano /= 2; // Dano reduzido se o escudo mágico estiver ativo
            System.out.println(getNome() + " está protegido pelo Escudo Mágico, recebendo apenas " + dano + " de dano.");
        } else {
            System.out.println(getNome() + " recebeu " + dano + " de dano.");
        }
        super.receberDano(dano); // Chama o método da classe pai para aplicar o dano
    }

    @Override
    public void receberCura(int cura) {

    }

    @Override
    public void jogar() {
        super.jogar();
        System.out.println(getNome() + " está preparado com o Escudo Mágico.");
    }



    public void atacar(Criatura alvo) {
        System.out.println(getNome() + " lança um feitiço em " + alvo.getNome() + ", causando " + getPoder() + " de dano.");
        alvo.receberDano(getPoder());  // Aplica o dano à criatura
    }

    // Método para atacar diretamente o jogador
    public void atacarJogador(Jogador jogador) {
        System.out.println(getNome() + " não encontra criaturas para atacar e lança um feitiço diretamente no jogador " + jogador.getNome() + ", causando " + getPoder() + " de dano.");
        jogador.receberDano(getPoder());  // Aplica o dano ao jogador
    }
}
