public class Criatura extends Carta {
    private int poder;
    private int resistencia;
    private String habilidadeEspecial;

    public Criatura(String nome, int custoMana, int poder, int resistencia, String habilidadeEspecial) {
        super(nome, custoMana);
        this.poder = poder;
        this.resistencia = resistencia;
        this.habilidadeEspecial = habilidadeEspecial;
    }

    public int getPoder() {
        return poder;
    }

    public int getResistencia() {
        return resistencia;
    }

    public String getHabilidadeEspecial() {
        return habilidadeEspecial;
    }

    @Override
    public void efeito() {
        // Implementação do efeito da habilidade especial
        System.out.println("Habilidade especial de " + getNome() + ": " + habilidadeEspecial);
    }

    @Override
    public void jogar() {
        System.out.println(getNome() + " entrou no campo de batalha");
    }

    public void atacar(Criatura alvo) {
        System.out.println(getNome() + " ataca " + alvo.getNome() + " causando " + poder + " de dano");
        alvo.receberDano(poder);
    }

    public void receberDano(int dano) {
        resistencia -= dano;
        if (resistencia <= 0) {
            System.out.println(getNome() + " foi destruída");
        }
    }
}
