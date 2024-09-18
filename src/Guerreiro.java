public class Guerreiro extends Criatura {
    private double furia;  // Atributo para definir o aumento de dano
    private boolean furiaAtivada;

    public Guerreiro(String nome, int custoMana, int poder, int resistencia) {
        super(nome, custoMana, poder, resistencia, "Fúria", false);  // Guerreiros não voam
        this.furia = 1.0;  // Inicializando o atributo furia sem aumento inicial
        this.furiaAtivada = false;
    }

    public double getFuria() {
        return furia;
    }

    public void setFuria(double furia) {
        this.furia = furia;
    }

    private void ativarFuria() {
        if (!furiaAtivada) {
            this.furia *= 2;  // Dobra o dano
            furiaAtivada = true;
            System.out.println(getNome() + " ativou a Fúria, dobrando o dano causado!");
        }
    }

    private void verificarFuria() {
        if (getResistencia() <= getResistenciaInicial() / 2 && !furiaAtivada) {
            ativarFuria();  // Ativa a fúria se a resistência estiver pela metade
        }
    }

    @Override
    public void atacar(Criatura alvo) {
        verificarFuria();  // Verifica se a fúria deve ser ativada
        int danoFinal = (int) (getPoder() * furia);  // Calcula o dano com o multiplicador de fúria

        if (this.isVoa() && !alvo.isVoa()) {
            System.out.println(getNome() + " ataca diretamente o jogador inimigo, ignorando criaturas terrestres.");
        } else {
            System.out.println(getNome() + " ataca " + alvo.getNome() + " causando " + danoFinal + " de dano.");
            alvo.receberDano(danoFinal);
        }
    }

    @Override
    public void receberDano(int dano) {
        verificarFuria();  // Verifica se a fúria deve ser ativada ao receber dano
        super.receberDano(dano);
    }

    @Override
    public void jogar() {
        super.jogar();
        System.out.println(getNome() + " está pronto para a batalha, preparado para ativar sua fúria!");
    }
}
