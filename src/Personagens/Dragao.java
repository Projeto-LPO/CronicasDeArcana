package Personagens;

import java.util.List; // Para trabalhar com listas de criaturas
import MecanicasDeJogo.Jogador; // Certifique-se de importar a classe Jogador corretamente

public class Dragao extends Criatura {

    public Dragao(String nome, int custoMana, int poder, int resistencia) {
        super(nome, custoMana, poder, resistencia, "Voar", true);  // A habilidade especial é "Voar"
    }

    @Override
    public void jogar() {
        // Mensagem customizada ao jogar a carta Dragão
        System.out.println(getNome() + " entrou no campo de batalha com a habilidade especial: " + getHabilidadeEspecial());
    }

    @Override
    public void receberDano(int dano) {
        super.receberDano(dano);
    }

    // Método de ataque ajustado
    public void atacar(List<Criatura> criaturas, Jogador jogadorAlvo) {
        boolean atacouCriaturaAerea = false;

        // Verifica se há uma criatura aérea para atacar
        for (Criatura criatura : criaturas) {
            if (criatura.isVoa()) {
                System.out.println(getNome() + " ataca " + criatura.getNome() + " no ar, causando " + getPoder() + " de dano.");
                criatura.receberDano(getPoder());
                atacouCriaturaAerea = true;
                break; // Ataca apenas uma criatura aérea por vez
            }
        }

        // Se não houver criaturas aéreas, ataca o jogador diretamente
        if (!atacouCriaturaAerea) {
            System.out.println(getNome() + " ataca diretamente o jogador " + jogadorAlvo.getNome() + ", causando " + getPoder() + " de dano.");
            jogadorAlvo.receberDano(getPoder());
        }
    }
}
