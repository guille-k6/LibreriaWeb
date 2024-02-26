
package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Entities.ValorCuotas;
import utils.LoggerError;

public class DataValorCuotas {

	public LinkedList<ValorCuotas> getAll() {
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<ValorCuotas> valoresCuotas = new LinkedList<>();

		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select * from valorcuotas");
			if (rs != null) {
				while (rs.next()) {
					ValorCuotas v = new ValorCuotas();
					v.setFechaDesde(rs.getDate("fechaDesde"));
					v.setValor(rs.getDouble("valorcuotascol"));

					valoresCuotas.add(v);
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
		return valoresCuotas;
	} // fin metodo GetAll

	public ValorCuotas getById(ValorCuotas valorCuotasToSearch) {
		ValorCuotas v = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("select * from valorcuotas where fechaDesde=?");
			stmt.setString(1, valorCuotasToSearch.getFechaDesde().toString());
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				v = new ValorCuotas();
				v.setFechaDesde(rs.getDate("fechaDesde"));
				v.setValor(rs.getDouble("valorcuotascol"));
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

		return v;
	} // Fin Metodo GetById

	public void add(ValorCuotas valorCuotas) {
		PreparedStatement stmt = null;
		ResultSet keyResultSet = null;
		// SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"insert into valorescuotas(fechaDesde,valorcuotascol) values(?,?)",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, valorCuotas.getFechaDesde().toString());
			stmt.setDouble(2, valorCuotas.getValor());
			stmt.executeUpdate();
			// no se si funciona y si es necesario recuperar la fecha que acabas de ingresar
			keyResultSet = stmt.getGeneratedKeys();
			if (keyResultSet != null && keyResultSet.next()) {
				valorCuotas.setFechaDesde(keyResultSet.getDate(1));
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

	public void update(ValorCuotas valorCuotas) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("update valorcuotas set valorcuotascol=? where fechaDesde=?");
			stmt.setDouble(1, valorCuotas.getValor());
			stmt.setString(2, valorCuotas.getFechaDesde().toString());

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

	public void remove(ValorCuotas valorCuotas) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("delete from valorcuotas where fechaDesde=?");
			stmt.setString(1, valorCuotas.getFechaDesde().toString());
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

	public double getValorActual() {
		double valor = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT fechaDesde, valorcuotascol FROM valorcuotas v inner join (SELECT max(fechaDesde) maxifecha FROM valorcuotas) fe on fe.maxifecha = v.fechaDesde");
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				valor = rs.getDouble("valorcuotascol");
			}
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

		return valor;
	} // FIN METOVO getValorActual

}
