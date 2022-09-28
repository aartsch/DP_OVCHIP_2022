package P2enP3.Domein;




import P4.Domein.OVChipkaart;

import java.sql.Date;
import java.util.List;

public class Reiziger {
    private int id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;
    private Adres adres;
    private List<OVChipkaart> ovChipkaarten;

    public Reiziger(int id, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum) {
        this.id = id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

    public String getVoorletters() {
        return voorletters;
    }
    public Date getGeboortedatum() {
        return geboortedatum;
    }
    public String getTussenvoegsel() {
        return tussenvoegsel;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNaam() {
        return achternaam;
    }
    public void setAdres(Adres adres) {
        this.adres = adres;
    }
    public Adres getAdres() {
        return adres;
    }
    public void addOvChipkaart(OVChipkaart ovChipkaart) {
        ovChipkaarten.add(ovChipkaart);
    }
    public void deleteOvChipkaart(OVChipkaart ovChipkaart) {
        for(OVChipkaart i : ovChipkaarten) {
            if(i == ovChipkaart) {
                ovChipkaarten.remove(i);
            }
        }
    }

    public List<OVChipkaart> getOvChipkaarten() {
        return this.ovChipkaarten;
    }



    public String toString() {
        return "Reiziger{" +
                "id=" + id +
                ", voorletters='" + voorletters + '\'' +
                ", tussenvoegsel='" + tussenvoegsel + '\'' +
                ", achternaam='" + achternaam + '\'' +
                ", geboortedatum=" + geboortedatum +
                " " + adres + " " + ovChipkaarten ;
    }
}
