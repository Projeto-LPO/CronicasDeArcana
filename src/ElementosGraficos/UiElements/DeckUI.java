package ElementosGraficos.UiElements;

import MecanicasDeJogo.FluxodeCartas.Decks;
import MecanicasDeJogo.Abstract.Carta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;;

public class DeckUI extends JPanel{
    private Decks deck;
    private DefaultListModel<Carta> modeloLista;
    private  JList<Carta> listaCartas;
    private JButton btnComprar;
    private JButton btnEmbaralhar;

    public DeckUI(Decks deck){
        this.deck = deck;
        this.setLayout(new BorderLayout());

        modeloLista = new DefaultListModel<>();
        listaCartas = new JList<>(modeloLista); // Cria a lista com o modelo
        listaCartas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane();
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel();
        btnComprar = new JButton("Comprar Carta");
        btnEmbaralhar = new JButton("Embaralhar Deck");

        btnComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comprarCarta();

            }
        });

      btnEmbaralhar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            embaralharDeck();
        }
    });
      painelBotoes.add(btnComprar);
      painelBotoes.add(btnEmbaralhar);
      this.add(painelBotoes, BorderLayout.SOUTH);
        atualizarCartas();
    }

      private void comprarCarta(){
          Carta cartaComprada = deck.comprarCarta();
          if(cartaComprada != null){
              atualizarCartas();
          }}
          private void embaralharDeck(){
              deck.embaralhar();
              atualizarCartas();
          }

          public void atualizarCartas() {
              modeloLista.clear(); // Limpa a lista atual
              List<Carta> cartasNoDeck = deck.getCartas(); // Obtém as cartas do deck
              for (Carta carta : cartasNoDeck) {
                  modeloLista.addElement(carta); // Adiciona cada carta ao modelo
              }
          }

        }

