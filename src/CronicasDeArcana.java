import java.util.ArrayList;
import java.util.List;

public class CronicasDeArcana {
    public static void main(String[] args) {
        // Criando o deck do Jogador 1
        Decks deckJogador1 = new Decks();
        deckJogador1.adicionarCarta(new Criatura("Golem", 8, 5, 9, "Provocar"));
        deckJogador1.adicionarCarta(new Criatura("Mago", 5, 4, 3, "Dano em área"));
        deckJogador1.adicionarCarta(new Criatura("Arqueiro", 3, 2, 2, "Ataque à distância"));
        deckJogador1.adicionarCarta(new Criatura("Cavaleiro", 3, 3, 4, ""));
        deckJogador1.adicionarCarta(new Criatura("P.E.K.K.A", 7, 8, 6, ""));
        deckJogador1.adicionarCarta(new Criatura("Gárgula", 4, 2, 3, "Voador"));
        deckJogador1.adicionarCarta(new Criatura("Dragão Infernal", 6, 4, 5, "Dano contínuo"));
        deckJogador1.adicionarCarta(new Criatura("Gigante", 6, 6, 8, "Tanque"));
        deckJogador1.adicionarCarta(new Criatura("Mosqueteira", 4, 3, 3, "Ataque à distância"));
        deckJogador1.adicionarCarta(new Criatura("Príncipe", 5, 5, 4, "Investida"));

        // Feitiços
        deckJogador1.adicionarCarta(new Feitiço("Bola de Fogo", 4, "Causa 6 de dano em área", 6));
        deckJogador1.adicionarCarta(new Feitiço("Flechas", 3, "Causa 3 de dano em todas as criaturas inimigas", 3));
        deckJogador1.adicionarCarta(new Feitiço("Cura Divina", 3, "Cura 5 de vida", 5, true));
        deckJogador1.adicionarCarta(new Feitiço("Raio", 6, "Causa 8 de dano em uma criatura", 8));

        // Encantamentos
        deckJogador1.adicionarCarta(new Encantamento("Rugido de Batalha", 5, "Dá +2 de poder a todas as criaturas no campo"));
        deckJogador1.adicionarCarta(new Encantamento("Barreira de Proteção", 4, "Dá +3 de resistência a uma criatura"));
        deckJogador1.adicionarCarta(new Encantamento("Aura de Mana", 4, "Ganha 1 de mana adicional por turno"));

        for (int i = 0; i < 13; i++) {
            deckJogador1.adicionarCarta(new Criatura("Guerreiro Genérico", 2, 1, 2, ""));
        }


        Decks deckJogador2 = new Decks();
        deckJogador2.adicionarCarta(new Criatura("Dragão Elétrico", 7, 5, 6, "Ataque de área elétrico"));
        deckJogador2.adicionarCarta(new Criatura("Esqueleto Gigante", 6, 4, 7, "Deixa bomba ao morrer"));
        deckJogador2.adicionarCarta(new Criatura("Mini P.E.K.K.A", 4, 4, 3, "Dano alto"));
        deckJogador2.adicionarCarta(new Criatura("Gigante Real", 6, 5, 7, "Ataque de longa distância"));
        deckJogador2.adicionarCarta(new Criatura("Bárbaro de Elite", 5, 4, 4, "Investida rápida"));
        deckJogador2.adicionarCarta(new Criatura("Dragão Bebê", 4, 3, 4, "Dano aéreo"));
        deckJogador2.adicionarCarta(new Criatura("Servos", 3, 2, 2, "Voador"));
        deckJogador2.adicionarCarta(new Criatura("Lava Hound", 8, 7, 9, "Invoca criaturas ao morrer"));

        // Feitiços
        deckJogador2.adicionarCarta(new Feitiço("Zap", 2, "Causa 2 de dano e atordoa", 2));
        deckJogador2.adicionarCarta(new Feitiço("Relâmpago", 6, "Causa 6 de dano em 3 criaturas", 6));
        deckJogador2.adicionarCarta(new Feitiço("Bola de Fogo", 4, "Causa 5 de dano em área", 5));
        deckJogador2.adicionarCarta(new Feitiço("Cura Celestial", 3, "Cura 6 de vida", 6, true));

        // Encantamentos
        deckJogador2.adicionarCarta(new Encantamento("Escudo Protetor", 3, "Dá +2 de resistência a todas as criaturas no campo"));
        deckJogador2.adicionarCarta(new Encantamento("Campo de Mana", 4, "Ganha 1 de mana extra ao usar feitiços"));

        // Repetindo cartas para completar o deck de 30 cartas
        for (int i = 0; i < 12; i++) {
            deckJogador2.adicionarCarta(new Criatura("Soldado Comum", 2, 1, 2, ""));
        }

        // Criando os jogadores

        Jogador jogador1 = new Jogador("Jogador 1", deckJogador1, 20, 10);
        Jogador jogador2 = new Jogador("Jogador 2", deckJogador2, 20, 10);
        // Iniciando o jogo

        Jogo jogo = new Jogo(jogador1, jogador2);
        jogo.iniciar();
    }
}