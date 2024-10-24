package MecanicasDeJogo;

import MecanicasDeJogo.Abstract.Carta;
import MecanicasDeJogo.Exceptions.ManaInsuficienteException;
import MecanicasDeJogo.FluxodeCartas.CampodeBatalha;
import Personagens.Criatura;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.util.List;

public class Jogo {
    private Jogador jogador1;
    private Jogador jogador2;
    private CampodeBatalha campoDeBatalha;
    private CampodeBatalha campoJogador1;
    private CampodeBatalha campoJogador2;
    private Jogador jogadorAtivo;
    private Scanner scanner;

    public Jogo(Jogador jogador1, Jogador jogador2, CampodeBatalha campoJogador1, CampodeBatalha campoJogador2) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.campoJogador1 = campoJogador1;
        this.campoJogador2 = campoJogador2;
        this.campoDeBatalha = new CampodeBatalha(jogador1.getMao(), jogador1.getCemiterio(), jogador1.getDeck());
        this.jogadorAtivo = new Random().nextBoolean() ? jogador1 : jogador2; // Decide aleatoriamente quem começa
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        jogador1.getDeck().embaralhar();
        jogador2.getDeck().embaralhar();
        System.out.println("O jogo começou! Boa sorte, " + jogador1.getNome() + " e " + jogador2.getNome() + "!");

