package pl.kubafularczyk;

import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GraczZywy extends Gracz {

    // TODO przy wybieraniu polozenia statku to Gra decyduje o tym czy ruch byl okej i steruje pobraniem ponownie
    // jesli cos bylo nie tak
    // w przypadku strzalu to gracz od razu decyduje o ponowieniu (gra sie tym nie interesuje)
    // ^przydaloby sie to ujednolicic

    @Override
    public PolozenieStatku wybierzPolozenie(int rozmiarPlanszy) {
        Optional<Pozycja> pozycja = odczytajPozycje();
        PolozenieStatku.Orientacja orientacja = PolozenieStatku.Orientacja.parse(SCANNER.next());
        SCANNER.nextLine(); // skasowanie znaku nowej linii
        PolozenieStatku polozenieStatku = new PolozenieStatku(pozycja.get(), orientacja);
        return polozenieStatku;
    }

//    @Override
//    public Pozycja podajPozycjeStrzalu(int rozmiarPlanszy) {
//        Pozycja pozycja = odczytajPozycje();
//        if (null != pozycja) {
//            if(pozycja.czyMiesciSieNaPlanszy(rozmiarPlanszy)) {
//                return pozycja;
//            }
//        }
//        System.out.println("Podano złą pozycje");
//        return podajPozycjeStrzalu(rozmiarPlanszy);
//    }

    @Override
    public Optional<Pozycja> podajPozycjeStrzalu(int rozmiarPlanszy) {
        return odczytajPozycje();
    }

    private Optional<Pozycja> odczytajPozycje() {
        String koordynaty = SCANNER.next();
        Pattern p = Pattern.compile("([a-zA-Z])([1-9]\\d*)");
        Matcher m = p.matcher(koordynaty);
        if (m.matches()) {
            String litera = m.group(1).toUpperCase();
            String liczba = m.group(2);
            // A1 -> 0,0
            int pozycjaY = Integer.parseInt(liczba) - 1; // 1 - 1 = 0
            int pozycjaX = litera.charAt(0) - 'A'; // 'A' - 'A' = 0
            Pozycja pozycja = new Pozycja(pozycjaX, pozycjaY);
            return Optional.of(pozycja);
        } else {
            return Optional.empty();
        }
    }
}
