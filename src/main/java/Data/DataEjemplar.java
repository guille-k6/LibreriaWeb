package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import Entities.Autor;
import Entities.Ejemplar;
import Entities.EjemplarCantidad;
import Entities.Libro;
import Logic.LibroLogic;
import utils.LoggerError;

public class DataEjemplar {

	public LinkedList<Ejemplar> getAll() {
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<Ejemplar> ejemplares = new LinkedList<>();

		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select * from ejemplar");
			if (rs != null) {
				while (rs.next()) {
					Ejemplar e = new Ejemplar();
					e.setIdEjemplar(rs.getInt("idejemplar"));
					e.setDisponible(rs.getBoolean("disponible"));

					// Busco el objeto libro para el ejemplar
					int idLibro = rs.getInt("idLibro");
					LibroLogic liblog = new LibroLogic();
					Libro elLibro = new Libro();
					elLibro.setIdLibro(idLibro);
					elLibro = liblog.getOneById(elLibro);
					// Le agrego el libro
					e.setLibro(elLibro);
					// Añado el ejemplar con libro incluido a la LinkedList.
					ejemplares.add(e);
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
		return ejemplares;
	} // fin metodo GetAll

	public Ejemplar getById(Ejemplar ejemplarToSearch) {
		Ejemplar e = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("select * from ejemplar where idejemplar=?");
			stmt.setInt(1, ejemplarToSearch.getIdEjemplar());
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				e = new Ejemplar();
				e.setIdEjemplar(rs.getInt("idejemplar"));
				e.setDisponible(rs.getBoolean("disponible"));

				// Busco el objeto libro para el ejemplar
				int idLibro = rs.getInt("idLibro");
				LibroLogic liblog = new LibroLogic();
				Libro elLibro = new Libro();
				elLibro.setIdLibro(idLibro);
				elLibro = liblog.getOneById(elLibro);
				// Le agrego el libro
				e.setLibro(elLibro);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return e;
	} // Fin Metodo GetById

	public void add(Ejemplar ejemplar) {
		PreparedStatement stmt = null;
		ResultSet keyResultSet = null;
		// SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"insert into ejemplar(idLibro,disponible) values(?,?)", Statement.RETURN_GENERATED_KEYS);

			stmt.setInt(1, ejemplar.getLibro().getIdLibro());
			stmt.setBoolean(2, true); // falta test
			stmt.executeUpdate();

			keyResultSet = stmt.getGeneratedKeys();
			if (keyResultSet != null && keyResultSet.next()) {
				ejemplar.setIdEjemplar(keyResultSet.getInt(1));
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

	public void update(Ejemplar ejemplar) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("update ejemplar set idLibro=?, disponible=? where idejemplar=?");
			stmt.setInt(1, ejemplar.getLibro().getIdLibro());
			stmt.setBoolean(2, ejemplar.isDisponible()); // falta test
			stmt.setInt(3, ejemplar.getIdEjemplar());
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

	public void remove(Ejemplar ejemplar) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("delete from ejemplar where idejemplar=?");
			stmt.setInt(1, ejemplar.getIdEjemplar());
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

	public LinkedList<EjemplarCantidad> getAmountOfLibros() {
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<EjemplarCantidad> cantidadLibros = new LinkedList<>();

		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery(
					"SELECT l.idlibro, l.isbn, l.titulo, l.editorial, l.fechaEdicion, l.cantDiasMaxPrestamo, a.idautor, a.nombre, a.apellido, count(l.idlibro) as cantidad "
							+ "FROM Libro l " + "INNER JOIN Ejemplar e ON e.idLibro = l.idlibro "
							+ "INNER JOIN Autor a ON a.idautor = l.idautor "
							+ "WHERE e.disponible = 1 GROUP BY l.idLibro;");
			if (rs != null) {
				while (rs.next()) {
					Autor a = new Autor();
					a.setApellido(rs.getString("apellido"));
					a.setNombre(rs.getString("nombre"));
					a.setIdAutor(rs.getInt("idautor"));

					Libro l = new Libro();
					l.setIdLibro(rs.getInt("idlibro"));
					l.setIsbn(rs.getString("isbn"));
					l.setTitulo(rs.getString("titulo"));
					l.setEditorial(rs.getString("editorial"));
					l.setFechaEdicion(rs.getDate("fechaEdicion"));
					l.setCantDiasMaxPrestamo(rs.getInt("cantDiasMaxPrestamo"));
					l.setAutor(a);

					EjemplarCantidad ejemplarCantidad = new EjemplarCantidad(rs.getInt("cantidad"), l);
					cantidadLibros.add(ejemplarCantidad);
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
			} catch (Exception e) {
				LoggerError.log(e.getStackTrace(), e.getMessage());
			}
			DbConnector.getInstancia().releaseConn();
		}
		return cantidadLibros;
	}

	/**
	 * Devuelve posiblemente una lista con ids de ejemplares del libro buscado con
	 * hasta un maximo de cantidad
	 * 
	 * @param idLibro
	 * @param cantidad
	 * @return lista de ejemplares con el id buscado
	 */
	public List<Integer> getIdsOfEjemplares(int idLibro, int cantidad) {
		List<Integer> idEjemplares = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("SELECT * FROM Ejemplar e where e.idLibro = ? AND e.disponible = 1 LIMIT ?;");
			stmt.setInt(1, idLibro);
			stmt.setInt(2, cantidad);
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					idEjemplares.add(rs.getInt("idejemplar"));
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return idEjemplares;
	}

	public boolean updateEjemplaresToAlquilados(Set<Integer> ejemplaresIdList) {
		PreparedStatement stmt = null;
		ResultSet keyResultSet = null;
		StringBuffer sb = new StringBuffer();
		try {
			Connection conn = DbConnector.getInstancia().getConn();
			for (int i = 0; i < ejemplaresIdList.size(); i++) {
				if (i == ejemplaresIdList.size() - 1) {
					sb.append("?");
				} else {
					sb.append("?, ");
				}
			}
			stmt = conn.prepareStatement("UPDATE ejemplar SET disponible = 0 WHERE idejemplar IN (" + sb + ")");

			int index = 1;
			for (int idEjemplar : ejemplaresIdList) {
				stmt.setInt(index, idEjemplar);
				index++;
			}
			stmt.executeUpdate();
		} catch (SQLException e) {
			LoggerError.log(e.getStackTrace(), e.getMessage());
			return false;
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
		return true;
	}
}
