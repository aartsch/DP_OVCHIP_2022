package P5.DAO;

import P4.DAO.OVChipkaartDAO;
import P4.Domein.OVChipkaart;
import P5.Domein.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductDAOPsql implements ProductDAO {
    private Connection conn;
    private OVChipkaartDAO odao;

    public ProductDAOPsql(Connection conn) {
        this.conn = conn;

    }

    @Override
    public boolean save(Product product) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO product VALUES (?, ?, ?, ?)");
            ps.setInt(1, product.getProductNummer());
            ps.setString(2, product.getNaam());
            ps.setString(3, product.getBeschrijving());
            ps.setInt(4, product.getPrijs());
            ps.executeUpdate();
            ps.close();

            for(Integer ovchip : product.getOvChipkaarten()) {
                if (ovchip != null) {
                    PreparedStatement ps1 = conn.prepareStatement("INSERT INTO ov_chipkaart_product values (?,?)");
                    ps1.setInt(1,ovchip);
                    ps1.setInt(2, product.getProductNummer());
                    ps1.executeUpdate();
                    ps1.close();
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Product product) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE product SET product_nummer = ?, naam = ?, beschrijving = ?, prijs = ? WHERE product_nummer = ? ");
            ps.setInt(1, product.getProductNummer());
            ps.setString(2, product.getNaam());
            ps.setString(3, product.getBeschrijving());
            ps.setInt(4, product.getPrijs());
            ps.setInt(5, product.getProductNummer());
            ps.executeUpdate();
            ps.close();

            for(Integer ovchip : product.getOvChipkaarten()) {
                if (ovchip != null) {
                    PreparedStatement ps1 = conn.prepareStatement("UPDATE ov_chipkaart_product SET  kaart_nummer = ?, product_nummer = ? WHERE product_nummer = ? ");

                    ps1.setInt(1,ovchip);
                    ps1.setInt(2, product.getProductNummer());
                    ps1.setInt(3, product.getProductNummer());
                    ps1.executeUpdate();
                    ps1.close();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Product product) {
        try {
            for(Integer ovchip : product.getOvChipkaarten()) {
                if (ovchip != null) {
                    PreparedStatement ps1 = conn.prepareStatement("DELETE FROM ov_chipkaart_product WHERE product_nummer= ?");
                    ps1.setInt(1,product.getProductNummer());
                    ps1.executeUpdate();
                    ps1.close();
                }
            }

            PreparedStatement ps =conn.prepareStatement("DELETE FROM product WHERE product_nummer= ?");
            ps.setInt(1, product.getProductNummer());
            ps.executeUpdate();
            ps.close();



        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
       return true;
    }

    @Override
    public List<Product> findByOvchipkaart(OVChipkaart ovChipkaart) {
        List<Product> producten = new ArrayList<Product>();
        try {
            int productNummer;
            String naam;
            String beschrijving;
            int prijs;
            int ovchip;


            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM product p join ov_chipkaart_product ovp on (ovp.kaart_nummer = ovp.kaart_nummer) WHERE kaart_nummer =" + ovChipkaart.getKaartNummer());
            while (rs.next()) {
                productNummer = rs.getInt("product_nummer");
                naam = rs.getString("naam");
                beschrijving = rs.getString("beschrijving");
                prijs = rs.getInt("prijs");
                ovchip = rs.getInt("kaart_nummer");

                int ovchipkaart = ovChipkaart.getKaartNummer();

                Product product = new Product(productNummer, naam, beschrijving, prijs, Collections.singletonList(ovchipkaart));
                producten.add(product);
            }
            rs.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return producten;
    }

    @Override
    public List<Product> findAll() {
        List<Product> producten = new ArrayList<Product>();
        try {
            int productNummer;
            String naam;
            String beschrijving;
            int prijs;
            
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM product p join ov_chipkaart_product ovp on (p.product_nummer = ovp.product_nummer)");
            while (rs.next()) {
                productNummer = rs.getInt("product_nummer");
                naam = rs.getString("naam");
                beschrijving = rs.getString("beschrijving");
                prijs = rs.getInt("prijs");
                int ovchip = rs.getInt("kaart_nummer");

                List<Integer> ovchipkaarten = Collections.singletonList(ovchip);

                Product product = new Product(productNummer, naam, beschrijving, prijs, ovchipkaarten);
                producten.add(product);

            }
            rs.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return producten;
    }
}
