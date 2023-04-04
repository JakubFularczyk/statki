package pl.kubafularczyk;

public class CzescStatku {
    private Statek statek;

    private boolean zatopiona;

    private Pozycja pozycja;

    public CzescStatku(int x, int y, Statek statek) {
        this.pozycja = new Pozycja(x,y);
        this.statek = statek;
    }

    public boolean isZatopiona() {
        return zatopiona;
    }

    public void setZatopiona(boolean zatopiona) {

        this.zatopiona = zatopiona;
    }

    public Pozycja getPozycja() {
        return pozycja;
    }

    public void setPozycja(Pozycja pozycja) {
        this.pozycja = pozycja;
    }
}
