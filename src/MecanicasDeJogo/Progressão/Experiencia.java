package MecanicasDeJogo.Progressão;

public class Experiencia {

    private int xp;

    public Experiencia(int xp){
        this.xp = xp;
    }


    public int getXp() {
        return xp;
    }

    public int incrementarXP(){
        xp++;
        return  xp;
    }
}
