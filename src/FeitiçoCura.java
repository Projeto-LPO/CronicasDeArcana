public class FeitiçoCura extends Feitiço {

    public FeitiçoCura(String nome, int custoMana, String efeito, int valorCura) {
        super(nome, custoMana, efeito, valorCura, true);  // Chama o construtor da classe Feitiço com cura
    }

    // Método para lançar o feitiço e curar uma criatura
    public void lançarFeitiçoCura(Criatura criatura) {
        System.out.println(getNome() + " foi lançado e curou " + getValorCura() + " pontos de resistência de " + criatura.getNome());
        criatura.receberCura(getValorCura());
    }

    @Override
    public void efeito() {
        System.out.println("Feitiço de cura " + getNome() + " tem o efeito: " + getEfeito());
    }


    public void jogar(Criatura criatura) {
        System.out.println(getNome() + " foi conjurado e restaurou " + getValorCura() + " pontos de resistência de " + criatura.getNome());
        lançarFeitiçoCura(criatura);  // Cura a criatura alvo
    }
}
