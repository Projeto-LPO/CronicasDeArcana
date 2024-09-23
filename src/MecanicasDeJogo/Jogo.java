package MecanicasDeJogo;

import MecanicasDeJogo.Abstract.Carta;
import Personagens.Criatura;

import java.util.Random;

public class Jogo {
    private Jogador jogador1;
    private Jogador jogador2;
    private CampodeBatalha campoDeBatalha;
    private Jogador jogadorAtivo;

    public Jogo(Jogador jogador1, Jogador jogador2) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.campoDeBatalha = new CampodeBatalha(jogador1.getMao(), jogador1.getCemiterio(), jogador1.getDeck());
        this.jogadorAtivo = new Random().nextBoolean() ? jogador1 : jogador2; // Decide aleatoriamente quem começa
    }

    public void iniciar() {
        jogador1.getDeck().embaralhar();
        jogador2.getDeck().embaralhar();


        for (int i = 0; i < 5; i++) {
            jogador1.comprarCartas();
            jogador2.comprarCartas();
        }

        while (true) {
            executarTurno(jogadorAtivo);
            // Verifica condições de vitória
            if (verificarVitoria(jogador1) || verificarVitoria(jogador2)) {
                break; // Termina o jogo se alguém vencer
            }
            // Troca o jogador ativo
            jogadorAtivo = (jogadorAtivo == jogador1) ? jogador2 : jogador1;
        }
    }

    private void executarTurno(Jogador jogador) {
        System.out.println("É a vez de " + jogador.getNome());
        System.out.println("Fase de Compra: ");
        jogador.comprarCartas();

        System.out.println("Fase de mana");
        jogador.reiniciarMana();

        System.out.println("Fase Principal");
        jogarCartas(jogador);


        System.out.println("Fase de Combate");
        combate(jogador);

        // Fase Final
        System.out.println(jogador.getNome() + " terminou seu turno.");


    }

//
    private void jogarCartas(Jogador jogador) {
        System.out.println(jogador.getNome() + ", suas cartas na mão:");
        for (Carta carta : jogador.getMao().getCartas()) {
            System.out.println(carta.getNome() + " - Custo de Mana: " + carta.getCustoMana());
        }

        for (Carta carta : jogador.getMao().getCartas()) {
            if (carta.getCustoMana() <= jogador.getManaAtual()) {
                campoDeBatalha.adicionarCartasAoCampo(carta);
                jogador.usarMana(carta.getCustoMana());
                break;
            }
        }
    }



    private void combate(Jogador jogador) {
        System.out.println(jogador.getNome() + ", declare suas criaturas para atacar:");

        // Supondo que o oponente é o jogador alternativo
        Jogador oponente = (jogador == jogador1) ? jogador2 : jogador1;

        for (Carta carta : campoDeBatalha.getCampo()) {
            if (carta instanceof Criatura) {
                System.out.println(carta.getNome() + " ataca!");
                // Aqui, o oponente deve defender
                oponente.defender(carta);
            }
        }
    }


    private boolean verificarVitoria(Jogador jogador) {
        if (jogador.getVida() <= 0) {
            System.out.println(jogador.getNome() + " foi derrotado!");
            return true;
        }

        return false;
    }

    public void criarAreaJogo(){

    }
}
