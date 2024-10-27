package ElementosGraficos.UiElements;

import javax.swing.*;
import MecanicasDeJogo.FluxodeCartas.Mao;
import MecanicasDeJogo.Abstract.Carta;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MaoUI extends JPanel {
private Mao mao;
private DefaultListModel<Carta> modeloLista;
private JList<Carta> listaCartas;
private JButton btnJogar;


    public MaoUI(Mao mao) {
        this.mao = mao; // Recebe a instância de Mao
        this.setLayout(new BorderLayout()); // Layout principal

        // Modelo da lista para exibir as cartas
        modeloLista = new DefaultListModel<>();
        listaCartas = new JList<>(modeloLista); // Cria a lista com o modelo
        listaCartas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Permite selecionar apenas uma carta

        // Criação do painel de rolagem para a lista de cartas
        JScrollPane scrollPane = new JScrollPane(listaCartas);
        this.add(scrollPane, BorderLayout.CENTER); // Adiciona ao painel principal

        // Botão para jogar a carta selecionada
        btnJogar = new JButton("Jogar Carta");
        btnJogar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jogarCarta(); // Chama o método para jogar a carta
            }
        });
        this.add(btnJogar, BorderLayout.SOUTH); // Adiciona o botão ao painel
    }
    public void atualizarCartas(){
        modeloLista.clear();
        for(Carta carta : mao.getCartas()){
            modeloLista.addElement(carta);
        }
    }

    private void jogarCarta() {
        Carta cartaSelecionada = listaCartas.getSelectedValue(); // Obtém a carta selecionada
        if (cartaSelecionada != null) {
            mao.removerCartaMao(cartaSelecionada); // Remove a carta da mão
            atualizarCartas(); // Atualiza a lista de cartas
            System.out.println(cartaSelecionada.getNome() + " foi jogada."); // Exibe uma mensagem
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma carta para jogar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

}
