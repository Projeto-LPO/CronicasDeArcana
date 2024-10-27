package ElementosGraficos.UiElements;

import javax.swing.*;
import MecanicasDeJogo.FluxodeCartas.Cemiterio;
import MecanicasDeJogo.Abstract.Carta;

import java.awt.*;

public class CemiterioUI extends JPanel {
    private Cemiterio cemiterio;
    private DefaultListModel<Carta> modeloLista;
    private JList<Carta> listaCartas;

    public CemiterioUI(Cemiterio cemiterio){
        this.cemiterio = cemiterio;
        this.setLayout(new BorderLayout());

        modeloLista = new DefaultListModel<>();
        listaCartas = new JList<>(modeloLista);
        listaCartas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane();
        this.add(scrollPane,BorderLayout.CENTER);

        atualizarCartas();

    }
    public void atualizarCartas(){
        modeloLista.clear();
        for(Carta carta: cemiterio.getCartasNoCemiterio()){
            modeloLista.addElement(carta);
        }
    }
}
