public class Feitiço extends Carta {
    private String efeito;

    public Feitiço(String nome, int custoMana, String efeito) {
        super(nome, custoMana);
        this.efeito = efeito;
    }

    @Override
    public void efeito() {
        // Implementação do efeito do feitiço
        System.out.println("Efeito de " + getNome() + ": " + efeito);
    }

    @Override
    public void jogar() {
        System.out.println(getNome() + " foi conjurado com o efeito: " + efeito);
    }

    public void aplicarEfeito(Jogador alvo) {
        System.out.println("Efeito aplicado no jogador " + alvo.getNome());
    }
}
