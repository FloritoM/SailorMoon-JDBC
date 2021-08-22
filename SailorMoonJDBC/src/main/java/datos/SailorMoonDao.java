package datos;

import static datos.Conexion.close;
import static datos.Conexion.getConnection;
import domain.SailorMoonDTO;
import java.sql.*;
import java.util.*;

public class SailorMoonDao implements iSailorMoonDao{

    private static final String SQL_SELECT = "SELECT nombre, apellido, planeta FROM personajes";
    private static final String SQL_INSERT = "INSERT INTO personajes(nombre, apellido, planeta) VALUES(?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE personajes SET nombre = ?, apellido = ? WHERE planeta = ?";
    private static final String SQL_DELETE = "DELETE FROM personajes WHERE planeta = ?";
    private static final String SQL_SELECT_BY_PLANET = "SELECT nombre, apellido, planeta FROM personajes WHERE planeta=?";
    private static final String SQL_CHECK = "SELECT * FROM personajes WHERE planeta=?";
    
    @Override
    public SailorMoonDTO selectByPlanet(SailorMoonDTO sailor) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null; 
        ResultSet rs = null;
        
        try {
            conn = Conexion.getConnection(); 
            stmt = conn.prepareStatement(SQL_SELECT_BY_PLANET);
            stmt.setString(1, sailor.getPlaneta());
            rs = stmt.executeQuery();
            
            rs.next();
            
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String planeta = rs.getString("planeta");
            
            sailor = new SailorMoonDTO(nombre, apellido, planeta);
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally { 
            try {
                Conexion.close(rs); 
                Conexion.close(stmt);
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return sailor;
    }
    
    @Override
    public List<SailorMoonDTO> select() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null; 
        ResultSet rs = null;
        SailorMoonDTO personaje = null;
        List<SailorMoonDTO> personajes = new ArrayList<>();
        
        try {
            conn = Conexion.getConnection(); 
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String planeta = rs.getString("planeta");
                
                personaje = new SailorMoonDTO(nombre, apellido, planeta);
                personajes.add(personaje);
                
                /*
                personaje = new SailorMoonDTO();
                personajes.setNombre(nombre);
                personajes.setApellido(apellido);
                personajes.setPlaneta(planeta);
                personajes.add(personaje);
                */
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally { 
            try {
                Conexion.close(rs); 
                Conexion.close(stmt);
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        
        return personajes;
    }

    @Override
    public int insert(SailorMoonDTO sailorMoon) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            
            stmt.setString(1, sailorMoon.getNombre());
            stmt.setString(2, sailorMoon.getApellido());
            stmt.setString(3, sailorMoon.getPlaneta());
            
            registros = stmt.executeUpdate(); 
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally{
            try {
                Conexion.close(stmt);
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }

    @Override
    public int update(SailorMoonDTO sailorMoon) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            stmt.setString(1, sailorMoon.getNombre()); 
            stmt.setString(2, sailorMoon.getApellido()); 
            stmt.setString(3, sailorMoon.getPlaneta());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }

    @Override
    public int delete(SailorMoonDTO sailorMoon) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setString(1, sailorMoon.getPlaneta());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
    @Override
    public boolean check(SailorMoonDTO sailor) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null; 
        ResultSet rs = null;
        
        try {
            conn = Conexion.getConnection(); 
            stmt = conn.prepareStatement(SQL_CHECK);
            stmt.setString(1, sailor.getPlaneta());
            rs = stmt.executeQuery();
            
            //rs.next();
            
            if(rs.absolute(1)){
                return true;
            } 
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally { 
            try {
                Conexion.close(rs); 
                Conexion.close(stmt);
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return false;
    }
    
    @Override
    public void checkPlanet(SailorMoonDTO sailor) throws SQLException{
        SailorMoonDao smDao = new SailorMoonDao();
        if(smDao.check(sailor) == true){
            System.out.println("Personaje si existe en la base");
        } else {
            System.out.println("No se encontró");
        }
    }
}
