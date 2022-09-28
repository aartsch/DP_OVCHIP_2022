package P4.DAO;

import P2enP3.Domein.Reiziger;
import P4.Domein.OVChipkaart;

import java.util.List;

public interface OVChipkaartDAO {
    public boolean save(OVChipkaart ovChipkaart);
    public boolean update(OVChipkaart ovChipkaart);
    public boolean delete(OVChipkaart ovChipkaart);
    public List<OVChipkaart> findByReiziger(Reiziger reiziger);
    public List<OVChipkaart> findAll();
}
