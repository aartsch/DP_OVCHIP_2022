package P2enP3;


import P2enP3.DAO.AdresDAO;
import P2enP3.DAO.AdresDAOPsql;
import P2enP3.DAO.ReizigerDAO;
import P2enP3.DAO.ReizigerDAOPsql;
import P2enP3.Domein.Adres;
import P2enP3.Domein.Reiziger;
import P4.DAO.OVChipkaartDAO;
import P4.DAO.OVChipkaartDAOPsql;
import P4.Domein.OVChipkaart;
import P5.DAO.ProductDAO;
import P5.DAO.ProductDAOPsql;
import P5.Domein.Product;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
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
        AdresDAO adaosql = new AdresDAO() {
            @Override
            public boolean save(Adres adres) {
                return false;
            }

            @Override
            public boolean update(Adres adres) {
                return false;
            }

            @Override
            public boolean delete(Adres adres) {
                return false;
            }

            @Override
            public Adres findByReiziger(Reiziger reiziger) {
                return null;
            }

            @Override
            public List<Adres> findAll() {
                return null;
            }
        };
        OVChipkaartDAO odaosql = new OVChipkaartDAO() {
            @Override
            public boolean save(OVChipkaart ovChipkaart) {
                return false;
            }

            @Override
            public boolean update(OVChipkaart ovChipkaart) {
                return false;
            }

            @Override
            public boolean delete(OVChipkaart ovChipkaart) {
                return false;
            }

            @Override
            public List<OVChipkaart> findByReiziger(Reiziger reiziger) {
                return null;
            }

            @Override
            public List<OVChipkaart> findAll() {
                return null;
            }
        };
        ProductDAO pdao = new ProductDAO() {

            @Override
            public boolean save(Product product) throws SQLException {
                return false;
            }

            @Override
            public boolean update(Product product) throws SQLException {
                return false;
            }

            @Override
            public boolean delete(Product product) throws SQLException {
                return false;
            }

            @Override
            public List<Product> findByOvchipkaart(OVChipkaart ovChipkaart) {
                return null;
            }

            @Override
            public List<Product> findAll() {
                return null;
            }
        };
        AdresDAOPsql apsql1 = new AdresDAOPsql(getConnection(), rdaosql);
        ReizigerDAOPsql rdaosql1 = new ReizigerDAOPsql(getConnection(), adaosql, odaosql);
        ProductDAOPsql pdaosql1 = new ProductDAOPsql(getConnection());
        OVChipkaartDAOPsql odaopsql = new OVChipkaartDAOPsql(getConnection(), rdaosql1, pdaosql1);
        testReizigerDAO(rdaosql1);
        testAdresDAO(apsql1);
        testOVchipDAO(odaopsql);
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

    private static void testOVchipDAO(OVChipkaartDAO odao) throws SQLException {
        System.out.println("\n---------- Test ovchipDAO -------------");

//         Haal alle reizigers op uit de database
        List<OVChipkaart> ovChipkaarts = odao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (OVChipkaart r : ovChipkaarts) {
            System.out.println(r);
        }
        System.out.println();

        String gbdatum = "1981-03-14";
        Reiziger sietske = new Reiziger(122, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
        OVChipkaart ovchip = new OVChipkaart(100, java.sql.Date.valueOf("2022-01-01"), 1, 27.00, sietske);
        System.out.print("[Test] Eerst " + ovChipkaarts.size() + " ovchip, na ovDAO.save() ");
        odao.save(ovchip);
        ovChipkaarts = odao.findAll();
        System.out.println(ovChipkaarts.size() + " ovchip\n");

        System.out.println("[Test] OVCHIP.findbyReiziger() geeft de volgende reizigers:");
        List<OVChipkaart> ovChipkaarten = odao.findByReiziger(sietske);
        for (OVChipkaart r : ovChipkaarten) {
            System.out.println(r);
        }
        System.out.println();

        odao.update(ovchip);
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
        Reiziger sietske = new Reiziger(122, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        rdao.save(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        // Voeg aanvullende tests van de ontbrekende CRUD-operaties in.
    }

    private static void testAdresDAO(AdresDAO adao) throws SQLException {
        System.out.println("\n---------- Test AdresDAO -------------");

        // Haal alle reizigers op uit de database
        List<Adres> adres = adao.findAll();
        System.out.println("[Test] AdresDAO.findAll() geeft de volgende reizigers:");
        for (Adres a : adres) {
            System.out.println(a);
        }
        System.out.println();

        String gbdatum = "1981-03-14";
        Reiziger sietske = new Reiziger(122, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
        Adres adres2 = new Adres(20, "3405CM", "363", "zuidzijde", "Benschop", sietske);
        System.out.print("[Test] Eerst " + adres.size() + " adressen, na AdresDAO.save() ");
        adao.save(adres2);
        adres = adao.findAll();
        System.out.println(adres.size() + " adressen\n");

        System.out.println("\n---------- Test AdresDAO -------------");

        // Haal alle reizigers op uit de database
        Adres adres4 = adao.findByReiziger(sietske);
        System.out.println("[Test] AdresDAO.findBYid() geeft de volgende reizigers:");
        System.out.println(adres4);
        System.out.print("[Test] Eerst " + adres.size() + " adressen, na AdresDAO.delete() ");
        adao.delete(adres4);
        adres = adao.findAll();
        System.out.println(adres.size() + " adressen\n");
        System.out.println();
    }

}
