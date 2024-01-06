package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Entities.Socio;

public class DataSocio {

	public LinkedList<Socio> getAll(){
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Socio> socios= new LinkedList<>();

		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select * from socio");
			if(rs!=null) {
				while(rs.next()) {
					Socio s=new Socio();
					s.setIdSocio(rs.getInt("idsocio"));
					s.setNombre(rs.getString("nombre"));
					s.setApellido(rs.getString("apellido"));
					s.setEmail(rs.getString("email"));
					s.setDomicilio(rs.getString("domicilio"));
					s.setTelefono(rs.getString("telefono"));
					s.setEstadoSocio(rs.getString("estadoSocio"));
					s.setContrasenia(rs.getString("contrasenia"));
					s.setAdmin(rs.getBoolean("isAdmin"));
					s.setUsuario(rs.getString("usuario"));
					socios.add(s);
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


		return socios;
	} // fin metodo GetAll

	public Socio getById(Socio socioToSearch) {
		Socio s=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from socio where idsocio=?"
					);
			stmt.setInt(1, socioToSearch.getIdSocio());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				s=new Socio();
				s.setIdSocio(rs.getInt("idsocio"));
				s.setNombre(rs.getString("nombre"));
				s.setApellido(rs.getString("apellido"));
				s.setEmail(rs.getString("email"));
				s.setDomicilio(rs.getString("domicilio"));
				s.setTelefono(rs.getString("telefono"));
				s.setEstadoSocio(rs.getString("estadoSocio"));
				s.setContrasenia(rs.getString("contrasenia"));
				s.setAdmin(rs.getBoolean("isAdmin"));
				s.setUsuario(rs.getString("usuario"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Logger e.getMessage();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return s;
	} // Fin Metodo GetById

	public void add(Socio socio) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into socio(nombre, apellido, email, domicilio, telefono, estadoSocio, contrasenia, isAdmin, usuario) values(?,?,?,?,?,?,?,?,?)",
							Statement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, socio.getNombre());
			stmt.setString(2, socio.getApellido());
			stmt.setString(3, socio.getEmail());
			stmt.setString(4, socio.getDomicilio());
			stmt.setString(5, socio.getTelefono());
			stmt.setString(6, socio.getEstadoSocio());
			stmt.setString(7, socio.getContrasenia());
			stmt.setBoolean(8, socio.getAdmin());
			stmt.setString(9, socio.getUsuario());
			stmt.executeUpdate();

			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                socio.setIdSocio(keyResultSet.getInt(1));
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

	public void update(Socio socio) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update socio set nombre=?, apellido=?, email=?, domicilio=?, telefono=?, estadoSocio=?, contrasenia=?, isAdmin=?, usuario=? where idsocio=?");
			stmt.setString(1, socio.getNombre());
			stmt.setString(2, socio.getApellido());
			stmt.setString(3, socio.getEmail());
			stmt.setString(4, socio.getDomicilio());
			stmt.setString(5, socio.getTelefono());
			stmt.setString(6, socio.getEstadoSocio());
			stmt.setString(7, socio.getContrasenia());
			stmt.setBoolean(8, socio.getAdmin());
			stmt.setString(9, socio.getUsuario());
			stmt.setInt(10, socio.getIdSocio());
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

	public void remove(Socio socio) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from socio where idsocio=?");
			stmt.setInt(1, socio.getIdSocio());
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

	public Socio getByUser(Socio socio) {
		Socio returnedSocio = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select idsocio, apellido, nombre, email, domicilio, telefono, estadoSocio, contrasenia, isAdmin, usuario from socio where usuario=? and contrasenia=?"
					);
			stmt.setString(1, socio.getUsuario());
			stmt.setString(2, socio.getContrasenia());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				returnedSocio=new Socio();
				returnedSocio.setIdSocio(rs.getInt("idsocio"));
				returnedSocio.setNombre(rs.getString("nombre"));
				returnedSocio.setApellido(rs.getString("apellido"));
				returnedSocio.setEmail(rs.getString("email"));
				returnedSocio.setDomicilio(rs.getString("domicilio"));
				returnedSocio.setTelefono(rs.getString("telefono"));
				returnedSocio.setEstadoSocio(rs.getString("estadoSocio"));
				returnedSocio.setContrasenia(rs.getString("contrasenia"));
				returnedSocio.setAdmin(rs.getBoolean("isAdmin"));
				returnedSocio.setUsuario(rs.getString("usuario"));
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

		return returnedSocio;
	}

}
