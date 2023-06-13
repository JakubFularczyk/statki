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

    public void setDlugosc(int dlugosc) {
        this.dlugosc = dlugosc;
    }

    public void setZnak(char znak) {
        this.znak = znak;
    }

    public Gracz getWlasciciel() {
        return wlasciciel;
    }

    public boolean isZatopiony() {
        return zatopiony;
    }

    public void zaktualizujStan() {
        zatopiony = true;
        for(CzescStatku czescStatku : czesciStatku) {
            if(!czescStatku.isZatopiona()) {
                zatopiony = false;
                return;
            }
        }
    }
}
