package Personagens;

import MecanicasDeJogo.Abstract.Carta;
import MecanicasDeJogo.Interfaces.Atacavel;
import MecanicasDeJogo.Interfaces.Jogavel;
import MecanicasDeJogo.Jogador;

public abstract class Criatura extends Carta implements Jogavel , Atacavel   {
    private int poder;
    private int resistencia;
    private int resistenciaInicial;
    private String habilidadeEspecial;
    private boolean voa;
    private String tipo;

    public Criatura(String nome, int custoMana, int poder, int resistencia, String habilidadeEspecial, boolean voa) {
        super(nome, custoMana);
        this.poder = poder;
        this.resistencia = resistencia;
        this.resistenciaInicial = resistencia;
        this.habilidadeEspecial = habilidadeEspecial;
        this.voa = voa;
    }

    // Getters
    public int getPoder() {
        return poder;
    }

    public int getResistencia() {
        return resistencia;
    }

    public int getResistenciaInicial() {
        return resistenciaInicial;
    }

    public String getHabilidadeEspecial() {
        return habilidadeEspecial;
    }

    public boolean isVoa() {
        return voa;
    }

    @Override
    public void efeito() {
        System.out.println("Habilidade especial de " + getNome() + ": " + habilidadeEspecial);
    }

    @Override
    public void jogar() {
        System.out.println(getNome() + " entrou no campo de batalha.");
        if (voa) {
            System.out.println(getNome() + " está voando.");
        }
    }

    public abstract void atacar(Criatura alvo);

    @Override
    public abstract void receberDano(int dano);
    @Override
    public abstract void receberCura(int cura);


    public abstract void atacarJogador(Jogador jogadorAlvo) ;


    public abstract void setPoder(int novoPoder) ;


    public abstract void setResistencia(int novaVida) ;

    //metodo sobrescrito de geração de descrição da criatura
    @Override
    public String gerarDescricao(){
        return String.format("<html><b>Tipo:</b> Criatura<br><b>Nome:</b> %s<br><b>Dano:</b> %d<br><b>Mana:</b> %d<br><b>Vida:</b> %d</html>",
                getNome(), getPoder(), getCustoMana(), getResistencia());
    }


}

