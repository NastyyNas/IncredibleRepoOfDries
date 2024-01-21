package be.pxl.h2.oefeningen.oef2;

public class Bankrekening {
    public String rekeningnummer;
    public String naamEigenaar;
    private double saldo;
    private double rentepercentage;

    public Bankrekening(){
        this("onbekend", "onbekend", 0, 1.2);
    }

    public Bankrekening(String rekeningnummer, String naamEigenaar, double saldo, double rentepercentage){
        this.rekeningnummer = rekeningnummer;
        this.naamEigenaar = naamEigenaar;
        this.saldo = saldo;
        this.rentepercentage = rentepercentage;
    }

    public void setRekeningnummer(String rekeningnummer) {
        this.rekeningnummer = rekeningnummer;
    }

    public void setNaamEigenaar(String naamEigenaar) {
        this.naamEigenaar = naamEigenaar;
    }

    public double getSaldo() {
        return saldo;
    }

    public void stort(double bedrag){
        if (valideerRekening()){
            saldo += bedrag;
        }

    }


    public void neemOp(double bedrag){
        if (valideerRekening()){
            if (bedrag > saldo){
                System.out.printf("u wilt %f van de rekening maar er staat maar %f op, %f word afgenomen", bedrag, saldo, saldo);
                saldo = 0;
            }else if(saldo == 0){
                System.out.println("u mag geen geld opnemen");
            }else{
                System.out.printf("%.2f word afgenomen", bedrag);
                saldo -= bedrag;
            }
        }


    }

    public void doeVerichting(double bedrag){
        if(valideerRekening()){
            if (bedrag < 0){
                neemOp(-bedrag);
            }else {
                stort(bedrag);
            }
        }

    }

    public void schrijfRenteBij(){
        if(valideerRekening()){
            saldo += saldo * rentepercentage;
        }

    }

    private boolean valideerRekening(){
        if (naamEigenaar.equals("onbekend") || rekeningnummer.equals("onbekend")){
            return false;
        }else{
            return true;
        }
    }

    public void print(){
        System.out.printf("Saldo op de spaarrekening %s op naam van %s bedraagt %.2f", rekeningnummer, naamEigenaar, saldo);
    }
}
