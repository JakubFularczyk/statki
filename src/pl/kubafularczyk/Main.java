package pl.kubafularczyk;

public class Main {

    /* Nie musi byc po kolei (ale moze)
    1.Stworzenie planszy do gry
    2.Stworzenie obiektu statkow i umieszczanie ich na planszy
    3.Ustanowienie funkcji strzelania oraz naprzemiennosci gry gracza zywego i graczaAI
    4.Ustawienie przeciwnika do gry przeciwko nam tzn; rozmieszczenie jego statkow, funkcja strzalu, co ma robic po pudle, co po trafieniu
    5.
     */

    // 1.wspolrzedne x,y planszy
    // 2.ilosc czesci statku
    // 3.boolean z rodzajem trafienia pudlo - false trafienie - true
    // 4.ilosc statkow pozostajacych w grze
    // 5.enum z zaznaczeniem czyja tura aktualnie jest
    // Projekt z enkapsulacja - co sie da robimy jako !private! - pomówić o enkapsulacji ogółem + gettery/settery
    public static void main(String[] args) {
        Gra gra = new Gra();
        gra.uruchom();
    }

}
