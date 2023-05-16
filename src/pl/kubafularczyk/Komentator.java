package pl.kubafularczyk;

// wypisuje biezace informacje o rozgrywce (kto co zrobil, jaki efekt na planszy itp.)
public class Komentator {

    public void start(Gracz gracz) {
        if (gracz instanceof GraczZywy) {
            System.out.println("Rozpoczyna gracz zywy!");
        } else {
            System.out.println("Rozpoczyna gracz martwy!");
        }
    }

    // trafienie + informacje o stanie obu plansz
    public void skomentujTrafienie(RodzajTrafienia rodzajTrafienia) {

        System.out.println("Uwaga, " + rodzajTrafienia.name() + " trafienie!");
        podsumujRozgrywke();
    }

    private void podsumujRozgrywke() {

    }

    // koncowe podsumowanie
    public void podsumujCalaRozgrywke() {

    }
}
