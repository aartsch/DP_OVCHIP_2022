package P2enP3.DAO;

import P2enP3.Domein.Adres;
import P2enP3.Domein.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdresDAOPsql implements AdresDAO {
    private Connection conn;
    private ReizigerDAO rdao;

    public AdresDAOPsql(Connection conn, ReizigerDAO rdao) {
        this.conn = conn;
        this.rdao = rdao;

    }

    @Override
    public boolean update(Adres adres) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE adres set adres_id=?, postcode=?, huisnummer=?,straat=?, woonplaats=?, reiziger_id=?");
            ps.setString(1, adres.getPostcode());

            int i = ps.executeUpdate();

            if (i == 1) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }



    @Override
    public boolean delete(Adres adres) {
        try {
            Statement statement = conn.createStatement();
            int i = statement.executeUpdate("DELETE FROM adres WHERE adres_id=" + adres.getId());

            if(i == 1) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        try {
            int adresId;
            String postcode;
            String huisnummer;
            String straat;
            String woonplaats;
            int reiziger_id;

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM adres WHERE reiziger_id=?");;
            ps.setInt(1, reiziger.getId());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                adresId = rs.getInt("adres_id");
                postcode = rs.getString("postcode");
                huisnummer = rs.getString("huisnummer");
                straat = rs.getString("straat");
                woonplaats = rs.getString("woonplaats");
                reiziger_id = rs.getInt("reiziger_id");

                Adres adres = new Adres(adresId, postcode, huisnummer, straat, woonplaats, reiziger);
                System.out.println(adresId + "." + postcode + " " +  huisnummer + " " + straat + " " + woonplaats + " van reiziger: " + reiziger_id);
                return adres;
            }
            rs.close();
            ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Adres> findAll() {
        List<Adres> adressen = new ArrayList<Adres>();
        try {
            int adresId;
            String postcode;
            String huisnummer;
            String straat;
            String woonplaats;
            int reiziger_id;

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM adres");
            while (rs.next()) {
                adresId = rs.getInt("adres_id");
                postcode = rs.getString("postcode");
                huisnummer = rs.getString("huisnummer");
                straat = rs.getString("straat");
                woonplaats = rs.getString("woonplaats");
                reiziger_id = rs.getInt("reiziger_id");
                Reiziger reiziger = rdao.findById(reiziger_id);
                Adres adres = new Adres(adresId, postcode, huisnummer, straat, woonplaats, reiziger);
                adressen.add(adres);

            }
            rs.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return adressen;
    }


    @Override
    public boolean save(Adres adres) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO adres VALUES (?, ?, ?, ?, ?, ?)");
            ps.setInt(1, adres.getId());
            ps.setString(   2, adres.getPostcode());
            ps.setString(3, adres.getHuisnummer());
            ps.setString(4, adres.getStraat());
            ps.setString(5, adres.getWoonplaats());
            ps.setInt(6, adres.getReiziger1().getId());

            int execute = ps.executeUpdate();

            if(execute == 1) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}

