package pl.kubafularczyk;

public class PolePlanszy {

    private CzescStatku czescStatku;
    private boolean trafione;

    public PolePlanszy(CzescStatku czescStatku) {
        this.czescStatku = czescStatku;
    }

    public CzescStatku getCzescStatku() {
        return czescStatku;
    }

    public void oznaczJakoTrafione() {
        trafione = true;
        czescStatku.oznaczJakoTrafiona();
    }
}
