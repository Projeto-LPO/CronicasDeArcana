package Soundtrack;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sons {

    //cria um clip
    Clip clip;
    URL soundURL[] = new URL[30]; //cria a identificação das soundtracks

        //instancia das soundtracks no construtor
        public Sons(){
            soundURL[0] = getClass().getResource("/Soundtrack/Musicas/MenuInicialMusica.wav");
            soundURL[1] = getClass().getResource("/Soundtrack/Musicas/BatalhaMusica.wav");
        }

        public void setFile(int i) {
            try {
                AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
                clip = AudioSystem.getClip();
                clip.open(ais);
                System.out.println("Som carregado com sucesso: " + soundURL[i]);
            } catch (Exception e) {
                System.out.println("Erro ao carregar o som: " + soundURL[i]);
                e.printStackTrace();
            }
        }


        public void play() {
            if (clip != null) {
                clip.start();
            } else {
                System.out.println("Erro: O Clip não está inicializado. Certifique-se de chamar setFile() primeiro.");
            }
        }

        public void stop() {
            if (clip != null) {
                clip.stop();
            }
        }

        public void loop() {
            if (clip != null) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        }

    }
