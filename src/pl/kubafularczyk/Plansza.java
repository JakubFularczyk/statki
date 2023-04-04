package pl.kubafularczyk;

public class Plansza {

    private PolePlanszy[][] polaPlanszy;
    private int rozmiar;

    public Plansza(int rozmiar){
        this.polaPlanszy = new PolePlanszy[rozmiar][rozmiar];
        this.rozmiar = rozmiar;
    }

    // TODO widac ograniczenie w drukowaniu samych pol planszy - do przemyslenia
    // TODO dlaczego statki sa niepelne?
    // statki najezdzaja na oznaczenia ABCD, 1234,
    // tablica u góry sie rozjezdza w miare zwiekszajacej sie ilosci oznaczen ABC itd

    public void wydrukujProsciej() {
        // wydrukowac dwa kwadraty kolo siebie rozmiar 10
        // jeden kwadrrat z kropek, drugi z gwiazdek

        // 0 - 19
        // 10 -> 0 (10%10 = 0)
        // 0 -> 0 (0%10 = 0)
        // 0-9 0-9

        int rozmiar = 10;
        for(int i = 0; i < rozmiar; i++){
            for(int j = 0; j < rozmiar * 2; j++){
                if(j == rozmiar) {
                    System.out.print("\t");
                }
                if(j < rozmiar) {
                    System.out.print(". ");
                } else {
                    System.out.print("* ");
                }

            }
            System.out.println();
        }
    }

