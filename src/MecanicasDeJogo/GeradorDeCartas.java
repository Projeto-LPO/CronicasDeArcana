package MecanicasDeJogo;

import Feiticos.Feitiço;
import Feiticos.FeitiçoCura;
import Feiticos.FeitiçoDano;
import Personagens.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GeradorDeCartas {

    public static void gerarCartas() {
        // Instanciar cartas de cada subclasse usando Criatura como superclasse
        List<Criatura> cartasCavaleiro = new ArrayList<>();
        cartasCavaleiro.add(new Guerreiro("Cavaleiro da Luz", 3, 7, 5));
        cartasCavaleiro.add(new Guerreiro("Guardião do Reino", 5, 8, 7));
        cartasCavaleiro.add(new Guerreiro("Campeão da Ordem", 7, 10, 9));

        List<Criatura> cartasArqueiro = new ArrayList<>();
        cartasArqueiro.add(new Arqueiro("Arqueiro da Floresta", 2, 5, 3));
        cartasArqueiro.add(new Arqueiro("Caçador Silencioso", 4, 6, 4));
        cartasArqueiro.add(new Arqueiro("Mestre do Arco", 6, 8, 5));

        List<Criatura> cartasMago = new ArrayList<>();
        cartasMago.add(new Mago("Feiticeiro do Fogo", 5, 4, 6));
        cartasMago.add(new Mago("Conjurador das Chamas", 7, 5, 8));
        cartasMago.add(new Mago("Mago Supremo", 9, 6, 10));

        List<Criatura> cartasDragao = new ArrayList<>();
        cartasDragao.add(new Dragao("Dragão de Fogo", 6, 9, 8));
        cartasDragao.add(new Dragao("Dragão Ancião", 8, 12, 10));
        cartasDragao.add(new Dragao("Dragão Celestial", 10, 14, 12));

        List<Encantamento> cartasEncantamento = new ArrayList<>();


        List<FeitiçoCura> cartasCura = new ArrayList<>();
        cartasCura.add(new FeitiçoCura("Revigor",4, "curar uma carta", 2));
        cartasCura.add(new FeitiçoCura("Tônico Máximo", 6, "curar efetivamente uma carta", 4));

        List<FeitiçoDano> cartasDano = new ArrayList<>();
        cartasDano.add(new FeitiçoDano("Bola de fogo",4,"dano instanâneo", 4));
        cartasDano.add(new FeitiçoDano("Saraivada", 3, "dano instantâneo", 3));


        // Serializar cada lista para JSON
        salvarComoJson("cartas_cavaleiro.json", cartasCavaleiro);
        salvarComoJson("cartas_arqueiro.json", cartasArqueiro);
        salvarComoJson("cartas_mago.json", cartasMago);
        salvarComoJson("cartas_dragao.json", cartasDragao);
    }

    // Função para salvar uma lista de cartas em formato JSON
    public static void salvarComoJson(String fileName, List<Criatura> cartas) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(cartas, writer);
            System.out.println("Cartas salvas no arquivo " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
