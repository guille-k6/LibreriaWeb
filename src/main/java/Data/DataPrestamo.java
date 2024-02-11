package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Optional;

import Entities.Autor;
import Entities.Ejemplar;
import Entities.Libro;
import Entities.LineaDePrestamo;
import Entities.Prestamo;
import Entities.Socio;
import Logic.SocioLogic;

public class DataPrestamo {

	public LinkedList<Prestamo> getAll() {
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<Prestamo> prestamos = new LinkedList<>();

		try {
			String query = "SELECT p.idPrestamo, p.fechaPrestamo, p.idSocio, ldp.idlineadeprestamo, ldp.fechaDevolucionTeorica, ldp.fechaDevolucionReal, ldp.estadoLinea, ldp.idEjemplar, s.idsocio, s.apellido, s.nombre, s.email, s.domicilio, s.telefono, s.estadoSocio, s.contrasenia, s.isAdmin, s.usuario, e.disponible, l.idLibro, l.isbn, l.titulo, l.editorial, l.fechaEdicion, l.cantDiasMaxPrestamo, a.idautor, a.nombre as NombreAutor, a.apellido as ApellidoAutor FROM prestamo p INNER JOIN lineadeprestamo ldp ON p.idprestamo = ldp.idPrestamo INNER JOIN socio s ON s.idsocio = p.idSocio INNER JOIN ejemplar e ON ldp.idEjemplar = e.idejemplar INNER JOIN libro l ON e.idLibro = l.idlibro INNER JOIN autor a ON l.idAutor = a.idautor ORDER BY p.idprestamo";
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery(query);
			if (rs != null) {
				int idAnterior = -1;
				Prestamo p = new Prestamo();
				LinkedList<LineaDePrestamo> lineasDePrestamo = new LinkedList<>();
				while (rs.next()) {
					if (idAnterior != rs.getInt("idprestamo")) {
						if (idAnterior != -1) { // No en la primera pasada
							p.setLineasDePrestamo(lineasDePrestamo);
							prestamos.add(p);
						}
						idAnterior = rs.getInt("idprestamo");
						lineasDePrestamo.clear();
						p = new Prestamo();
						p.setIdPrestamo(rs.getInt("idprestamo"));
						p.setFechaPrestamo(rs.getDate("fechaPrestamo"));
						p.setSocio(new Socio(rs.getInt("idsocio"), rs.getString("apellido"), rs.getString("nombre"),
								rs.getString("email"), rs.getString("domicilio"), rs.getString("telefono"),
								rs.getString("estadoSocio"), rs.getString("usuario")));

						Autor autor = new Autor(rs.getInt("idautor"), rs.getString("NombreAutor"),
								rs.getString("ApellidoAutor"));
						Libro libro = new Libro(rs.getInt("idLibro"), rs.getString("isbn"), rs.getString("titulo"),
								rs.getString("editorial"), rs.getDate("fechaEdicion"), rs.getInt("cantDiasMaxPrestamo"),
								autor);
						Ejemplar ejemplar = new Ejemplar(rs.getInt("idejemplar"), rs.getBoolean("disponible"), libro);
						LineaDePrestamo ldp = new LineaDePrestamo(rs.getInt("idlineadeprestamo"),
								rs.getDate("fechaDevolucionTeorica"), rs.getDate("fechaDevolucionReal"),
								rs.getString("estadoLinea"), ejemplar);
						lineasDePrestamo.add(ldp);
					} else { // Ids de prestamo iguales (es otra linea del mismo prestmo)
						Autor autor = new Autor(rs.getInt("idautor"), rs.getString("NombreAutor"),
								rs.getString("ApellidoAutor"));
						Libro libro = new Libro(rs.getInt("idLibro"), rs.getString("isbn"), rs.getString("titulo"),
								rs.getString("editorial"), rs.getDate("fechaEdicion"), rs.getInt("cantDiasMaxPrestamo"),
								autor);
						Ejemplar ejemplar = new Ejemplar(rs.getInt("idejemplar"), rs.getBoolean("disponible"), libro);
						LineaDePrestamo ldp = new LineaDePrestamo(rs.getInt("idlineadeprestamo"),
								rs.getDate("fechaDevolucionTeorica"), rs.getDate("fechaDevolucionReal"),
								rs.getString("estadoLinea"), ejemplar);
						lineasDePrestamo.add(ldp);
					}
					return prestamos;
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

		return prestamos;
	} // fin metodo GetAll

	public Prestamo getOneById(Prestamo prestamoToSearch) {
		Prestamo p = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("SELECT * FROM prestamo WHERE idprestamo=?");
			stmt.setInt(1, prestamoToSearch.getIdPrestamo());
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				p = new Prestamo();
				p.setIdPrestamo(rs.getInt("idprestamo"));
				p.setFechaPrestamo(rs.getDate("fechaPrestamo"));

				// Busco el objeto socio para el préstamo
				int idSocio = rs.getInt("idSocio");
				SocioLogic sociolog = new SocioLogic();
				Socio elSocio = new Socio();
				elSocio.setIdSocio(idSocio);
				elSocio = sociolog.getOneById(elSocio);

				// Le asigno el socio al préstamo
				p.setSocio(elSocio);
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

	public Optional<Integer> add(Prestamo prestamo) {
		PreparedStatement stmt = null;
		ResultSet keyResultSet = null;
		Optional<Integer> generatedId = Optional.empty();
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"INSERT INTO prestamo(fechaPrestamo, idSocio) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
			stmt.setDate(1, prestamo.getFechaPrestamo());
			stmt.setInt(2, prestamo.getSocio().getIdSocio());
			stmt.executeUpdate();

			keyResultSet = stmt.getGeneratedKeys();
			if (keyResultSet != null && keyResultSet.next()) {
				generatedId = Optional.of(keyResultSet.getInt(1));
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

		return generatedId;

	} // FIN METODO ADD

	public void update(Prestamo prestamo) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("update prestamo set fechaPrestamo=?, idSocio=? where idprestamo=?");
			stmt.setString(1, prestamo.getFechaPrestamo().toString()); // OJO ACA (MIRAR COMO MAPEA)
			stmt.setInt(2, prestamo.getSocio().getIdSocio());
			stmt.setInt(3, prestamo.getIdPrestamo());
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

	public void remove(Prestamo prestamo) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("delete from prestamo where idprestamo=?");
			stmt.setInt(1, prestamo.getIdPrestamo());
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
	}

	public int getCantidadLibrosPrestadosBySocio(Socio socio) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int librosPrestados = 0;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("select p.idprestamo, p.idsocio, ldp.fechaDevolucionReal  from prestamo p "
							+ "inner join lineadeprestamo ldp on ldp.idprestamo = p.idprestamo "
							+ "where fechaDevolucionReal = null and p.idsocio = ?");
			stmt.setInt(1, socio.getIdSocio());
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				librosPrestados++;
			}
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
		return librosPrestados;
	}

}
