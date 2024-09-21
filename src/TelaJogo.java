import java.awt.*;
import java.util.Scanner;
import javax.swing.*;


public class TelaJogo {
    private JPanel textPanel;
    private JTextArea textArea;

    public void criarTelaJogo(){

        textPanel = new JPanel();
        textPanel.setLayout(null);
        textPanel.setBounds(100, 100, 600, 500);

        textPanel.setBackground(Color.blue);
        textArea = new JTextArea();
        textArea.setBounds(100, 100, 800, 500);
        textArea.setBackground(Color.blue);
        textArea.setForeground(Color.white);
        textArea.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        textArea.setLineWrap(true);
        textPanel.add(textArea);

    }
}
