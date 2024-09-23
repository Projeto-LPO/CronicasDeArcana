package Personagens;

public class Arqueiro extends Criatura {

    public Arqueiro(String nome, int custoMana, int poder, int resistencia) {
        super(nome, custoMana, poder, resistencia, "Atira em tropas aéreas", false);  // Define a habilidade especial
    }

    @Override
    public void atacar(Criatura alvo) {
        if (alvo.isVoa()) {
            System.out.println(getNome() + " usa sua habilidade especial e dispara uma flecha no ar, atacando " + alvo.getNome() + " causando " + getPoder() + " de dano.");
        } else {
            System.out.println(getNome() + " ataca " + alvo.getNome() + " causando " + getPoder() + " de dano.");
        }
        alvo.receberDano(getPoder());  // Aplica o dano ao alvo
    }

    @Override
    public void jogar() {
        System.out.println(getNome() + " entrou no campo de batalha com a habilidade especial: " + getHabilidadeEspecial());
    }
}
