package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import Entities.*;
import Logic.SocioLogic;

public class DataPrestamo {
	
	public LinkedList<Prestamo> getAll(){
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Prestamo> prestamos= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			  rs = stmt.executeQuery("SELECT * FROM prestamo");
	            if (rs != null) {
	                while (rs.next()) {
	                    Prestamo p = new Prestamo();
	                    p.setIdPrestamo(rs.getInt("idprestamo"));
	                    p.setFechaPrestamo(rs.getDate("fechaPrestamo"));
	                    // Busco el objeto socio para el préstamo
	                    int idSocio = rs.getInt("idSocio");
	                    SocioLogic sociolog = new SocioLogic();
	                    Socio elSocio = new Socio();
	                    elSocio.setIdSocio(idSocio);
	                    elSocio = sociolog.getOneById(elSocio);

	                    // Le asigno el socio al préstamo
	                    p.setSocio(elSocio);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return prestamos;
	} // fin metodo GetAll
	
	public Prestamo getOneById(Prestamo prestamoToSearch) {
	    Prestamo p = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    try {
	        stmt = DbConnector.getInstancia().getConn().prepareStatement(
	                "SELECT * FROM prestamo WHERE idprestamo=?"
	        );
	        stmt.setInt(1, prestamoToSearch.getIdPrestamo());
	        rs = stmt.executeQuery();
	        if (rs != null && rs.next()) {
	            p = new Prestamo();
	            p.setIdPrestamo(rs.getInt("idprestamo"));
	            p.setFechaPrestamo(rs.getDate("fechaPrestamo"));

	            // Busco el objeto socio para el préstamo
	            int idSocio = rs.getInt("idSocio");
	            SocioLogic sociolog = new SocioLogic();
	            Socio elSocio = new Socio();
	            elSocio.setIdSocio(idSocio);
	            elSocio = sociolog.getOneById(elSocio);

	            // Le asigno el socio al préstamo
	            p.setSocio(elSocio);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) { rs.close(); }
	            if (stmt != null) { stmt.close(); }
	            DbConnector.getInstancia().releaseConn();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return p;
	} // Fin Metodo GetById
	
	public void add(Prestamo prestamo) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		//SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"INSERT INTO prestamo(fechaPrestamo, idSocio) VALUES (?, ?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, prestamo.getFechaPrestamo().toString()); // OJO ACA (MIRAR COMO MAPEA)
			stmt.setInt(2, prestamo.getSocio().getIdSocio());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
            	prestamo.setIdPrestamo(keyResultSet.getInt(1));
            }

			
		} catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}

	} // FIN METODO ADD
	
	
	public void update(Prestamo prestamo) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update prestamo set fechaPrestamo=?, idSocio=? where idprestamo=?");
			stmt.setString(1, prestamo.getFechaPrestamo().toString()); // OJO ACA (MIRAR COMO MAPEA)
			stmt.setInt(2, prestamo.getSocio().getIdSocio());
			stmt.setInt(3, prestamo.getIdPrestamo());
			stmt.executeUpdate();
		} catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
	} // FIN METODO UPDATE
	
	public void remove(Prestamo prestamo) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from prestamo where idprestamo=?");
			stmt.setInt(1, prestamo.getIdPrestamo());
			stmt.executeUpdate();
		} catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
	}	// FIN METODO REMOVE


}
