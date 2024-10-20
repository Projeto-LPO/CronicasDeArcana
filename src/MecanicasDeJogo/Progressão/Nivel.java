package MecanicasDeJogo.Progressão;

public class Nivel {

    private int nivelAtual;

    public Nivel(){
        this.nivelAtual = 1;
    }

    public void ganharNivel(){
        this.nivelAtual++;
        System.out.println("Você subiu de nível! Nível atual: " + this.nivelAtual);
    }

    public int getNivelAtual() {return nivelAtual;}

    public void mostrarNivel(){
        System.out.println("Nível atual: " + this.nivelAtual);
    }

}
