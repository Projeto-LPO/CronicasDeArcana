public class FeitiçoDano extends Feitiço {

    public FeitiçoDano(String nome, int custoMana, String efeito, int valorDano) {
        super(nome, custoMana, efeito, valorDano);  // Chama o construtor da classe Feitiço
    }

    // Método para lançar o feitiço e causar dano a uma criatura
    public void lançarFeitiçoDano(Criatura alvo) {
        System.out.println(getNome() + " foi lançado e causou " + getValorDano() + " de dano a " + alvo.getNome());
        alvo.receberDano(getValorDano());  // Aplica o dano à criatura alvo
    }

    @Override
    public void efeito() {
        System.out.println("Feitiço de dano " + getNome() + " tem o efeito: " + getEfeito());
    }

    @Override
    public void jogar() {
        System.out.println(getNome() + " foi conjurado com o efeito: " + getEfeito());
    }

    // Método específico para aplicar o dano à criatura rival
    public void aplicarDano(Criatura alvo) {
        System.out.println(getNome() + " está aplicando " + getValorDano() + " de dano a " + alvo.getNome());
        lançarFeitiçoDano(alvo);  // Chama o método que causa o dano à criatura
    }
}
