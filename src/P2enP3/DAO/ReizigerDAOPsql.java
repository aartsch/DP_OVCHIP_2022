package P2enP3.DAO;

import P2enP3.Domein.Adres;
import P2enP3.Domein.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO {
    private Connection conn;
    private AdresDAO adao;

    public ReizigerDAOPsql(Connection conn, AdresDAO adao) {
        this.conn = conn;
        this.adao = adao;
    }
    @Override
    public List<Reiziger> findAll() {
        List<Reiziger> reizigers = new ArrayList<Reiziger>();
        try {
            int reizigerId;
            String voorletters;
            String tussenvoegsel;
            String achternaam;
            Date geboortedatum;


            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM reiziger");
            while (rs.next()) {
                reizigerId = rs.getInt("reiziger_id");
                voorletters = rs.getString("voorletters");
                tussenvoegsel = rs.getString("tussenvoegsel");
                achternaam = rs.getString("achternaam");
                geboortedatum = rs.getDate("geboortedatum");
                System.out.println(reizigerId + "." + voorletters + " " + tussenvoegsel + " " + achternaam + " " + geboortedatum);
                Reiziger reiziger1 = new Reiziger(reizigerId, voorletters , tussenvoegsel , achternaam , geboortedatum);
                Adres a1 = adao.findByReiziger(reiziger1);
                reiziger1.setAdres(a1);
                reizigers.add(reiziger1);
            }
            rs.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return reizigers;
    }

    @Override
    public List<Reiziger> findByGbdatum(Date datum) {
        List<Reiziger> reizigers = new ArrayList<Reiziger>();
        try {
            int reizigerId;
            String voorletters;
            String tussenvoegsel;
            String achternaam;
            Date geboortedatum;

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM reiziger WHERE geboortedatum=?");
            ps.setDate(1, datum);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                reizigerId = rs.getInt("reiziger_id");
                voorletters = rs.getString("voorletters");
                tussenvoegsel = rs.getString("tussenvoegsel");
                achternaam = rs.getString("achternaam");
                geboortedatum = rs.getDate("geboortedatum");
                System.out.println(reizigerId + "." + voorletters + " " + tussenvoegsel + " " + achternaam + " " + geboortedatum);
                Reiziger reiziger1 = new Reiziger(reizigerId, voorletters , tussenvoegsel , achternaam , geboortedatum);
                Adres a1 = adao.findByReiziger(reiziger1);
                reiziger1.setAdres(a1);
                reizigers.add(reiziger1);
            }
            rs.close();
            ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return reizigers;

    }

        @Override
    public Reiziger findById(int id) {
            try {
                int reizigerId;
                String voorletters;
                String tussenvoegsel;
                String achternaam;
                Date geboortedatum;

                PreparedStatement ps = conn.prepareStatement("SELECT * FROM reiziger WHERE reiziger_id=?");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    reizigerId = rs.getInt("reiziger_id");
                    voorletters = rs.getString("voorletters");
                    tussenvoegsel = rs.getString("tussenvoegsel");
                    achternaam = rs.getString("achternaam");
                    geboortedatum = rs.getDate("geboortedatum");
                    System.out.println(reizigerId + "." + voorletters + " " + tussenvoegsel + " " + achternaam + " " + geboortedatum);
                    Reiziger reiziger1 = new Reiziger(reizigerId, voorletters , tussenvoegsel , achternaam , geboortedatum);
                    Adres a1 = adao.findByReiziger(reiziger1);
                    reiziger1.setAdres(a1);

                    return reiziger1;
                }

                rs.close();
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return null ;

        }

    @Override
    public boolean delete(Reiziger reiziger) {
        try {
            Statement statement = conn.createStatement();
            int i = statement.executeUpdate("DELETE FROM reiziger WHERE reiziger_id=" + reiziger.getId());

            if(i == 1) {
                return true;
            }
            Adres adres = reiziger.getAdres();
            adao.delete(adres);
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Reiziger reiziger) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE reiziger set reiziger_id=?, voorletters=?, tussenvoegsel=?,achternaam=?, geboortedatum=?");
            ps.setInt(1, reiziger.getId());

            int i = ps.executeUpdate();

            if(i == 1) {
                return true;
            }
            Adres adres = reiziger.getAdres();
            adao.update(adres);

            ps.close();

    } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }

        @Override
    public boolean save(Reiziger reiziger) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO reiziger VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, reiziger.getId());
            ps.setString(2, reiziger.getVoorletters());
            ps.setString(3, reiziger.getTussenvoegsel());
            ps.setString(4, reiziger.getNaam());
            ps.setDate(5, reiziger.getGeboortedatum());
            int execute = ps.executeUpdate();
            Adres adres = reiziger.getAdres();
            adao.save(adres);

            if(execute == 1) {
                return true;
            }
            ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
