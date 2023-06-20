package pl.kubafularczyk;
public class Komentator {

    public static final String WYBOR_POLOZENIA_STATKU = "Podaj pozycje statku w formacie litera liczba np. A1, D7";
    public static final String WYBOR_ORIENTACJI_STATKU = "Wybierz orientacje statku (pionowa/pozioma)";
    public static final String WYBOR_KOLEJNEJ_POZYCJI = "Podaj kolejna pozycje statku";
    public static final String POLOZENIE_JEST_NIEPRAWIDLOWE = "Polozenie jest nieprawidlowe";
    public static final String PODAJ_DLUGOSC_STATKU = "Podaj dlugosc statku nie wieksza niz polowa dlugosci planszy";
    public static final String POZYCJA_NIEPRAWIDLOWA = "Pozycja nieprawidlowa, zmiana gracza";
    public static final String STRZAL_W_SWOJ_STATEK = "Nie mozesz strzelic w swoj statek";
    public static final String STATEK_ZBYT_DUZY = "Statki zbyt duze, musisz podac rozmiar z przedzialu 1 - ";
    public static final String PODAJ_LICZBE_STATKOW = "Podaj liczbe statkow";
    public static final String PODAJ_WIELKOSC_PLANSZY = "Podaj wielkosc planszy";
    public static final String WYBOR_TRYBU_ROZGRYWKI = "Gracz Zywy vs Gracz AI wpisz = 1\nGracz Zywy vs Gracz Zywy wpisz = 2\nGracz AI vs Gracz AI wpisz = 3";
    public static final String DOZWOLONE_NUMERY = "Dozwolone sa tylko numery od 1 do 3";

    public void start(Gracz gracz) {
        if (gracz instanceof GraczZywy) {
            System.out.println("Rozpoczyna gracz zywy!");
        } else {
            System.out.println("Rozpoczyna gracz martwy!");
        }
    }
    public void skomentujTrafienie(RodzajTrafienia rodzajTrafienia) {

        System.out.println("Uwaga, " + rodzajTrafienia.name() + " trafienie!");
        podsumujRozgrywke();
    }
    public void wyborPolozeniaIOrientacjiStatku() {
        skomentuj(WYBOR_POLOZENIA_STATKU, WYBOR_ORIENTACJI_STATKU);
    }
    public void wyborPolozeniaStatku() {
        skomentuj(WYBOR_POLOZENIA_STATKU);
    }
    public void wyborKolejnejPozycjiStatku() {
        skomentuj(WYBOR_KOLEJNEJ_POZYCJI);
    }
    public void polozenieJestNieprawidlowe() {
        skomentuj(POLOZENIE_JEST_NIEPRAWIDLOWE);
    }
    public void podajPolozenieStatku() {
        skomentuj(PODAJ_DLUGOSC_STATKU);
    }
    public void pozycjaNieprawidlowa() {
        skomentuj(POZYCJA_NIEPRAWIDLOWA);
    }
    public void strzalWSwojStatek() {
        skomentuj(STRZAL_W_SWOJ_STATEK);
    }
    public void statekZbytDuzy() {
        skomentuj(STATEK_ZBYT_DUZY);
    }
    public void podajLiczbeStatkow() {
        skomentuj(PODAJ_LICZBE_STATKOW);
    }
    public void podajWielkoscPlanszy() {
        skomentuj(PODAJ_WIELKOSC_PLANSZY);
    }
    public void wyborTrybuRozgrywki() {
        skomentuj(WYBOR_TRYBU_ROZGRYWKI);
    }
    public void dozwoloneNumery() {
        skomentuj(DOZWOLONE_NUMERY);
    }
    public void podsumujCalaRozgrywke() {

    }
    private void podsumujRozgrywke() {

    }
    private void skomentuj(String ... komentarze) {
        for (String komentarz : komentarze) {
            System.out.println(komentarz);
        }
    }
}
