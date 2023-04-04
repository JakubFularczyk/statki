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

    public void setCzescStatku(CzescStatku czescStatku) {
        this.czescStatku = czescStatku;
    }

    public boolean isTrafione() {
        return trafione;
    }

    public void setTrafione(boolean trafione) {
        this.trafione = trafione;
    }
}
