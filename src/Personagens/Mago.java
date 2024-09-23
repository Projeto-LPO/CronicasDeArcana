package Personagens;

public class Mago extends Criatura {
    private boolean escudoMagicoAtivo;

    public Mago(String nome, int custoMana, int poder, int resistencia) {
        super(nome, custoMana, poder, resistencia, "Escudo Mágico", false); // Personagens.Mago não voa
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
        verificarEscudo();
        if (escudoMagicoAtivo) {
            dano /= 2;
            System.out.println(getNome() + " está protegido pelo Escudo Mágico, recebendo apenas " + dano + " de dano.");
        }
        super.receberDano(dano);
    }

    @Override
    public void jogar() {
        super.jogar();
        System.out.println(getNome() + " está preparado com o Escudo Mágico.");
    }
}
