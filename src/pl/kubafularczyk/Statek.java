package pl.kubafularczyk;

public class Statek {
    private Gracz wlasciciel;
    private CzescStatku[] czesciStatku;
    private int dlugosc;
    private char znak;
    private boolean zatopiony;

    public Statek(Gracz wlasciciel){
        this.wlasciciel = wlasciciel;
    }

    public CzescStatku[] getCzesciStatku() {
        return czesciStatku;
    }

    public void setCzesciStatku(CzescStatku[] czesciStatku) {
        this.czesciStatku = czesciStatku;
    }

    public int getDlugosc() {
        return dlugosc;
    }

    public void setDlugosc(int dlugosc) {
        this.dlugosc = dlugosc;
    }

    public char getZnak() {
        return znak;
    }

    public void setZnak(char znak) {
        this.znak = znak;
    }

    public boolean isZatopiony() {
        return zatopiony;
    }

    public void setZatopiony(boolean zatopiony) {
        this.zatopiony = zatopiony;
    }

}
