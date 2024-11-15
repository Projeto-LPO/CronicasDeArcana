package ElementosGraficos.UiElements;

import Encantamento.Encantamento;
import Feiticos.Feitiço;
import Feiticos.FeitiçoCura;
import Feiticos.FeitiçoDano;
import MecanicasDeJogo.Jogador;
import Personagens.Criatura;
import Encantamento.*;
import java.util.List;


    public class GerenciadorDeCombate {
        public void executarFaseDeCombate(Jogador jogadorAtacante, Jogador jogadorDefensor) {
            List<Criatura> criaturasAtacantes = jogadorAtacante.getCampoDeBatalha().getCriaturasNoCampo(jogadorAtacante);
            List<Criatura> criaturasDefensoras = jogadorDefensor.getCampoDeBatalha().getCriaturasNoCampo(jogadorDefensor);

            for (Criatura atacante : criaturasAtacantes) {
                if (!criaturasDefensoras.isEmpty()) {
                    Criatura alvo = criaturasDefensoras.get(0);
                    atacarCriatura(atacante, alvo);

                    if (alvo.getResistencia() <= 0) {
                        removerCriaturaDoCampo(jogadorDefensor, alvo);
                    }
                } else {
                    atacarJogador(atacante, jogadorDefensor);
                }
            }
        }

        private void atacarCriatura(Criatura atacante, Criatura alvo) {
            atacante.atacar(alvo);
        }

        private void atacarJogador(Criatura atacante, Jogador jogador) {
            atacante.atacarJogador(jogador);
        }

        private void removerCriaturaDoCampo(Jogador jogador, Criatura criatura) {
            jogador.getCampoDeBatalha().removerCartaDoCampo(criatura);
            jogador.getCemiterio().adicionarCartasNoCemiterio(criatura);
        }

        public void aplicarFeitiçoDeCura(FeitiçoCura feitiçoCura, Jogador jogadorAlvo) {
            feitiçoCura.aplicarEfeitoCura(jogadorAlvo);

            for (Criatura criatura : jogadorAlvo.getCampoDeBatalha().getCriaturasNoCampo(jogadorAlvo)) {
                feitiçoCura.aplicarEfeitoCura(criatura);
            }
        }

        public void aplicarFeitiçoDeDano(FeitiçoDano feitiçoDano , Jogador jogadorAlvo){
            feitiçoDano.aplicarEfeitoDano(jogadorAlvo);
            for (Criatura criatura : jogadorAlvo.getCampoDeBatalha().getCriaturasNoCampo(jogadorAlvo)) {
                feitiçoDano.aplicarEfeitoDano(criatura);
            }
        }
        public void adicionarFeitiçonoCemiterio(Jogador jogador , Feitiço feitiço) {
            jogador.getCemiterio().adicionarCartasNoCemiterio(feitiço);
        }

        public void adicionarEnacantamentonoCemiterio(Jogador jogador, Encantamento encantamento){
            jogador.getCemiterio().adicionarCartasNoCemiterio(encantamento);
        }

        public void aplicarEncantamentoDano(Jogador jogadorAlvo, EncantamentoDano encantamentoDano){
            for (Criatura criatura: jogadorAlvo.getCampoDeBatalha().getCriaturasNoCampo(jogadorAlvo)){
                encantamentoDano.aplicarEfeitoDano(criatura);
            }
        }
        public void aplicarEncantementoCura(Jogador jogadorAlvo, Encantamento encantamentoCura ){
            for(Criatura criatura: jogadorAlvo.getCampoDeBatalha().getCriaturasNoCampo(jogadorAlvo)){
                encantamentoCura.aplicarEfeitoCura(criatura);
            }

        }


        public void verificarDuracaoEncantamentos(List<Encantamento> encantamentos, Jogador jogador) {
            for (Encantamento encantamento : encantamentos) {
                encantamento.reduzirDuracao();

                if (encantamento.getDuracao() <= 0) {
                    jogador.getCampoDeBatalha().removerCartaDoCampo(encantamento);
                    jogador.getCemiterio().adicionarCartasNoCemiterio(encantamento);
                    System.out.println("Encantamento " + encantamento.getNome() + " foi movido para o cemitério.");
                }
            }
        }


    }


