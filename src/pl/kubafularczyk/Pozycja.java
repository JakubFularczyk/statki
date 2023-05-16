package pl.kubafularczyk;

public class Pozycja {
    private int x;
    private int y;
    // TODO zrob funkcje ktora bedzie sprawdzac pozycje (czy miesci sie na planszy, rozmiar jako argument)

    public Pozycja(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean czyMiesciSieNaPlanszy(int rozmiar) {
        return this.x >= 0 && this.x < rozmiar && this.y >= 0 && this.y < rozmiar;
    }
}
