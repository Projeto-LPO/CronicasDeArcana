public class Dragão extends Criatura {

    public Dragão(String nome, int custoMana, int poder, int resistencia) {
        super(nome, custoMana, poder, resistencia, "Voar", true);  // A habilidade especial é "Voar"
    }

    @Override
    public void jogar() {
        System.out.println(getNome() + " entrou no campo de batalha com a habilidade especial: " + getHabilidadeEspecial());
    }

    @Override
    public void atacar(Criatura alvo) {
        if (alvo.isVoa()) {
            System.out.println(getNome() + " ataca " + alvo.getNome() + " no ar, causando " + getPoder() + " de dano.");
        } else {
            System.out.println(getNome() + " voa sobre " + alvo.getNome() + " e ataca sem ser bloqueado, causando " + getPoder() + " de dano.");
        }
        alvo.receberDano(getPoder());
    }
}
