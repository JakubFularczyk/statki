package pl.kubafularczyk;

public class PolozenieStatku {

    private Orientacja orientacja;
    private Pozycja pozycja;
    private int dlugosc;

    enum Orientacja {
        POZIOMA, PIONOWA;

        public static Orientacja parse(String tekst) {
            tekst = tekst.trim();
            for(Orientacja orientacja : Orientacja.values()) {
                if (orientacja.name().equalsIgnoreCase(tekst)) {
                    return orientacja;
                }
            }
            return null;
        }
    }
    public PolozenieStatku(Pozycja pozycja, Orientacja orientacja){
        this.pozycja = pozycja;
        this.orientacja = orientacja;
    }


    public Orientacja getOrientacja() {
        return orientacja;
    }

    public void setOrientacja(Orientacja orientacja) {
        this.orientacja = orientacja;
    }

    public Pozycja getPozycja() {
        return pozycja;
    }

    public void setPozycja(Pozycja pozycja) {
        this.pozycja = pozycja;
    }

    public int getDlugosc() {
        return dlugosc;
    }

    public void setDlugosc(int dlugosc) {
        this.dlugosc = dlugosc;
    }
}
