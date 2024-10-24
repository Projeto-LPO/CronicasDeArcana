package Personagens;

import MecanicasDeJogo.Interfaces.Atacavel;
import MecanicasDeJogo.Interfaces.Jogavel;
import MecanicasDeJogo.Jogador;

public class Guerreiro extends Criatura implements Jogavel, Atacavel {
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

        if (alvo != null) {
            System.out.println(getNome() + " ataca " + alvo.getNome() + ", causando " + danoFinal + " de dano.");
            alvo.receberDano(danoFinal);  // Causa dano ao alvo
        }
    }

    @Override
    public void atacarJogador(Jogador jogador) {
        verificarFuria();  // Verifica se a fúria deve ser ativada
        int danoFinal = (int) (getPoder() * furia);  // Calcula o dano com o multiplicador de fúria
        System.out.println(getNome() + " ataca o jogador " + jogador.getNome() + ", causando " + danoFinal + " de dano.");
        jogador.receberDano(danoFinal);  // Causa dano ao jogador
    }

    @Override
    public void setPoder(int novoPoder) {
        this.setPoder(novoPoder);  // Chama o método da superclasse
        System.out.println(getNome() + " agora tem " + novoPoder + " de poder.");  // Mensagem personalizada
    }

    @Override
    public void setResistencia(int novaVida) {
        this.setResistencia(novaVida);  // Chama o método da superclasse
        System.out.println(getNome() + " agora tem " + novaVida + " de resistência.");  // Mensagem personalizada
    }

    @Override
    public void receberDano(int dano) {
        System.out.println(getNome() + " recebeu " + dano + " de dano.");
        setResistencia(getResistencia() - dano);  // Aplica o dano ao Guerreiro
        verificarFuria();  // Verifica se a fúria deve ser ativada após receber dano

        if (getResistencia() <= 0) {
            System.out.println(getNome() + " foi derrotado.");
        } else {
            System.out.println(getNome() + " tem " + getResistencia() + " de vida restante.");
        }
    }

    @Override
    public void receberCura(int cura) {
        setResistencia(getResistencia() + cura);
        if (getResistencia() > getResistenciaInicial()) {
            setResistencia(getResistenciaInicial());  // Garante que a resistência não ultrapasse o valor inicial
        }
        System.out.println(getNome() + " foi curado. Vida atual: " + getResistencia());
    }

    @Override
    public void jogar() {
        super.jogar();
        System.out.println(getNome() + " está pronto para a batalha, preparado para ativar sua fúria!");
    }
}
