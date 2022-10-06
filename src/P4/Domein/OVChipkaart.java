package P4.Domein;

import P2enP3.Domein.Reiziger;
import P5.Domein.Product;

import java.sql.Date;
import java.util.List;

public class OVChipkaart {
    private Reiziger reiziger;
    private int kaartNummer;
    private Date geldigTot;
    private int klasse;
    private double saldo;
    private List<Product> producten;

    public OVChipkaart(int kaartNummer, Date geldigTot, int klasse, double saldo, Reiziger reiziger) {
        this.kaartNummer = kaartNummer;
        this.geldigTot = geldigTot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger = reiziger;
    }

    public int getKaartNummer() {
        return kaartNummer;
    }

    public void setKaartNummer(int kaartNummer) {
        this.kaartNummer = kaartNummer;
    }

    public Reiziger getReiziger1() {
        return reiziger;
    }

    public void setReiziger1(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    public Date getGeldigTot() {
        return geldigTot;
    }

    public void setGeldigTot(Date geldigTot) {
        this.geldigTot = geldigTot;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    public List<Product> getProducten() {
        return producten;
    }

    public void addProduct(Product product) {
        producten.add(product);
        product.addOvChipkaart(this.kaartNummer);
    }


    @Override
    public String toString() {
        return "OVChipkaart{" +
                ", kaartNummer=" + kaartNummer +
                ", geldigTot=" + geldigTot +
                ", klasse=" + klasse +
                ", saldo=" + saldo +
                "reiziger=" + reiziger +
                '}';
    }
}


