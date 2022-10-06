package P5.Domein;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private int productNummer;
    private String naam;
    private String beschrijving;
    private int prijs;
    private List<Integer>  ovChipkaarten = new ArrayList<>(); // moet int worden (ovchipkaart nummers)

    public Product(int productNummer, String naam, String beschrijving, int prijs, List<Integer> ovChipkaarten) {
        this.productNummer = productNummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
        this.ovChipkaarten = ovChipkaarten;
    }

    public int getProductNummer() {
        return productNummer;
    }

    public void setProductNummer(int productNummer) {
        this.productNummer = productNummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public int getPrijs() {
        return prijs;
    }

    public void setPrijs(int prijs) {
        this.prijs = prijs;
    }

    public void addOvChipkaart(int ovChipkaart) {
        ovChipkaarten.add(ovChipkaart);
    }

    public void deleteOvChipkaart(int ovChipkaart) {
        ovChipkaarten.remove(ovChipkaart);
    }

    public List<Integer> getOvChipkaarten() {
        return ovChipkaarten;
    }


    @Override
    public String toString() {
        return "Product{" +
                "productNummer=" + productNummer +
                ", naam='" + naam + '\'' +
                ", beschrijving='" + beschrijving + '\'' +
                ", prijs=" + prijs +
                ", ovChipkaart=" + ovChipkaarten +
                '}';
    }
}
