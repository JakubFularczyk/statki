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
        plansza.wydrukujPlansze(gracze, rodzaj);
        startRozgrywki();
    }

    private void inicjalizujRozgrywke() {
        komentator = new Komentator();
        stworzGraczy();
        stworzPlansze();
        stworzStatki();
    }

    private void stworzGraczy() {
        gracze = new Gracz[LICZBA_GRACZY];
        komentator.wyborTrybuRozgrywki();
        while(true) {
            try {
                int odpowiedz = scanner.nextInt();
                this.rodzaj = RodzajRozgrywki.parse(odpowiedz);
                break;
            } catch (InputMismatchException e) {
                komentator.wyborTrybuRozgrywki();
            } catch (BlednyRodzajRozgrywkiException e) {
                komentator.dozwoloneNumery();
            } finally {
                scanner.nextLine();
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
        komentator.podajWielkoscPlanszy();
        int wielkoscPlanszy = scanner.nextInt();
        plansza = new Plansza(wielkoscPlanszy);
    }

    private void stworzStatki() {
        komentator.podajLiczbeStatkow();
        int liczbaStatkow = scanner.nextInt();
        int dlugoscStatku = obliczDlugoscStatku(plansza.getRozmiar());

        for(Gracz gracz : gracze) {
            stworzStatki(gracz, liczbaStatkow, dlugoscStatku);
        }
    }

    private void stworzStatki(Gracz gracz, int liczbaStatkow, int dlugosc) {
        Statek[] statki = new Statek[liczbaStatkow];
        boolean graczZywy = gracz instanceof GraczZywy;
        komentator.wyborPolozeniaIOrientacjiStatku();
        for(int i = 0; i < liczbaStatkow;) { // petli for each nie stosuje sie gdy bedzie przypisanie
            PolozenieStatku polozenie = gracz.wybierzPolozenie(plansza.getRozmiar());
            polozenie.setDlugosc(dlugosc);
            if (plansza.czyPolozeniePoprawne(polozenie)) {
                statki[i] = plansza.stworzStatekWPolozeniu(polozenie, gracz);
                i++;
            } else if (graczZywy) {
                komentator.polozenieJestNieprawidlowe();
            }
            if (i < liczbaStatkow && graczZywy) {
                komentator.wyborKolejnejPozycjiStatku();
            }
        }
        gracz.setStatki(statki);
    }

    private int obliczDlugoscStatku(int rozmiar) {
        int dlugoscStatku;
        if (RodzajRozgrywki.GRACZ_AI_VS_GRACZ_AI.equals(rodzaj)) {
            Random random = new Random();
            dlugoscStatku = random.nextInt(rozmiar/2) + 1;
        } else {
            komentator.podajPolozenieStatku();
            dlugoscStatku = scanner.nextInt();
            while(dlugoscStatku > rozmiar/2 || dlugoscStatku <= 0) {
                komentator.statekZbytDuzy(); System.out.println( rozmiar/2 );
                dlugoscStatku = scanner.nextInt();
            }
        }
        return dlugoscStatku;
    }

    private void startRozgrywki() {
        Gracz gracz = losujGraczaRozpoczynajacego();
        komentator.start(gracz);
        while(czyObajGraczeMajaStatki()) {
            Optional<Pozycja> pozycjaOpt = pobierzPozycjeDoStrzalu(gracz);
            if (pozycjaOpt.isEmpty()) {
                komentator.pozycjaNieprawidlowa();
                gracz = zmienGracza(gracz);
                continue;
            }
            Pozycja pozycja = pozycjaOpt.get();
            RodzajTrafienia rodzajTrafienia = strzel(pozycja, gracz);
            plansza.wydrukujPlansze(gracze, rodzaj);
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
                posiadaNiezatopioneStatki[i] |= !statek.isZatopiony();
            }
        }

        return posiadaNiezatopioneStatki[0] && posiadaNiezatopioneStatki[1];
    }

    private Optional<Pozycja> pobierzPozycjeDoStrzalu(Gracz gracz) {
        int licznik = 0;
        if(gracz instanceof GraczZywy) {
            komentator.wyborPolozeniaStatku();
        }
        while(true) {
            Optional<Pozycja> pozycja = gracz.podajPozycjeStrzalu(plansza.getRozmiar());
            if(pozycja.isEmpty() || !pozycja.get().czyMiesciSieNaPlanszy(plansza.getRozmiar())) {
                komentator.polozenieJestNieprawidlowe();
                licznik++;
                if(licznik == 3) {
                    return Optional.empty();
                }
                continue;
            }
            PolePlanszy polePlanszy = plansza.pobierzPolePlanszy(pozycja.get());
            if (null != polePlanszy) {
                CzescStatku czescStatku = polePlanszy.getCzescStatku();
                Statek statek = czescStatku.getStatek();
                if (statek.getWlasciciel() == gracz) {
                    komentator.strzalWSwojStatek();
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
