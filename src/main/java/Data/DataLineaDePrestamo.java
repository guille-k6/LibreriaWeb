package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import Entities.Ejemplar;
import Entities.LineaDePrestamo;
import Entities.Prestamo;
import Logic.EjemplarLogic;
import utils.LoggerError;

public class DataLineaDePrestamo {

	/**
	 * Dado un prestamo, le completa sus lineas de prestamo
	 * 
	 * @param prestamo
	 * @return un prestamo con sus lineas de prestamo completas
	 */
	public Prestamo getAllByPrestamo(Prestamo prestamo) {
		// TODO: Mejorar la forma en la que se busca el ejemplar
		PreparedStatement stmt = null;
		ResultSet rs = null;
		LinkedList<LineaDePrestamo> lineasDePrestamo = new LinkedList<>();
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("select * from lineadeprestamo where idprestamo=?");
			stmt.setInt(1, prestamo.getIdPrestamo());
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					LineaDePrestamo l = new LineaDePrestamo();
					l.setIdLineaPrestamo(rs.getInt("idlineadeprestamo"));
					l.setFechaDevolucionTeorica(rs.getDate("fechaDevolucionTeorica"));
					l.setFechaDevolucionReal(rs.getDate("fechaDevolucionReal"));
					l.setEstadoLinea(rs.getString("estadoLinea"));
					// Busco el objeto ejemplar para la linea
					int idEjemplar = rs.getInt("idEjemplar");
					EjemplarLogic ejelog = new EjemplarLogic();
					Ejemplar elEjemplar = new Ejemplar();
					elEjemplar.setIdEjemplar(idEjemplar);
					elEjemplar = ejelog.getOneById(elEjemplar);
					// Le agrego el ejemplar
					l.setEjemplar(elEjemplar);
					// Aniado la linea con el ejemplar incluido a la LinkedList.
					lineasDePrestamo.add(l);
				}
				prestamo.setLineasDePrestamo(lineasDePrestamo);
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
		return prestamo;
	}

	public LineaDePrestamo getById(LineaDePrestamo lineaToSearch) {
		LineaDePrestamo l = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("select * from lineadeprestamo where idlineadeprestamo=?");
			stmt.setInt(1, lineaToSearch.getIdLineaPrestamo());
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				l = new LineaDePrestamo();
				l.setIdLineaPrestamo(rs.getInt("idlineadeprestamo"));
				l.setFechaDevolucionTeorica(rs.getDate("fechaDevolucionTeorica"));
				l.setFechaDevolucionReal(rs.getDate("fechaDevolucionReal"));
				l.setEstadoLinea(rs.getString("estadoLinea"));
				// Busco el objeto ejemplar para la linea
				int idEjemplar = rs.getInt("idEjemplar");
				EjemplarLogic ejelog = new EjemplarLogic();
				Ejemplar elEjemplar = new Ejemplar();
				elEjemplar.setIdEjemplar(idEjemplar);
				elEjemplar = ejelog.getOneById(elEjemplar);
				// Le agrego el ejemplar
				l.setEjemplar(elEjemplar);
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

		return l;
	}

	/**
	 * Inserta lineas de prestamo en batch
	 * 
	 * @param lineasDePrestamo lista de lineasdePrestamo que seran insertadas
	 */
	public void addAll(List<LineaDePrestamo> lineasDePrestamo) {
		PreparedStatement stmt = null;
		ResultSet keyResultSet = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"INSERT INTO lineadeprestamo(fechaDevolucionTeorica, idPrestamo, idEjemplar) VALUES(?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			for (LineaDePrestamo lineaDePrestamo : lineasDePrestamo) {
				stmt.setDate(1, lineaDePrestamo.getFechaDevolucionTeorica());
				stmt.setInt(2, lineaDePrestamo.getIdPrestamo());
				stmt.setInt(3, lineaDePrestamo.getEjemplar().getIdEjemplar());
				stmt.addBatch();
			}
			stmt.clearParameters();
			stmt.executeBatch();

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

	public void addOne(Prestamo prestamo, LineaDePrestamo l) {

		PreparedStatement stmt = null;
		ResultSet keyResultSet = null;
		// SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"insert into lineadeprestamo(fechaDevolucionTeorica, fechaDevolucionReal, estadoLinea, idPrestamo, idEjemplar) values(?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setDate(1, l.getFechaDevolucionTeorica());
			stmt.setDate(2, l.getFechaDevolucionReal());
			stmt.setString(3, l.getEstadoLinea());
			stmt.setInt(4, prestamo.getIdPrestamo());
			stmt.setInt(5, l.getEjemplar().getIdEjemplar());
			stmt.executeUpdate();

			keyResultSet = stmt.getGeneratedKeys();
			if (keyResultSet != null && keyResultSet.next()) {
				l.setIdLineaPrestamo(keyResultSet.getInt(1));
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

	} // FIN METODO ADD ONE

	public void update(LineaDePrestamo linea) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"update lineadeprestamo set fechaDevolucionTeorica=?, fechaDevolucionReal=?, estadoLinea=?, idEjemplar=? where idlineadeprestamo=?");
			stmt.setString(1, linea.getFechaDevolucionTeorica().toString());
			stmt.setString(2, linea.getFechaDevolucionReal().toString());
			stmt.setString(3, linea.getEstadoLinea());
			stmt.setInt(4, linea.getEjemplar().getIdEjemplar());
			stmt.setInt(5, linea.getIdLineaPrestamo());
			stmt.executeUpdate();
		} catch (SQLException e) {
			LoggerError.log(e.getStackTrace(), e.getMessage());
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				LoggerError.log(e.getStackTrace(), e.getMessage());
			}
		}
	} // FIN METODO UPDATE

	public void remove(LineaDePrestamo linea) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("delete from lineadeprestamo where idlineadeprestamo=?");
			stmt.setInt(1, linea.getIdLineaPrestamo());
			stmt.executeUpdate();
		} catch (SQLException e) {
			LoggerError.log(e.getStackTrace(), e.getMessage());
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				LoggerError.log(e.getStackTrace(), e.getMessage());
			}
		}
	} // FIN METODO REMOVE

}
