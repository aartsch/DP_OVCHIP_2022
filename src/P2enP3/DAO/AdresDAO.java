package P2enP3.DAO;

import P2enP3.Domein.Adres;
import P2enP3.Domein.Reiziger;

import java.util.List;

public interface AdresDAO {
    boolean save(Adres adres);
    boolean update(Adres adres);
    boolean delete(Adres adres);
    Adres findByReiziger(Reiziger reiziger);
    List<Adres> findAll();

}
