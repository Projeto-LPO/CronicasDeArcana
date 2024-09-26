package MecanicasDeJogo;

import MecanicasDeJogo.Abstract.Carta;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class GeradorDeCartas {

    // Método para carregar cartas genéricas de um arquivo JSON
    public static List<Carta> carregarCartas(String fileName) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(fileName)) {
            Type cartaListType = new TypeToken<List<Carta>>() {}.getType();
            return gson.fromJson(reader, cartaListType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void gerarCartas() {
        // Carregar cartas de um arquivo JSON genérico
        List<Carta> cartas = carregarCartas("cartas.json");

        // Exemplo de como usar as cartas carregadas
        if (cartas != null) {
            for (Carta carta : cartas) {
                carta.mostrarDetalhes();
            }
        }
    }
}
