package P5.DAO;

import P4.Domein.OVChipkaart;
import P5.Domein.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {
    public boolean save(Product product) throws SQLException;

    public boolean update(Product product) throws SQLException;

    public boolean delete(Product product) throws SQLException;

    public List<Product> findByOvchipkaart(OVChipkaart ovChipkaart);

    public List<Product> findAll();
}