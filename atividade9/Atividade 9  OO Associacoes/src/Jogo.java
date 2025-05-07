import java.util.Random;

public class Jogo {
    private Time time1;
    private Time time2;
    private int golsTime1;
    private int golsTime2;

    public Jogo(Time time1, Time time2) {
        this.time1 = time1;
        this.time2 = time2;
    }

    // Getters
    public Time getTime1() { return time1; }
    public Time getTime2() { return time2; }
    public int getGolsTime1() { return golsTime1; }
    public int getGolsTime2() { return golsTime2; }

    public String getResultado() {
        return time1.getNome() + " " + golsTime1 + " x " + golsTime2 + " " + time2.getNome();
    }

    public void simular() {
        Random random = new Random();
        golsTime1 = random.nextInt(5);
        golsTime2 = random.nextInt(5);
    }
}