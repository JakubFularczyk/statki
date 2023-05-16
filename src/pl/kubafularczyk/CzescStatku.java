package pl.kubafularczyk;

public class CzescStatku {
    private Statek statek;
    private Pozycja pozycja;
    private boolean zatopiona;

    public CzescStatku(int x, int y, Statek statek) {
        this.pozycja = new Pozycja(x,y);
        this.statek = statek;
    }

    public Statek getStatek() {
        return statek;
    }

    public boolean isZatopiona() {
        return zatopiona;
    }

    public void zatop() {

        this.zatopiona = true;
    }

    public Pozycja getPozycja() {
        return pozycja;
    }

    public void setPozycja(Pozycja pozycja) {
        this.pozycja = pozycja;
    }

    public void oznaczJakoTrafiona() {
        zatopiona = true;
        statek.zaktualizujStan();
    }
}
