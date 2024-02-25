package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Entities.PoliticaPrestamo;

public class DataPoliticaPrestamo {

	public LinkedList<PoliticaPrestamo> getAll() {
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<PoliticaPrestamo> Politicas = new LinkedList<>();

		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select * from politicaprestamo");
			if (rs != null) {
				while (rs.next()) {
					PoliticaPrestamo p = new PoliticaPrestamo();
					p.setIdPoliticaPrestamo(rs.getInt("idPoliticaPrestamo"));
					p.setFechaDesde(rs.getDate("fechaDesde"));
					p.setCantMaxLibrosPend(rs.getInt("cantMaxLibrosPend"));
					Politicas.add(p);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();

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
				e.printStackTrace();
			}
		}

		return Politicas;
	} // fin metodo GetAll

	public PoliticaPrestamo getById(PoliticaPrestamo PoliticaPrestamoToSearch) {
		PoliticaPrestamo p = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("select * from politicaprestamo where idPoliticaPrestamo=?");
			stmt.setInt(1, PoliticaPrestamoToSearch.getIdPoliticaPrestamo());
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				p = new PoliticaPrestamo();
				p.setIdPoliticaPrestamo(rs.getInt("idPoliticaPrestamo"));
				p.setFechaDesde(rs.getDate("fechaDesde"));
				p.setCantMaxLibrosPend(rs.getInt("cantMaxLibrosPend"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
				e.printStackTrace();
			}
		}

		return p;
	} // Fin Metodo GetById

	public void add(PoliticaPrestamo PoliticaPrestamo) {
		PreparedStatement stmt = null;
		ResultSet keyResultSet = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"insert into politicaprestamo(fechaDesde, cantMaxLibrosPend) values(?,?)",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, PoliticaPrestamo.getFechaDesde().toString());
			stmt.setInt(2, PoliticaPrestamo.getCantMaxLibrosPend());
			stmt.executeUpdate();

			keyResultSet = stmt.getGeneratedKeys();
			if (keyResultSet != null && keyResultSet.next()) {
				PoliticaPrestamo.setIdPoliticaPrestamo(keyResultSet.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (keyResultSet != null)
					keyResultSet.close();
				if (stmt != null)
					stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	} // FIN METODO ADD

	public void update(PoliticaPrestamo PoliticaPrestamo) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"update politicaprestamo set fechaDesde=?, cantMaxLibrosPend=? where idPoliticaPrestamo=?");
			stmt.setString(1, PoliticaPrestamo.getFechaDesde().toString());
			stmt.setInt(2, PoliticaPrestamo.getCantMaxLibrosPend());
			stmt.setInt(3, PoliticaPrestamo.getIdPoliticaPrestamo());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	} // FIN METODO UPDATE

	public void remove(PoliticaPrestamo PoliticaPrestamo) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("delete from politicaprestamo where idPoliticaPrestamo=?");
			stmt.setInt(1, PoliticaPrestamo.getIdPoliticaPrestamo());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	} // FIN METODO REMOVE

	// Metodo para obtener la ultima politica de prestamo con fecha desde mas
	// reciente
	public PoliticaPrestamo getLast() {
		PoliticaPrestamo p = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery(
					"select * from politicaprestamo where fechaDesde=(select max(fechaDesde) from politicaprestamo where fechaDesde<=current_date)");
			if (rs != null && rs.next()) {
				p = new PoliticaPrestamo();
				p.setIdPoliticaPrestamo(rs.getInt("idPoliticaPrestamo"));
				p.setFechaDesde(rs.getDate("fechaDesde"));
				p.setCantMaxLibrosPend(rs.getInt("cantMaxLibrosPend"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
				e.printStackTrace();
			}
		}

		return p;
	} // Fin Metodo getLast

}
