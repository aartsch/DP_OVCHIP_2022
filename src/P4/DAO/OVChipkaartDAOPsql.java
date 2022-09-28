package P4.DAO;

import P2enP3.DAO.ReizigerDAO;
import P2enP3.Domein.Reiziger;
import P4.Domein.OVChipkaart;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaartDAOPsql implements OVChipkaartDAO {
    private Connection conn;
    private ReizigerDAO rdao;


    public OVChipkaartDAOPsql(Connection conn, ReizigerDAO rdao) {
        this.conn = conn;
        this.rdao = rdao;

    }

    @Override
    public boolean save(OVChipkaart ovChipkaart) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO ov_chipkaart VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, ovChipkaart.getKaartNummer());
            ps.setDate(2, ovChipkaart.getGeldigTot());
            ps.setInt(3, ovChipkaart.getKlasse());
            ps.setDouble(4, ovChipkaart.getSaldo());
            ps.setInt(5, ovChipkaart.getReiziger().getId());
            int execute = ps.executeUpdate();

            if (execute == 1) {
                ps.close();
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(OVChipkaart ovChipkaart) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE ov_chipkaart set geldig_tot=?, klasse=?,saldo=?, reiziger_id=? WHERE kaart_nummer =?");
            ps.setDate(1, ovChipkaart.getGeldigTot());
            ps.setInt(2, ovChipkaart.getKlasse());
            ps.setDouble(3, ovChipkaart.getSaldo());
            ps.setInt(4, ovChipkaart.getReiziger().getId());
            ps.setInt(5, ovChipkaart.getKaartNummer());


            int i = ps.executeUpdate();

            if(i == 1) {
                ps.close();
                return true;
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return false;
    }

    @Override
    public boolean delete(OVChipkaart ovChipkaart) {
        try {
            Statement statement = conn.createStatement();
            int i = statement.executeUpdate("DELETE FROM ovchipkaart WHERE kaart_nummer=" + ovChipkaart.getKaartNummer());


            if(i == 1) {
                statement.close();
                return true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public List<OVChipkaart> findByReiziger(Reiziger reiziger) {
        List<OVChipkaart> ovChipkaarten = new ArrayList<OVChipkaart>();
        try {
            int kaartNummer;
            Date geldigTot;
            int klasse;
            double saldo;

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ov_chipkaart WHERE reiziger_id=?");
            ps.setInt(1, reiziger.getId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                kaartNummer = rs.getInt("kaart_nummer");
                geldigTot = rs.getDate("geldig_tot");
                klasse = rs.getInt("klasse");
                saldo = rs.getDouble("saldo");
                OVChipkaart ovchip1 = new OVChipkaart(kaartNummer, geldigTot, klasse, saldo, reiziger);
                ovChipkaarten.add(ovchip1);
            }
            rs.close();
            ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ovChipkaarten;
    }

    @Override
    public List<OVChipkaart> findAll() {
        List<OVChipkaart> ovChipkaarten = new ArrayList<OVChipkaart>();
        try {
            int kaartNummer;
            Date geldigTot;
            int klasse;
            double saldo;
            int reizigerId;


            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM ov_chipkaart");
            while (rs.next()) {
                kaartNummer = rs.getInt("kaart_nummer");
                geldigTot = rs.getDate("geldig_tot");
                klasse = rs.getInt("klasse");
                saldo = rs.getDouble("saldo");
                reizigerId = rs.getInt("reiziger_id");
                Reiziger reiziger = rdao.findById(reizigerId);
                OVChipkaart ovchip1 = new OVChipkaart(kaartNummer, geldigTot, klasse, saldo, reiziger );
                ovChipkaarten.add(ovchip1);
            }

            rs.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ovChipkaarten;
    }
}
