package Telas;

import java.awt.*;
import javax.swing.*;

public class TelaJogo extends JPanel {

    private JTextArea textArea;

    public TelaJogo() {
        // Define o layout como BorderLayout para ocupar todo o espaço
        setLayout(new BorderLayout());

        // Inicializa e configura a área de texto
        textArea = new JTextArea("Cronicas de Arcana");
        textArea.setBackground(Color.DARK_GRAY);
        textArea.setForeground(Color.WHITE);
        textArea.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true); // Quebra linhas em palavras

        // Adiciona a área de texto dentro de um JScrollPane para rolagem
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Adiciona o JScrollPane ao painel principal
        add(scrollPane, BorderLayout.CENTER);
    }
}
