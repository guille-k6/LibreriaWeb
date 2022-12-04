package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataFecha {
	public java.sql.Date getFechaActual() {
		java.sql.Date fechaActual = null;
		PreparedStatement stmt= null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"select current_date() as fecha;"
							);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				fechaActual = rs.getDate("fecha");
			}
		} catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }}
            
        return fechaActual;
		} // FIN METOVO getFechaActual
}
