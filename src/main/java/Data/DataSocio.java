package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Entities.Socio;
import utils.LoggerError;

public class DataSocio {

	public LinkedList<Socio> getAll() {
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<Socio> socios = new LinkedList<>();

		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select * from socio");
			if (rs != null) {
				while (rs.next()) {
					Socio s = new Socio();
					s.setIdSocio(rs.getInt("idsocio"));
					s.setNombre(rs.getString("nombre"));
					s.setApellido(rs.getString("apellido"));
					s.setEmail(rs.getString("email"));
					s.setDomicilio(rs.getString("domicilio"));
					s.setTelefono(rs.getString("telefono"));
					s.setContrasenia(rs.getString("contrasenia"));
					s.setAdmin(rs.getBoolean("isAdmin"));
					s.setUsuario(rs.getString("usuario"));
					socios.add(s);
				}
			}

		} catch (SQLException e) {
			LoggerError.log(e.getStackTrace(), e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				LoggerError.log(e.getStackTrace(), e.getMessage());
			}
		}
		return socios;
	}

	public Socio getById(Socio socioToSearch) {
		Socio s = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("select * from socio where idsocio=?");
			stmt.setInt(1, socioToSearch.getIdSocio());
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				s = new Socio();
				s.setIdSocio(rs.getInt("idsocio"));
				s.setNombre(rs.getString("nombre"));
				s.setApellido(rs.getString("apellido"));
				s.setEmail(rs.getString("email"));
				s.setDomicilio(rs.getString("domicilio"));
				s.setTelefono(rs.getString("telefono"));
				s.setUsuario(rs.getString("usuario"));
				s.setAdmin(rs.getBoolean("isAdmin"));
			}
		} catch (SQLException e) {
			LoggerError.log(e.getStackTrace(), e.getMessage());
			// Logger e.getMessage();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				LoggerError.log(e.getStackTrace(), e.getMessage());
			}
		}

		return s;
	}

	public void add(Socio socio) {
		PreparedStatement stmt = null;
		ResultSet keyResultSet = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"insert into socio(nombre, apellido, email, domicilio, telefono, contrasenia, isAdmin, usuario) values(?,?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, socio.getNombre());
			stmt.setString(2, socio.getApellido());
			stmt.setString(3, socio.getEmail());
			stmt.setString(4, socio.getDomicilio());
			stmt.setString(5, socio.getTelefono());
			stmt.setString(6, socio.getContrasenia());
			stmt.setBoolean(7, socio.getAdmin());
			stmt.setString(8, socio.getUsuario());
			stmt.executeUpdate();

			keyResultSet = stmt.getGeneratedKeys();
			if (keyResultSet != null && keyResultSet.next()) {
				socio.setIdSocio(keyResultSet.getInt(1));
			}

		} catch (SQLException e) {
			LoggerError.log(e.getStackTrace(), e.getMessage());
		} finally {
			try {
				if (keyResultSet != null)
					keyResultSet.close();
				if (stmt != null)
					stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				LoggerError.log(e.getStackTrace(), e.getMessage());
			}
		}

	}

	public void update(Socio socio) throws Exception {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"update socio set nombre=?, apellido=?, email=?, domicilio=?, telefono=?, contrasenia=?, isAdmin=?, usuario=? where idsocio=?");
			stmt.setString(1, socio.getNombre());
			stmt.setString(2, socio.getApellido());
			stmt.setString(3, socio.getEmail());
			stmt.setString(4, socio.getDomicilio());
			stmt.setString(5, socio.getTelefono());
			stmt.setString(6, socio.getContrasenia());
			stmt.setBoolean(7, socio.getAdmin());
			stmt.setString(8, socio.getUsuario());
			stmt.setInt(9, socio.getIdSocio());
			stmt.executeUpdate();
		} catch (Exception e) {
			LoggerError.log(e.getStackTrace(), e.getMessage());
			throw new Exception(e);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				LoggerError.log(e.getStackTrace(), e.getMessage());
			}
		}
	}

	public void updateWithoutPassword(Socio socio) throws Exception {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"update socio set nombre=?, apellido=?, email=?, domicilio=?, telefono=?, isAdmin=?, usuario=? where idsocio=?");
			stmt.setString(1, socio.getNombre());
			stmt.setString(2, socio.getApellido());
			stmt.setString(3, socio.getEmail());
			stmt.setString(4, socio.getDomicilio());
			stmt.setString(5, socio.getTelefono());
			stmt.setBoolean(6, socio.getAdmin());
			stmt.setString(7, socio.getUsuario());
			stmt.setInt(8, socio.getIdSocio());
			stmt.executeUpdate();
		} catch (Exception e) {
			LoggerError.log(e.getStackTrace(), e.getMessage());
			throw new Exception(e);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				LoggerError.log(e.getStackTrace(), e.getMessage());
			}
		}
	}

	public void remove(Socio socio) throws Exception {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("delete from socio where idsocio=?");
			stmt.setInt(1, socio.getIdSocio());
			stmt.executeUpdate();
		} catch (SQLException e) {
			LoggerError.log(e.getStackTrace(), e.getMessage());
			throw new Exception(e);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				LoggerError.log(e.getStackTrace(), e.getMessage());
			}
		}
	}

	public Socio getByUser(Socio socio) {
		Socio returnedSocio = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"select idsocio, apellido, nombre, email, domicilio, telefono, contrasenia, isAdmin, usuario from socio where usuario=? and contrasenia=?");
			stmt.setString(1, socio.getUsuario());
			stmt.setString(2, socio.getContrasenia());
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				returnedSocio = new Socio();
				returnedSocio.setIdSocio(rs.getInt("idsocio"));
				returnedSocio.setNombre(rs.getString("nombre"));
				returnedSocio.setApellido(rs.getString("apellido"));
				returnedSocio.setEmail(rs.getString("email"));
				returnedSocio.setDomicilio(rs.getString("domicilio"));
				returnedSocio.setTelefono(rs.getString("telefono"));
				returnedSocio.setContrasenia(rs.getString("contrasenia"));
				returnedSocio.setAdmin(rs.getBoolean("isAdmin"));
				returnedSocio.setUsuario(rs.getString("usuario"));
			}
		} catch (SQLException e) {
			LoggerError.log(e.getStackTrace(), e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				LoggerError.log(e.getStackTrace(), e.getMessage());
			}
		}

		return returnedSocio;
	}

	/**
	 * Indica la cantidad de libros que tiene fecha de devolucion nula para un
	 * socio.
	 * 
	 * @param socio
	 * @return Cantidad de libros que un socio tiene prestados actualmente.
	 */
	public int getCantidadLibrosPrestadosBySocio(Socio socio) {
		int cantidad = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT count(*) as cantidad FROM socio s inner join prestamo p on s.idsocio= p.idSocio inner join lineadeprestamo ldp on ldp.idPrestamo = p.idprestamo where ldp.fechaDevolucionReal is null and s.idsocio = ?;");
			stmt.setInt(1, socio.getIdSocio());
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				cantidad = rs.getInt("cantidad");
			}
		} catch (SQLException e) {
			LoggerError.log(e.getStackTrace(), e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				LoggerError.log(e.getStackTrace(), e.getMessage());
			}
		}

		return cantidad;
	} // FIN METODO GETCANTIDADLIBROSPRESTADOSBYSOCIO

	public List<Socio> getAllSociosThatMatch(String matching) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Socio> socios = new ArrayList<>();
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT * FROM socio WHERE nombre LIKE ? OR apellido LIKE ? OR CONCAT(nombre, ' ', apellido) LIKE ?;");
			String likeMatcher = "%" + matching + "%";
			stmt.setString(1, likeMatcher);
			stmt.setString(2, likeMatcher);
			stmt.setString(3, likeMatcher);
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Socio s = new Socio();
					s.setIdSocio(rs.getInt("idsocio"));
					s.setNombre(rs.getString("nombre"));
					s.setApellido(rs.getString("apellido"));
					s.setEmail(rs.getString("email"));
					s.setDomicilio(rs.getString("domicilio"));
					s.setTelefono(rs.getString("telefono"));
					s.setUsuario(rs.getString("usuario"));
					socios.add(s);
				}
			}

		} catch (SQLException e) {
			LoggerError.log(e.getStackTrace(), e.getMessage());

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				LoggerError.log(e.getStackTrace(), e.getMessage());
			}
		}
		return socios;
	}

	public boolean isUsernameAvailable(String username) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean isAvailable = true;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("select * from socio where usuario=?");
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				isAvailable = false;
			}
		} catch (SQLException e) {
			LoggerError.log(e.getStackTrace(), e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				LoggerError.log(e.getStackTrace(), e.getMessage());
			}
		}
		return isAvailable;
	}

}
