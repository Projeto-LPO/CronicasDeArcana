public class Encantamento extends Carta {
    private String efeitoContínuo;

    public Encantamento(String nome, int custoMana, String efeitoContínuo) {
        super(nome, custoMana);
        this.efeitoContínuo = efeitoContínuo;
    }

    @Override
    public void efeito() {
        // Implementação do efeito contínuo
        System.out.println("Efeito contínuo de " + getNome() + ": " + efeitoContínuo);
    }

    @Override
    public void jogar() {
        System.out.println(getNome() + " foi colocado no campo de batalha");
    }
}