        while (true) {
            executarTurno(jogadorAtivo);
            // Verifica condições de vitória
            if(verificarVitoria(jogadorAtivo)){
                System.out.println("Parabens " +jogador1.getNome() + "Vc venceu");
                return ;
            }



            // Troca o jogador ativo
            jogadorAtivo = (jogadorAtivo == jogador1) ? jogador2 : jogador1;
        }
    }

    private void executarTurno(Jogador jogador) throws ManaInsuficienteException {
        System.out.println("É a vez de " + jogador.getNome());
        System.out.println("Fase de Compra: ");
        jogador.comprarCartas();

        System.out.println("Fase de Mana");
        jogador.reiniciarMana();

        System.out.println("Fase Principal");
        jogarCartas(jogador);

        System.out.println("Fase de Combate");
        combate(jogador);

        // Fase Final
        System.out.println(jogador.getNome() + " terminou seu turno.");
        System.out.println("-----------------------------");
    }

    private void jogarCartas(Jogador jogador) {
        System.out.println(jogador.getNome() + ", suas cartas na mão:");
        for (int i = 0; i < jogador.getMao().getCartas().size(); i++) {
            Carta carta = jogador.getMao().getCartas().get(i);
            System.out.println((i + 1) + ": " + carta.getNome() + " - Custo de Mana: " + carta.getCustoMana());
        }

        // Escolha da carta a jogar
        System.out.println("Escolha uma carta para jogar (digite o número da carta) ou 0 para não jogar:");
        int escolha = scanner.nextInt();

        if (escolha > 0 && escolha <= jogador.getMao().getCartas().size()) {
            Carta cartaEscolhida = jogador.getMao().getCartas().get(escolha - 1);
            if (cartaEscolhida.getCustoMana() <= jogador.getManaAtual()) {
                campoDeBatalha.adicionarCartasAoCampo(cartaEscolhida);
                jogador.usarMana(cartaEscolhida.getCustoMana());
                jogador.getMao().removerCartaMao(cartaEscolhida);
                System.out.println(jogador.getNome() + " jogou a carta " + cartaEscolhida.getNome());
            } else {
                System.out.println("Mana insuficiente para jogar essa carta.");
            }
        } else {
            System.out.println("Nenhuma carta foi jogada.");
        }
    }

    private void combate(Jogador atacante) {
        System.out.println(atacante.getNome() + ", declare suas criaturas para atacar:");
        Jogador defensor = (atacante == jogador1) ? jogador2 : jogador1;

        // Listar criaturas no campo do atacante
        List<Criatura> criaturasAtacantes = campoDeBatalha.getCriaturasNoCampo(atacante);
        if (criaturasAtacantes.isEmpty()) {
            System.out.println("Nenhuma criatura no campo para atacar.");
            return;
        }

        // Selecionar criaturas para atacar
        List<Criatura> criaturasEscolhidas = new ArrayList<>();
        for (int i = 0; i < criaturasAtacantes.size(); i++) {
            Criatura criatura = criaturasAtacantes.get(i);
            System.out.println((i + 1) + ": " + criatura.getNome() + " - Poder: " + criatura.getPoder());
        }

        System.out.println("Escolha criaturas para atacar (digite os números separados por vírgula, ou 0 para não atacar):");
        String input = scanner.next();
        if (!input.equals("0")) {
            String[] escolhas = input.split(",");
            for (String escolha : escolhas) {
                int index = Integer.parseInt(escolha.trim()) - 1;
                if (index >= 0 && index < criaturasAtacantes.size()) {
                    criaturasEscolhidas.add(criaturasAtacantes.get(index));
                }
            }
        }

        if (criaturasEscolhidas.isEmpty()) {
            System.out.println("Nenhuma criatura atacou.");
            return;
        }

        // Selecionar criaturas bloqueadoras
        System.out.println(defensor.getNome() + ", escolha suas criaturas para bloquear:");
        List<Criatura> criaturasBloqueadoras = new ArrayList<>();
        List<Criatura> criaturasNoCampoDefensor = campoDeBatalha.getCriaturasNoCampo(defensor);
        for (int i = 0; i < criaturasNoCampoDefensor.size(); i++) {
            Criatura criatura = criaturasNoCampoDefensor.get(i);
            System.out.println((i + 1) + ": " + criatura.getNome() + " - Poder: " + criatura.getPoder());
        }

        System.out.println("Escolha criaturas para bloquear (digite os números separados por vírgula, ou 0 para não bloquear):");
        input = scanner.next();
        if (!input.equals("0")) {
            String[] escolhas = input.split(",");
            for (String escolha : escolhas) {
                int index = Integer.parseInt(escolha.trim()) - 1;
                if (index >= 0 && index < criaturasNoCampoDefensor.size()) {
                    criaturasBloqueadoras.add(criaturasNoCampoDefensor.get(index));
                }
            }
        }

        // Combate: troca de dano entre atacantes e bloqueadores
        for (int i = 0; i < criaturasEscolhidas.size(); i++) {
            Criatura atacanteCriatura = criaturasEscolhidas.get(i);
            if (i < criaturasBloqueadoras.size()) { // Se houver um bloqueador correspondente
                Criatura bloqueadorCriatura = criaturasBloqueadoras.get(i);
                System.out.println(atacanteCriatura.getNome() + " ataca " + bloqueadorCriatura.getNome());
                bloquearCriatura(atacanteCriatura, bloqueadorCriatura, atacante, defensor);

            } else {
                // Se não houver bloqueador, ataque direto ao jogador
                System.out.println(atacanteCriatura.getNome() + " ataca " + defensor.getNome() + " diretamente!");
                atacanteCriatura.atacarJogador(defensor);
            }
        }

        // Aqui você pode querer verificar se o defensor foi derrotado
        if (defensor.getVida() <= 0) {
            System.out.println(defensor.getNome() + " foi derrotado!");
            verificarVitoria(atacante);
        }
    }

    public void bloquearCriatura(Criatura atacante, Criatura bloqueador, Jogador jogadorAtacante, Jogador jogadorDefensor) {
        // Troca de dano entre as criaturas
        bloqueador.receberDano(atacante.getPoder());
        atacante.receberDano(bloqueador.getPoder());

        // Verifica se a criatura bloqueadora foi destruída
        if (bloqueador.getResistencia() <= 0) {
            System.out.println(bloqueador.getNome() + " foi destruído.");
            jogadorDefensor.getCemiterio().adicionarCartasNoCemiterio(bloqueador);
            jogadorDefensor.getCampoDeBatalha().removerCartaDoCampo(bloqueador); // Corrigido aqui
        }

        // Verifica se a criatura atacante foi destruída
        if (atacante.getResistencia() <= 0) {
            System.out.println(atacante.getNome() + " foi destruído.");
            jogadorAtacante.getCemiterio().adicionarCartasNoCemiterio(atacante);
            jogadorAtacante.getCampoDeBatalha().removerCartaDoCampo(atacante); // Corrigido aqui
        }
    }

    private boolean verificarVitoria(Jogador jogador) {
        if (jogador.getVida() <= 0) {
          System.out.println(jogador.getNome() +" Ficou sem vida");
            return true;
        }

        if (jogador.getDeck().isEmpty()) {
            System.out.println(jogador.getNome() + "Ficou sem cartas");
            return true;
        }

        return false;
    }
}