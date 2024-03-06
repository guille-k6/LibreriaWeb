package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Entities.Cuotas;
import Entities.Socio;
import Logic.SocioLogic;
import utils.LoggerError;

public class DataCuotas {

	public LinkedList<Cuotas> getAll() {
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<Cuotas> cuotas = new LinkedList<>();

		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select * from cuotas");
			if (rs != null) {
				while (rs.next()) {
					Cuotas c = new Cuotas();
					c.setIdCuota(rs.getInt("idcuotas")); // La clase se llama en plural el get es en singular pero la
															// columna en la bd es en plural
					c.setFechaPago(rs.getDate("fechaPago"));
					c.setFechaDesde(rs.getDate("fechaDesde"));
					c.setFechaHasta(rs.getDate("fechaHasta"));
					// Busco el objeto autor para el libro
					int idSocio = rs.getInt("idSocio");
					SocioLogic soclog = new SocioLogic();
					Socio elSocio = new Socio();
					elSocio.setIdSocio(idSocio);
					elSocio = soclog.getOneById(elSocio);
					// Le agrego el autor
					c.setSocio(elSocio);
					c.setEstado(rs.getString("estado"));
					// A�ado el libro con autor incluido a la LinkedList.
					cuotas.add(c);
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
		return cuotas;
	} // fin metodo GetAll

	public Cuotas getById(Cuotas cuotasToSearch) {
		Cuotas c = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("select * from cuotas where idcuotas=?");
			stmt.setInt(1, cuotasToSearch.getIdCuota());
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				c = new Cuotas();
				c.setIdCuota(rs.getInt("idcuotas")); // La clase se llama en plural el get es en singular pero la
														// columna en la bd es en plural
				c.setFechaPago(rs.getDate("fechaPago"));
				c.setFechaDesde(rs.getDate("fechaDesde"));
				c.setFechaHasta(rs.getDate("fechaHasta"));
				// Busco el objeto autor para el libro
				int idSocio = rs.getInt("idSocio");
				SocioLogic soclog = new SocioLogic();
				Socio elSocio = new Socio();
				elSocio.setIdSocio(idSocio);
				elSocio = soclog.getOneById(elSocio);
				// Le agrego el autor
				c.setSocio(elSocio);
				c.setEstado(rs.getString("estado"));
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

		return c;
	} // Fin Metodo GetById

	public void add(Cuotas cuotas) {
		PreparedStatement stmt = null;
		ResultSet keyResultSet = null;
		// SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"insert into cuotas(fechaPago, fechaDesde, fechaHasta, idSocio, estado) values(?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, cuotas.getFechaPago().toString());
			stmt.setString(2, cuotas.getFechaDesde().toString());
			stmt.setString(3, cuotas.getFechaHasta().toString());
			stmt.setInt(4, cuotas.getSocio().getIdSocio());
			stmt.setString(5, cuotas.getEstado());
			stmt.executeUpdate();

			keyResultSet = stmt.getGeneratedKeys();
			if (keyResultSet != null && keyResultSet.next()) {
				cuotas.setIdCuota(keyResultSet.getInt(1));
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

	} // FIN METODO ADD

	public void update(Cuotas cuotas) {
		PreparedStatement stmt = null;
		String fechaPago;
		if (cuotas.getFechaPago() == null) {
			fechaPago = null;
		} else {
			fechaPago = cuotas.getFechaPago().toString();
		}

		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"update cuotas set fechaPago=?, fechaDesde=?, fechaHasta=?, idSocio=?, estado=? where idcuotas = ?");
			stmt.setString(1, fechaPago);
			stmt.setString(2, cuotas.getFechaDesde().toString());
			stmt.setString(3, cuotas.getFechaHasta().toString());
			stmt.setInt(4, cuotas.getSocio().getIdSocio());
			stmt.setString(5, cuotas.getEstado());
			stmt.setInt(6, cuotas.getIdCuota());
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

	public void remove(Cuotas cuotas) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("delete from cuotas where idcuotas=?");
			stmt.setInt(1, cuotas.getIdCuota());
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

	public LinkedList<Cuotas> getCuotasByUser(Socio socio) { // GUARDA CON ESTE NO SE SI ANDA BIEN
		LinkedList<Cuotas> c = new LinkedList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("select * from cuotas where idSocio=?");
			stmt.setInt(1, socio.getIdSocio());
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				Cuotas q = new Cuotas();
				q.setIdCuota(rs.getInt("idcuotas")); // La clase se llama en plural el get es en singular pero la
														// columna en la bd es en plural
				q.setFechaPago(rs.getDate("fechaPago"));
				q.setFechaDesde(rs.getDate("fechaDesde"));
				q.setFechaHasta(rs.getDate("fechaHasta"));
				// Busco el objeto autor para el libro
				int idSocio = rs.getInt("idSocio");
				SocioLogic soclog = new SocioLogic();
				Socio elSocio = new Socio();
				elSocio.setIdSocio(idSocio);
				elSocio = soclog.getOneById(elSocio);
				// Le agrego el autor
				q.setSocio(elSocio);
				q.setEstado(rs.getString("estado"));
				// A�ado la cuota a la lista de cuotas
				c.add(q);
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

		return c;
		// Fin Metodo getCuotasByUser
	}

	public LinkedList<Cuotas> getCuotasImpagasByUser(Socio socio) {
		LinkedList<Cuotas> lasCuotas = new LinkedList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("select * from cuotas where idSocio=? and fechaPago is null");
			stmt.setInt(1, socio.getIdSocio());
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Cuotas c = new Cuotas();
					c.setIdCuota(rs.getInt("idcuotas")); // La clase se llama en plural el get es en singular pero la
															// columna en la bd es en plural
					c.setFechaPago(rs.getDate("fechaPago"));
					c.setFechaDesde(rs.getDate("fechaDesde"));
					c.setFechaHasta(rs.getDate("fechaHasta"));
					// Busco el objeto autor para el libro
					int idSocio = rs.getInt("idSocio");
					SocioLogic soclog = new SocioLogic();
					Socio elSocio = new Socio();
					elSocio.setIdSocio(idSocio);
					elSocio = soclog.getOneById(elSocio);
					// Le agrego el autor
					c.setSocio(elSocio);
					c.setEstado(rs.getString("estado"));

					lasCuotas.add(c);
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

		return lasCuotas;
	}

	public LinkedList<Cuotas> getCuotasAConfirmarByUser(Socio socio) {
		LinkedList<Cuotas> lasCuotas = new LinkedList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("select * from cuotas where idSocio=? and estado = 'A_Confirmar'");
			stmt.setInt(1, socio.getIdSocio());
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Cuotas c = new Cuotas();
					c.setIdCuota(rs.getInt("idcuotas"));
					c.setFechaPago(rs.getDate("fechaPago"));
					c.setFechaDesde(rs.getDate("fechaDesde"));
					c.setFechaHasta(rs.getDate("fechaHasta"));
					int idSocio = rs.getInt("idSocio");
					SocioLogic soclog = new SocioLogic();
					Socio elSocio = soclog.getOneById(new Socio(idSocio));
					c.setSocio(elSocio);
					c.setEstado(rs.getString("estado"));

					lasCuotas.add(c);
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

		return lasCuotas;
	}

	public LinkedList<Socio> getUsuariosAConfirmar() {
		LinkedList<Socio> losSocios = new LinkedList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("SELECT distinct idSocio FROM cuotas where estado = 'A_Confirmar'");
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Socio s = new Socio();
					int idSocio = rs.getInt("idSocio");
					SocioLogic soclog = new SocioLogic();
					s.setIdSocio(idSocio);
					s = soclog.getOneById(s);
					losSocios.add(s);
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

		return losSocios;
	}

	/**
	 * obtiene una lista de todos para todos los socios que no tienen una cuota
	 * registrada este mes. Luego, iterara sobre esta lista e inserta las cuotas
	 * nuevas
	 */
	public void addCuotaForAll() {
		String sqlGetSocios = "SELECT idSocio FROM socio WHERE idSocio NOT IN (SELECT idSocio FROM cuotas WHERE MONTH(fechaDesde) = MONTH(CURDATE()) AND YEAR(fechaDesde) = YEAR(CURDATE()))";
		String sqlAddCuota = "INSERT INTO cuotas(idSocio, fechaPago, fechaDesde, fechaHasta) VALUES (?, NULL, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 1 MONTH))";

		try (Connection conn = DbConnector.getInstancia().getConn();
				PreparedStatement pstmtGetSocios = conn.prepareStatement(sqlGetSocios);
				ResultSet rs = pstmtGetSocios.executeQuery();
				PreparedStatement pstmtAddCuota = conn.prepareStatement(sqlAddCuota)) {

			while (rs.next()) {
				int idSocio = rs.getInt("idSocio");
				pstmtAddCuota.setInt(1, idSocio);
				pstmtAddCuota.executeUpdate();
			}

		} catch (SQLException e) {
			LoggerError.log(e.getStackTrace(), e.getMessage());
		}
	}

	public boolean checkIfSocioPaidCurrentCuota(Socio socio) {
		boolean socioPaid = true;
		String query = "SELECT * FROM cuotas c where idSocio = ? AND MONTH(c.fechaDesde) = MONTH(CURDATE()) AND YEAR(c.fechaDesde) = YEAR(CURDATE()) AND fechaPago is null";
		try (Connection conn = DbConnector.getInstancia().getConn();
				PreparedStatement pStatement = conn.prepareStatement(query)) {
			pStatement.setInt(1, socio.getIdSocio());
			ResultSet rs = pStatement.executeQuery();

			while (rs != null && rs.next()) {
				socioPaid = false;
			}

		} catch (SQLException e) {
			LoggerError.log(e.getStackTrace(), e.getMessage());
		}
		return socioPaid;
	}

	public List<Cuotas> getCuotasBySocioAndDate(Socio socio, java.sql.Date fechaDesde, java.sql.Date fechaHasta) {
		List<Cuotas> cuotas = new ArrayList<>();
		String query = "SELECT * FROM cuotas WHERE idSocio = ? AND fechaDesde >= ? AND fechaHasta <= ?";
		try (Connection conn = DbConnector.getInstancia().getConn();
				PreparedStatement pStatement = conn.prepareStatement(query)) {
			pStatement.setInt(1, socio.getIdSocio());
			pStatement.setDate(2, fechaDesde);
			pStatement.setDate(3, fechaHasta);
			ResultSet rs = pStatement.executeQuery();

			while (rs != null && rs.next()) {
				Cuotas cuota = new Cuotas();
				cuota.setIdCuota(rs.getInt("idcuotas"));
				cuota.setFechaDesde(rs.getDate("fechaDesde"));
				cuota.setFechaHasta(rs.getDate("fechaHasta"));
				cuota.setFechaPago(rs.getDate("fechaPago"));
				cuota.setEstado(rs.getString("estado"));
				cuotas.add(cuota);
			}

		} catch (SQLException e) {
			LoggerError.log(e.getStackTrace(), e.getMessage());
		}
		return cuotas;
	}

}
