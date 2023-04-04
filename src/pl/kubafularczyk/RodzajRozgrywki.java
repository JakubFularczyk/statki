package pl.kubafularczyk;

// TODO inna nazwa?
public enum RodzajRozgrywki {
    GRACZ_ZYWY_VS_GRACZ_AI(1),
    GRACZ_ZYWY_VS_GRACZ_ZYWY(2),
    GRACZ_AI_VS_GRACZ_AI(3);

    private int numerOpcji;

    RodzajRozgrywki(int numerOpcji) {
        this.numerOpcji = numerOpcji;
    }

    public static RodzajRozgrywki parse(int numerOpcji) {
        for(RodzajRozgrywki rodzaj : RodzajRozgrywki.values()) {
            if (rodzaj.getNumerOpcji() == numerOpcji) {
                return rodzaj;
            }
        }
        throw new BlednyRodzajRozgrywkiException("Bledny numer opcji");
    }

    public int getNumerOpcji() {
        return numerOpcji;
    }
}
