package pl.kubafularczyk;

import java.util.*;

public class Gra {

    private final int LICZBA_GRACZY = 2;
    private Gracz[] gracze;
    private Plansza plansza;
    private Komentator komentator;
    private final Scanner SCANNER = new Scanner(System.in);

    public void uruchom() {
        inicjalizujRozgrywke();
        plansza.wydrukuj2(gracze);
        startRozgrywki();
    }

    private void inicjalizujRozgrywke() {
        stworzGraczy();
        stworzPlansze();
        stworzStatki();
        komentator = new Komentator();
    }

    private boolean sprawdzCzyToLiczba(String tekst) {
        for(char znak : tekst.toCharArray()) {
            if (!(znak >= '0' && znak <= '9')) {
                return false;
            }
        }
        return true;

        /*
        String liczba = tekst;
        while(liczba.length() != 0) {
            String cyfra = liczba.substring(0,1);
            liczba = liczba.substring(1);
            if (!(cyfra.charAt(0) >= '0' && cyfra.charAt(0) <= '9')) {
                return false;
            }
        }

        return true;
        */
    }

    private void stworzGraczy() {
        gracze = new Gracz[LICZBA_GRACZY];
        RodzajRozgrywki rodzaj;
        System.out.println("Gracz Zywy vs Gracz AI wpisz = 1\nGracz Zywy vs Gracz Zywy wpisz = 2\nGracz AI vs Gracz AI wpisz = 3");
        while(true) {
            try {
                int odpowiedz = SCANNER.nextInt();
                rodzaj = RodzajRozgrywki.parse(odpowiedz);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Gracz Zywy vs Gracz AI wpisz = 1\nGracz Zywy vs Gracz Zywy wpisz = 2\nGracz AI vs Gracz AI wpisz = 3");
            } catch (BlednyRodzajRozgrywkiException e) {
                System.out.println("Dozwolone sa tylko numery od 1 do 3");
            } finally {
                SCANNER.nextLine(); // pobranie znaku nowej linii
            }
        }

        if(RodzajRozgrywki.GRACZ_ZYWY_VS_GRACZ_AI.equals(rodzaj)) {
            gracze[0] = new GraczZywy();
            gracze[1] = new GraczAI();
        } else if (RodzajRozgrywki.GRACZ_ZYWY_VS_GRACZ_ZYWY.equals(rodzaj)) {
            gracze[0] = new GraczZywy();
            gracze[1] = new GraczZywy();
        } else if (RodzajRozgrywki.GRACZ_AI_VS_GRACZ_AI.equals(rodzaj)) {
            gracze[0] = new GraczAI();
            gracze[1] = new GraczAI();
        }
    }

    private void stworzPlansze() {
        System.out.println("Podaj wielkosc planszy");
        int wielkoscPlanszy = SCANNER.nextInt();
        plansza = new Plansza(wielkoscPlanszy);
    }

    /*
    Statki skladaja sie z czesci statkow, czesci statkow sa zapisywane na polach planszy
    Osobno chcemy miec liste na posiadane przez graczy statki (lub ogolnie statki)
     */
    private void stworzStatki() {
        System.out.println("Podaj liczbe statkow");
        int liczbaStatkow = SCANNER.nextInt();

        for(Gracz gracz : gracze) {
            stworzStatki(gracz, liczbaStatkow);
        }
    }

    private void stworzStatki(Gracz gracz, int liczbaStatkow) {
        // stworzenie tablicy
        Statek[] statki = new Statek[liczbaStatkow];
        for(int i = 0; i < liczbaStatkow; ) { // petli for each nie stosuje sie gdy bedzie przypisanie
            PolozenieStatku polozenie = gracz.wybierzPolozenie(plansza.getRozmiar());
            int dlugosc = obliczMaksymalnaDlugoscStatku();
            polozenie.setDlugosc(dlugosc);
            if (plansza.czyPolozeniePoprawne(polozenie)) {
                statki[i] = plansza.stworzStatekWPolozeniu(polozenie, gracz);
                i++;
            } else {
                if (gracz instanceof GraczZywy) {
                    System.out.println("Polozenie jest nieprawidlowe");
                }
            }
        }
        gracz.setStatki(statki);

        // losowanie lub wybor pozycji i orientacji <- po stronie gracza

        // sprawdzenie czy pozycja jest ok

        // jak jest ok to przejscie

        // jak nie jest ok to powtorzenie z jakims komunikatem
    }

    public static int obliczMaksymalnaDlugoscStatku() {
        // TODO zmienic dlugosc statku na zmienna ktora wpisuje gracz lub losuje (gra moze prosic o podanie statkow o konkretnej dlugosci)
        int dlugoscStatku = 4;
        return dlugoscStatku;
    }

    private void startRozgrywki() {
        Gracz gracz = losujGraczaRozpoczynajacego();
        while(czyObajGraczeMajaStatki()) {
            Pozycja pozycja = pobierzPozycjeDoStrzalu(gracz);
            RodzajTrafienia rodzajTrafienia = strzel(pozycja);
            gracz.addStrzal(pozycja);
            plansza.wydrukuj2(gracze);
            komentator.skomentujTrafienie();
            if (RodzajTrafienia.NIECELNE.equals(rodzajTrafienia)) {
                gracz = zmienGracza(gracz);
            }
        }
        komentator.podsumujCalaRozgrywke();
    }

    private Gracz losujGraczaRozpoczynajacego() {
        Random random = new Random();
        int numer = random.nextInt(LICZBA_GRACZY);
        return gracze[numer];
    }

    private boolean czyObajGraczeMajaStatki() {

        return false;
    }

    private Pozycja pobierzPozycjeDoStrzalu(Gracz gracz) {

        return null;
    }

    private RodzajTrafienia strzel(Pozycja pozycja) {

        return RodzajTrafienia.NIECELNE;
    }


    private Gracz zmienGracza(Gracz gracz) {
        /* gracze[gracz] - poprosze gracza o numerze grazyna */
        // mojKubek == ... - sprawdzenie czy to dokladnie ten sam kubek ktory mialem
        // mojKubek.equals(...) - sprawdzenie czy to taki sam kubek (niekoniecznie ten co mialem, ale fizycznie wygladajacy tak samo)

        // == - tez mogloby byc okej, bo prawdopodobnie nie bedziemy tworzyc nowych graczy (w innym momencie niz na poczatku)
        return gracze[0].equals(gracz) ? gracze[1] : gracze[0];
    }
}
