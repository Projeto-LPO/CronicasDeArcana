package main2;

import ElementosGraficos.Telas.MenuInicial;

public class CronicasDeArcana {
    public static void main(String[] args) {
        //inicializando a tela de menu inicial e instanciando um novo objeto no metodo run()
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MenuInicial menuInicial = new MenuInicial();
                menuInicial.setVisible(true);
            }
        });
    }
}
