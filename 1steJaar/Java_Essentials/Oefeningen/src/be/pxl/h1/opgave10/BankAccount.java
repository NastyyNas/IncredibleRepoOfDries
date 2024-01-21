package be.pxl.h1.opgave10;

public class BankAccount {
    private String naam;
    private String code;
    private int controleCijfers;
    private long nummer;

    public String getNaam() {
        return naam;
    }

    public String getCode() {
        return code;
    }

    public int getControleCijfers() {
        return controleCijfers;
    }

    public long getNummer() {
        return nummer;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setControleCijfers(int controleCijfers) {
        this.controleCijfers = controleCijfers;
    }

    public void setNummer(long nummer) {
        this.nummer = nummer;
    }

    public String getAccount(){
        return code + controleCijfers + nummer;
    }

    public boolean isGeldig(){
        long hulp = nummer * 1000000 + 111400;
        int rest = (int)(hulp % 97);
        int controle = 98 - rest;
        return controle == controleCijfers;
    }
}
