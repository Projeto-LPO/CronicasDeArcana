package MecanicasDeJogo.FluxodeCartas;

import MecanicasDeJogo.Abstract.Carta;
import Personagens.Criatura;
import Encantamento.Encantamento;


import java.util.ArrayList;
import  java.util.List;
public class Cemiterio {
    private List<Carta> cartasNoCemiterio;

    public Cemiterio(){
        this.cartasNoCemiterio = new ArrayList<>();
    }

    public void adicionarCartasNoCemiterio(Carta carta){
        cartasNoCemiterio.add(carta);

    }

    public  List<Carta> getCartasNoCemiterio(){
        return cartasNoCemiterio;
    }
}
