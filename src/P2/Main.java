package P2;

import P2.DAO.ReizigerDAO;
import P2.DAO.ReizigerDAOPsql;
import P2.Domein.Reiziger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static Connection connection;

    public static void main(String[] args) throws SQLException {
        ReizigerDAO rdaosql = new ReizigerDAO() {
            @Override
            public boolean save(Reiziger reiziger) {
                return false;
            }

            @Override
            public boolean update(Reiziger reiziger) {
                return false;
            }

            @Override
            public boolean delete(Reiziger reiziger) {
                return false;
            }

            @Override
            public Reiziger findById(int id) {
                return null;
            }

            @Override
            public List<Reiziger> findByGbdatum(Date datum) {
                return null;
            }

            @Override
            public List<Reiziger> findAll() {
                return null;
            }
        };
        ReizigerDAOPsql rdaosql1 = new ReizigerDAOPsql(getConnection());
        testReizigerDAO(rdaosql1);
//        testOVchipDAO(odaopsql);
//        testProductDao(pdaosql1);
        closeConnection();


    }

    private static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost/ovchip?user=postgres&password=password";

        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }


    private static void closeConnection() throws SQLException {
        connection.close();
    }

    private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");

        // Haal alle reizigers op uit de database
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();

        // Maak een nieuwe reiziger aan en persisteer deze in de database
        String gbdatum = "1981-03-14";
        Reiziger sietske = new Reiziger(112, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        rdao.save(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        // Voeg aanvullende tests van de ontbrekende CRUD-operaties in.
    }

}
