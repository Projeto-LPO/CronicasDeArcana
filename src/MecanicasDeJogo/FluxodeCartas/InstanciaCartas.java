package MecanicasDeJogo.FluxodeCartas;

import MecanicasDeJogo.Abstract.Carta;
import Personagens.Arqueiro;
import Personagens.Dragao;
import Personagens.Guerreiro;
import Personagens.Mago;
import Encantamento.*;
import Feiticos.*;

import java.util.ArrayList;
import java.util.List;

public class InstanciaCartas {

    public static List<Carta> gerarCartas() {
        List<Carta> cartas = new ArrayList<>();

        // Cartas de Guerreiros
        cartas.add(new Guerreiro("Cavaleiro", 3, 5, 6));
        cartas.add(new Guerreiro("Mini P.E.K.K.A", 4, 6, 7));
        cartas.add(new Guerreiro("Valquíria", 3, 4, 5));
        cartas.add(new Guerreiro("Golem de Elixir", 2, 3, 8));
        cartas.add(new Guerreiro("Rei Bárbaro", 5, 8, 9));
        cartas.add(new Guerreiro("Gigante", 5, 7, 10));
        cartas.add(new Guerreiro("P.E.K.K.A", 6, 9, 12));
        cartas.add(new Guerreiro("Príncipe", 4, 7, 8));

        // Cartas de Arqueiros
        cartas.add(new Arqueiro("Arqueiras", 2, 4, 3));
        cartas.add(new Arqueiro("Musketeer", 4, 5, 5));
        cartas.add(new Arqueiro("Rainha Arqueira", 5, 7, 6));
        cartas.add(new Arqueiro("Goblin com Dardo", 3, 4, 2));
        cartas.add(new Arqueiro("Caçador", 3, 5, 5));
        cartas.add(new Arqueiro("Goblin de Lança", 1, 2, 2));
        cartas.add(new Arqueiro("Atirador Fantasma", 4, 6, 5));

        // Cartas de Dragões
        cartas.add(new Dragao("Dragão Bebê", 4, 6, 5));
        cartas.add(new Dragao("Dragão Infernal", 5, 7, 8));
        cartas.add(new Dragao("Dragão Elétrico", 5, 8, 6));
        cartas.add(new Dragao("Dragão Ancião", 6, 10, 9));
        cartas.add(new Dragao("Dragão das Sombras", 5, 9, 7));
        cartas.add(new Dragao("Dragão de Gelo", 4, 7, 6));
        cartas.add(new Dragao("Dragão de Fogo", 5, 8, 8));

        // Cartas de Magos
        cartas.add(new Mago("Mago de Fogo", 4, 5, 5));
        cartas.add(new Mago("Mago Elétrico", 5, 6, 6));
        cartas.add(new Mago("Mago de Gelo", 4, 6, 5));
        cartas.add(new Mago("Mago das Sombras", 5, 7, 6));
        cartas.add(new Mago("Feiticeiro", 3, 4, 4));
        cartas.add(new Mago("Mago da Terra", 3, 5, 5));
        cartas.add(new Mago("Mago das Chamas", 4, 6, 5));
        cartas.add(new Mago("Mago Ilusionista", 2, 4, 4));

        // Feitiços de Dano
        cartas.add(new FeitiçoDano("Bola de Fogo", 4, "Dano", 5));
        cartas.add(new FeitiçoDano("Relâmpago", 5, "Muito Dano!", 10));
        cartas.add(new FeitiçoDano("Flechas", 3, "Dano", 3));
        cartas.add(new FeitiçoDano("Tornado", 4, "Dano", 5));
        cartas.add(new FeitiçoDano("Zap", 2, "Dano", 2));

        // Feitiços de Cura
        cartas.add(new FeitiçoCura("Espírito Curador", 2, "Cura", 3));
        cartas.add(new FeitiçoCura("Poção de Cura", 3, "Cura", 4));
        cartas.add(new FeitiçoCura("Bênção Divina", 5, "Muita Cura", 6));
        cartas.add(new FeitiçoCura("Restauração", 4, "Muita Cura", 5));
        cartas.add(new FeitiçoCura("Rejuvenescimento", 3, "Cura", 4));


        return cartas;
    }
}
