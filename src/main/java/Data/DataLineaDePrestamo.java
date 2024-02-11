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

public class DataLineaDePrestamo {

	public Prestamo getAllByPrestamo(Prestamo prestamo) {
		LineaDePrestamo l = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		LinkedList<LineaDePrestamo> prestamos = new LinkedList<>();

		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("select * from lineadeprestamo where idprestamo=?");
			stmt.setInt(1, prestamo.getIdPrestamo());
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					l = new LineaDePrestamo();
					l.setIdLineaPrestamo(rs.getInt("idlineadeprestamo"));
					l.setFechaDevolucionTeorica(rs.getDate("fechaDevolucionTeorica"));
					l.setFechaDevolucionTeorica(rs.getDate("fechaDevolucionReal"));
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
					prestamos.add(l);
				}
				prestamo.setLineasDePrestamo(prestamos);
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
		return prestamo;
	} // fin metodo GetAll

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
				l.setFechaDevolucionTeorica(rs.getDate("fechaDevolucionReal"));
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

		return l;
	} // Fin Metodo GetById

	public void addAll(List<LineaDePrestamo> lineasDePrestamo) {
		PreparedStatement stmt = null;
		ResultSet keyResultSet = null;
		StringBuilder cantidadLineas = new StringBuilder();
		int index = 0;
		try {
			/*
			 * for (LineaDePrestamo lineaDePrestamo : lineasDePrestamo) { if (index ==
			 * (lineasDePrestamo.size() - 1)) { cantidadLineas.append(" ? "); } else {
			 * cantidadLineas.append("?, "); } index++; }
			 */
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

	} // FIN METODO ADD ONE

	public void update(LineaDePrestamo linea, Prestamo pres) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"update lineadeprestamo set fechaDevolucionTeorica=?, fechaDevolucionReal=?, estadoLinea=?, idPrestamo=?, idEjemplar=? where idlineadeprestamo=?");
			stmt.setString(1, linea.getFechaDevolucionTeorica().toString());
			stmt.setString(2, linea.getFechaDevolucionReal().toString());
			stmt.setString(3, linea.getEstadoLinea());
			stmt.setInt(4, pres.getIdPrestamo());
			stmt.setInt(5, linea.getEjemplar().getIdEjemplar());
			stmt.setInt(6, linea.getIdLineaPrestamo());
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

	public void remove(LineaDePrestamo linea) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("delete from lineadeprestamo where idlineadeprestamo=?");
			stmt.setInt(1, linea.getIdLineaPrestamo());
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

}
