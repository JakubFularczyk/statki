package pl.kubafularczyk;

import java.util.*;

public class Gra {

    public static final int LICZBA_GRACZY = 2;
    private final Scanner scanner = new Scanner(System.in);
    private Gracz[] gracze;
    private Plansza plansza;
    private Komentator komentator;
    private RodzajRozgrywki rodzaj;


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
        System.out.println("Gracz Zywy vs Gracz AI wpisz = 1\nGracz Zywy vs Gracz Zywy wpisz = 2\nGracz AI vs Gracz AI wpisz = 3");
        while(true) {
            try {
                int odpowiedz = scanner.nextInt();
                this.rodzaj = RodzajRozgrywki.parse(odpowiedz);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Gracz Zywy vs Gracz AI wpisz = 1\nGracz Zywy vs Gracz Zywy wpisz = 2\nGracz AI vs Gracz AI wpisz = 3");
            } catch (BlednyRodzajRozgrywkiException e) {
                System.out.println("Dozwolone sa tylko numery od 1 do 3");
            } finally {
                scanner.nextLine(); // pobranie znaku nowej linii
            }
        }

        if(RodzajRozgrywki.GRACZ_ZYWY_VS_GRACZ_AI.equals(this.rodzaj)) {
            gracze[0] = new GraczZywy();
            gracze[1] = new GraczAI();
        } else if (RodzajRozgrywki.GRACZ_ZYWY_VS_GRACZ_ZYWY.equals(this.rodzaj)) {
            gracze[0] = new GraczZywy();
            gracze[1] = new GraczZywy();
        } else if (RodzajRozgrywki.GRACZ_AI_VS_GRACZ_AI.equals(this.rodzaj)) {
            gracze[0] = new GraczAI();
            gracze[1] = new GraczAI();
        }
    }

    private void stworzPlansze() {
        System.out.println("Podaj wielkosc planszy");
        int wielkoscPlanszy = scanner.nextInt();
        plansza = new Plansza(wielkoscPlanszy);
    }

    /*
    Statki skladaja sie z czesci statkow, czesci statkow sa zapisywane na polach planszy
    Osobno chcemy miec liste na posiadane przez graczy statki (lub ogolnie statki)
     */
    private void stworzStatki() {
        System.out.println("Podaj liczbe statkow");
        int liczbaStatkow = scanner.nextInt();
        int dlugoscStatku = obliczDlugoscStatku(plansza.getRozmiar());

        for(Gracz gracz : gracze) {
            stworzStatki(gracz, liczbaStatkow, dlugoscStatku);
        }
    }


    private void stworzStatki(Gracz gracz, int liczbaStatkow, int dlugosc) {
        // stworzenie tablicy
        Statek[] statki = new Statek[liczbaStatkow];

        for(int i = 0; i < liczbaStatkow;) { // petli for each nie stosuje sie gdy bedzie przypisanie
            PolozenieStatku polozenie = gracz.wybierzPolozenie(plansza.getRozmiar());
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

    private int obliczDlugoscStatku(int rozmiar) {
        int dlugoscStatku;
        if (RodzajRozgrywki.GRACZ_AI_VS_GRACZ_AI.equals(rodzaj)) {
            Random random = new Random();
            dlugoscStatku = random.nextInt(rozmiar/2) + 1;
        } else {
            System.out.println("Podaj dlugosc statku nie wieksza niz polowa dlugosci planszy");
            dlugoscStatku = scanner.nextInt();
            while(dlugoscStatku > rozmiar/2 || dlugoscStatku <= 0) {
                System.out.println("Statki zbyt duze, musisz podac rozmiar z przedzialu 1-" + rozmiar/2 );
                dlugoscStatku = scanner.nextInt();
            }
        }
        return dlugoscStatku;
    }

    private void startRozgrywki() {
        Gracz gracz = losujGraczaRozpoczynajacego();
        komentator.start(gracz);
        while(czyObajGraczeMajaStatki()) {
            Pozycja pozycja = pobierzPozycjeDoStrzalu(gracz);
            RodzajTrafienia rodzajTrafienia = strzel(pozycja, gracz);
            plansza.wydrukuj2(gracze);
            komentator.skomentujTrafienie(rodzajTrafienia);
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

        boolean posiadaNiezatopioneStatki[] = new boolean[2];

        for(int i = 0; i < 2; i++) {
            for (Statek statek : gracze[i].getStatki()) {
                posiadaNiezatopioneStatki[i] |= !statek.isZatopiony(); // tab[i] = tab[i] || statek.isZatopiony()
            }
        }

        return posiadaNiezatopioneStatki[0] && posiadaNiezatopioneStatki[1];
    }

    private Pozycja pobierzPozycjeDoStrzalu(Gracz gracz) {
        while(true) {
            System.out.println("Podaj pozycje do strzalu w formacie litera liczba np. A1, D7");
            Pozycja pozycja = gracz.podajPozycjeStrzalu(plansza.getRozmiar());
            PolePlanszy polePlanszy = plansza.pobierzPolePlanszy(pozycja);
            if (null != polePlanszy) {
                CzescStatku czescStatku = polePlanszy.getCzescStatku();
                Statek statek = czescStatku.getStatek();
                if (statek.getWlasciciel() == gracz) {
                    System.out.println("Nie mozesz strzelic w swoj statek!");
                    continue;

                }
            }
            return pozycja;
        }
    }

    private RodzajTrafienia strzel(Pozycja pozycja, Gracz gracz) {
        PolePlanszy polePlanszy = plansza.pobierzPolePlanszy(pozycja);
        gracz.addStrzal(pozycja);
        if(polePlanszy != null) {
            polePlanszy.oznaczJakoTrafione();
            return RodzajTrafienia.CELNE;
        }
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
