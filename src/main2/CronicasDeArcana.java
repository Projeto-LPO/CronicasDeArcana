package main2;

import ElementosGraficos.Telas.MenuInicial;

public class CronicasDeArcana {
    public static void main(String[] args) {
        // Inicializando a tela de menu inicial
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Instancia a tela MenuInicial e a torna visível
                MenuInicial menuInicial = new MenuInicial();
                menuInicial.setVisible(true);
            }
        });
    }
}
