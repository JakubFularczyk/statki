package pl.kubafularczyk;

import java.util.Scanner;

public class GraczZywy extends Gracz {

    @Override
    public PolozenieStatku wybierzPolozenie(int rozmiarPlanszy) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj X oraz Y na ktorym ma zostac umiejscowiony kawalek statku"); // czy bedziemy wrzucac kawalki statku na plansze czy od razu caly statek?
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        scanner.nextLine();
        Pozycja pozycja = new Pozycja(x,y);
        System.out.println("Wybierz orientacje statku (pionowa/pozioma)");
        PolozenieStatku.Orientacja orientacja = PolozenieStatku.Orientacja.parse(scanner.nextLine());
        PolozenieStatku polozenieStatku = new PolozenieStatku(pozycja, orientacja);
        return polozenieStatku;
    }

}
