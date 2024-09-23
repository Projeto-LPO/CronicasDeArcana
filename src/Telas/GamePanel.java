package Telas;

import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    private Thread gameThread;
    private boolean running;
    private final int FPS = 60;


    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
    }

    private void update() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

    }

    public void launchGame() {
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        // Loop do jogo
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000.0 / FPS;
        double delta = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;

            while (delta >= 1) {
                update();
                delta--;
            }

            repaint();
        }
    }

    public void stopGame() {
        running = false; // Para o loop do jogo
        try {
            gameThread.join(); // Aguarda a thread terminar
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
