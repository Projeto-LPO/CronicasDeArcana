package MecanicasDeJogo.FluxodeCartas;

import MecanicasDeJogo.Abstract.Carta;
import Personagens.Arqueiro;
import Personagens.Dragao;
import Personagens.Guerreiro;
import Personagens.Mago;

import java.util.ArrayList;
import java.util.List;

public class InstanciaCartas {

    public static List<Carta> gerarCartas() {
        List<Carta> cartas = new ArrayList<>();

        //cartas guerreiro
        cartas.add(new Guerreiro("Guerreiro Valoroso", 2, 4, 5));
        cartas.add(new Guerreiro("Guerreiro Ferido", 1, 3, 4));
        cartas.add(new Guerreiro("Guerreiro Resoluto", 3, 5, 6));
        cartas.add(new Guerreiro("Guerreiro Selvagem", 2, 4, 4));
        cartas.add(new Guerreiro("Guerreiro do Reino", 2, 5, 5));
        cartas.add(new Guerreiro("Guerreiro da Fronteira", 1, 2, 3));
        cartas.add(new Guerreiro("Guerreiro da Lança", 3, 6, 7));
        cartas.add(new Guerreiro("Guerreiro da Espada", 2, 4, 5));

        //cartas arqueiro
        cartas.add(new Arqueiro("Arqueiro da Floresta", 3, 4, 3));
        cartas.add(new Arqueiro("Arqueiro da Colina", 2, 3, 2));
        cartas.add(new Arqueiro("Arqueiro Silencioso", 3, 4, 4));
        cartas.add(new Arqueiro("Arqueiro Sombrio", 4, 5, 5));
        cartas.add(new Arqueiro("Arqueiro das Sombras", 2, 2, 3));
        cartas.add(new Arqueiro("Arqueiro dos Ventos", 3, 3, 4));
        cartas.add(new Arqueiro("Arqueiro Letal", 4, 5, 5));

        //cartas dragão
        cartas.add(new Dragao("Dragão de Fogo", 5, 7, 8));
        cartas.add(new Dragao("Dragão da Noite", 6, 8, 9));
        cartas.add(new Dragao("Dragão da Montanha", 5, 6, 7));
        cartas.add(new Dragao("Dragão Ancião", 7, 10, 10));
        cartas.add(new Dragao("Dragão Alado", 6, 9, 8));
        cartas.add(new Dragao("Dragão de Gelo", 5, 7, 6));
        cartas.add(new Dragao("Dragão Sombrio", 6, 8, 9));

        //cartas mago
        cartas.add(new Mago("Mago Elementalista", 4, 5, 5));
        cartas.add(new Mago("Mago das Sombras", 5, 6, 6));
        cartas.add(new Mago("Mago Arcano", 4, 5, 4));
        cartas.add(new Mago("Mago Ilusionista", 3, 4, 4));
        cartas.add(new Mago("Mago de Gelo", 4, 5, 5));
        cartas.add(new Mago("Mago do Fogo", 5, 6, 5));
        cartas.add(new Mago("Mago da Terra", 4, 5, 5));
        cartas.add(new Mago("Mago das Chamas", 3, 4, 4));

        return cartas;
    }
}
