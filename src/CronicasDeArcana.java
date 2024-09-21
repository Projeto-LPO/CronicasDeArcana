import javax.swing.*;
import java.awt.*;

public class CronicasDeArcana {
    public static void main(String[] args) {
        JFrame janela = new JFrame("Crônicas de Arcana");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setResizable(false);
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
        janela.setContentPane(new TelaInicial());  // Define a TelaInicial como o painel inicial
        janela.pack();
    }
}
