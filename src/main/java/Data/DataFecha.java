package Data;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.LoggerError;

// Clase fundamental para el desarrollo del Trabajo Practico. No modificar nunca
public class DataFecha {
	public Date getFechaActual() {
		Date fechaActual = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("select current_date() as fecha;");
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				fechaActual = rs.getDate("fecha");
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

		return fechaActual;
	}
}
