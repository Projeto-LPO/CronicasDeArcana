package Telas;

import javax.swing.*;
import MecanicasDeJogo.FluxodeCartas.CampodeBatalha;
import MecanicasDeJogo.Abstract.Carta;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CampodeBatalhaUI extends JPanel {
    private  CampodeBatalha campodeBatalha;
    private DefaultListModel<Carta> modeloLista;
    private JList<Carta> listaCartas;
    public  CampodeBatalhaUI(CampodeBatalha campodeBatalha){
        this.campodeBatalha = campodeBatalha;
        this.setLayout(new BorderLayout());

        modeloLista = new DefaultListModel<>();
        listaCartas = new JList<>(modeloLista);
        listaCartas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listaCartas);
        this.add(scrollPane, BorderLayout.CENTER);
        atualizarCartas();

    }
    public void atualizarCartas(){
        modeloLista.clear();
        List<Carta> cartasnoCampo = campodeBatalha.getCampo();
        for(Carta carta : cartasnoCampo){
            modeloLista.addElement(carta);
        }
    }
}
