package datos;

import domain.SailorMoonDTO;
import java.sql.SQLException;
import java.util.List;

public interface iSailorMoonDao {
    
    public List<SailorMoonDTO> select() throws SQLException;

    public int insert(SailorMoonDTO sailorMoon) throws SQLException;
    
    public int update(SailorMoonDTO sailorMoon) throws SQLException;
    
    public int delete(SailorMoonDTO sailorMoon) throws SQLException;
    
    public SailorMoonDTO selectByPlanet(SailorMoonDTO sailor) throws SQLException;
    
    public boolean check(SailorMoonDTO sailor) throws SQLException;
    
    public void checkPlanet(SailorMoonDTO sailor) throws SQLException;
}