    public void wydrukuj2(Gracz[] gracze) {
        String[][][] plansze = new String[2][rozmiar + 1][rozmiar + 1];
        for(int i = 0; i < plansze.length; i++) {
            for (int j = 0; j < rozmiar + 1; j++) {
                for (int k = 0; k < rozmiar + 1; k++) {
                    plansze[i][j][k] = "."; // wstepne uzupelnienie

                    if(k == 0 && j == 0){
                        plansze[i][j][k] = leftPadSpace("\\") + "";
                    } else if(k == 0) {
                        // 10 -> "10 "
                        // 9 ->  " 9 ";
                        plansze[i][j][k] = leftPadSpace(j) + "";
                    } else if(j == 0) {
                        plansze[i][j][k] = (char)('A' + k - 1) + "";
                    }
                }
            }
        }

        for (int i = 0; i < gracze.length; i++) {
            Gracz gracz = gracze[i];
            String oznaczenie = gracz instanceof GraczZywy ? "Z" : "A";
            for(Statek statek : gracz.getStatki()) {
                for(CzescStatku czescStatku : statek.getCzesciStatku()) {
                    Pozycja pozycja = czescStatku.getPozycja();
                    plansze[i][pozycja.getY()+1][pozycja.getX()+1] = oznaczenie;
                }
            }
            for(Pozycja pozycjaStrzalu : gracz.getStrzaly()) {
                plansze[i][pozycjaStrzalu.getY()][pozycjaStrzalu.getX()] = "X";
            }
        }


        for(int i = 0; i < rozmiar + 1; i++){
            int k = 0; // numer gracza/planszy
            for(int j = 0; j <= rozmiar + 1 && k < 2; j++){
                if(j == rozmiar + 1) {
                    System.out.print("\t" + " ");
                    k++;
                    j = -1;
                    continue;
                }
                System.out.print(plansze[k][i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    private String leftPadSpace(String s) {
        while (s.length() != 2) {
            s = " " + s;
        }
        return s;
    }

    private String leftPadSpace(int number) {
        return leftPadSpace(number + "");
    }


        public void wydrukuj(Gracz[] gracze) {
        for(int i = 0; i < rozmiar; i++){
            int k = 0;
            for(int j = 0; j <= rozmiar && k < 2; j++){
                if(j == rozmiar) {
                    System.out.print("\t");
                    k++;
                    j = -1;
                    continue;
                }
                if(k == 0) {
                    // statki gracza 0 i trafienia gracza 1 (pudla i celne)
                    // jesli na polu planszy jest statek gracza 0 == S, jesli jest strzal gracza 1 == X, else == .
                    if(polaPlanszy[i][j]   != null){
                        //if(gracze[0].getStatki())
                        System.out.print("S ");
                    } else{
                        System.out.print(". ");
                    }
                } else {
                    // trafienia gracza 0 (pudla i celne)
                    // jesli na polu planszy jest statek gracza 1 == S, jesli jest strzal gracza 0 == X, else == .
                    if(polaPlanszy[i][j] == null) {
                        System.out.print(". ");
                    } else if (!polaPlanszy[i][j].isTrafione()) {
                        // tymczasowo
                        System.out.print("E ");
                    } else if(polaPlanszy[i][j].isTrafione()){
                        System.out.print("X ");
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    public PolePlanszy[][] getPolaPlanszy() {
        return polaPlanszy;
    }

    public int getRozmiar() {
        return rozmiar;
    }

    // TODO do weryfikacji w pozniejszym etapie

    public boolean czyPolozeniePoprawne(PolozenieStatku polozenie) {
        Pozycja pozycja = polozenie.getPozycja();
        int x = pozycja.getX();
        int y = pozycja.getY();
        int dlugosc = polozenie.getDlugosc();
        PolozenieStatku.Orientacja orientacja = polozenie.getOrientacja();
        if(!(x >= 0 && x < rozmiar)){
            return false;
        }
        if(!(y >= 0 && y < rozmiar)){
            return false;
        }
        if (orientacja == null) {
            return false;
        }
        if(PolozenieStatku.Orientacja.PIONOWA.equals(orientacja)){
            if(y + dlugosc >= rozmiar){
                return false;
            }
        }
        if(PolozenieStatku.Orientacja.POZIOMA.equals(orientacja)) {
            if (x + dlugosc >= rozmiar) {
                return false;
            }
        }

        for(int i = 0; i < dlugosc; i++){
            if(PolozenieStatku.Orientacja.PIONOWA.equals(orientacja)){
                if(polaPlanszy[y + i][x] != null){
                    return false;
                }
            }
            if(PolozenieStatku.Orientacja.POZIOMA.equals(orientacja)) {
                if(polaPlanszy[y][x + i] != null){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean czyPolozeniePoprawneV2(PolozenieStatku polozenie) {
        Pozycja pozycja = polozenie.getPozycja();
        int x = pozycja.getX();
        int y = pozycja.getY();
        int dlugosc = polozenie.getDlugosc();
        PolozenieStatku.Orientacja orientacja = polozenie.getOrientacja();

        try {
            for (int i = 0; i < dlugosc; i++) {
                if (PolozenieStatku.Orientacja.PIONOWA.equals(orientacja)) {
                    if (polaPlanszy[y + i][x] != null) {
                        return false;
                    }
                }
                if (PolozenieStatku.Orientacja.POZIOMA.equals(orientacja)) {
                    if (polaPlanszy[y][x + i] != null) {
                        return false;
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    public Statek stworzStatekWPolozeniu(PolozenieStatku polozenie, Gracz gracz) {
        Pozycja pozycja = polozenie.getPozycja();
        int x = pozycja.getX();
        int y = pozycja.getY();
        int dlugosc = polozenie.getDlugosc();
        PolozenieStatku.Orientacja orientacja = polozenie.getOrientacja();

        // jak stworzyc statek, jak stworzyc czesci statku, jak przypisac czesci statku do statku, jak dodac czesci statku do pol planszy
        Statek statek = new Statek(gracz);
        statek.setDlugosc(dlugosc);
        CzescStatku[] czesciStatku = new CzescStatku[dlugosc];
        for (int i = 0; i < dlugosc; i++) {
            if (PolozenieStatku.Orientacja.PIONOWA.equals(orientacja)) {
                czesciStatku[i] = new CzescStatku(x, y + i, statek);
                polaPlanszy[y + i][x] = new PolePlanszy(czesciStatku[i]);
            }
            if (PolozenieStatku.Orientacja.POZIOMA.equals(orientacja)) {
                czesciStatku[i] = new CzescStatku(x + i, y, statek);
                polaPlanszy[y][x + i] = new PolePlanszy(czesciStatku[i]);
            }
        }
        statek.setCzesciStatku(czesciStatku);
        statek.setZnak('O');

        return statek;
    }



}
