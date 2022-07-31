package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import Entities.*;

public class DataAutor {
	
	public LinkedList<Autor> getAll(){
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Autor> autores= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select * from autor");
			if(rs!=null) {
				while(rs.next()) {
					Autor a=new Autor();
					a.setIdAutor(rs.getInt("idautor"));
					a.setNombre(rs.getString("nombre"));
					a.setApellido(rs.getString("apellido"));
					autores.add(a);
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
		
		
		return autores;
	} // fin metodo GetAll
	
	public Autor getById(Autor autorToSearch) {
		Autor a=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from autor where idautor=?"
					);
			stmt.setInt(1, autorToSearch.getIdAutor());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				a=new Autor();
				a.setIdAutor(rs.getInt("idautor"));
				a.setNombre(rs.getString("nombre"));
				a.setApellido(rs.getString("apellido"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return a;
	} // Fin Metodo GetById
	
	public void add(Autor autor) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into autor(nombre, apellido) values(?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, autor.getNombre());
			stmt.setString(2, autor.getApellido());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                autor.setIdAutor(keyResultSet.getInt(1));
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
	
	public void update(Autor autor) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update autor set nombre=?, apellido=? where idautor=?");
			stmt.setString(1, autor.getNombre());
			stmt.setString(2, autor.getApellido());
			stmt.setInt(3, autor.getIdAutor());
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
	
	public void remove(Autor autor) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from autor where idautor=?");
			stmt.setInt(1, autor.getIdAutor());
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
