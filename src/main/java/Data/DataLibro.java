package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Entities.Autor;
import Entities.Libro;
import Logic.AutorLogic;
import utils.LoggerError;

public class DataLibro {

	public LinkedList<Libro> getAll() {
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<Libro> libros = new LinkedList<>();
		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select * from libro");
			if (rs != null) {
				while (rs.next()) {
					Libro l = new Libro();
					l.setIdLibro(rs.getInt("idlibro"));
					l.setIsbn(rs.getString("isbn"));
					l.setTitulo(rs.getString("titulo"));
					l.setEditorial(rs.getString("editorial"));
					l.setFechaEdicion(rs.getDate("fechaEdicion"));
					l.setCantDiasMaxPrestamo(rs.getInt("cantDiasMaxPrestamo"));

					// Busco el objeto autor para el libro
					int idAutor = rs.getInt("idAutor");
					AutorLogic autlog = new AutorLogic();
					Autor elAutor = new Autor();
					elAutor.setIdAutor(idAutor);
					elAutor = autlog.getOneById(elAutor);
					// Le agrego el autor
					l.setAutor(elAutor);
					// A�ado el libro con autor incluido a la LinkedList.
					libros.add(l);
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
		return libros;
	}

	public Libro getById(Libro libroToSearch) {
		Libro l = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("select * from libro where idlibro=?");
			stmt.setInt(1, libroToSearch.getIdLibro());
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				l = new Libro();
				l.setIdLibro(rs.getInt("idlibro"));
				l.setIsbn(rs.getString("isbn"));
				l.setTitulo(rs.getString("titulo"));
				l.setEditorial(rs.getString("editorial"));
				l.setFechaEdicion(rs.getDate("fechaEdicion"));
				l.setCantDiasMaxPrestamo(rs.getInt("cantDiasMaxPrestamo"));
				// Busco el objeto autor para el libro
				int idAutor = rs.getInt("idAutor");
				AutorLogic autlog = new AutorLogic();
				Autor elAutor = new Autor();
				elAutor.setIdAutor(idAutor);
				elAutor = autlog.getOneById(elAutor);
				// Le agrego el autor
				l.setAutor(elAutor);
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

	public void add(Libro libro) throws Exception {
		PreparedStatement stmt = null;
		ResultSet keyResultSet = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"insert into libro(isbn, titulo, editorial, fechaEdicion, cantDiasMaxPrestamo, idAutor) values(?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, libro.getIsbn());
			stmt.setString(2, libro.getTitulo());
			stmt.setString(3, libro.getEditorial());
			stmt.setString(4, libro.getFechaEdicion().toString()); // OJO ACA (MIRAR COMO MAPEA)
			stmt.setInt(5, libro.getCantDiasMaxPrestamo());
			stmt.setInt(6, libro.getAutor().getIdAutor());
			stmt.executeUpdate();

			keyResultSet = stmt.getGeneratedKeys();
			if (keyResultSet != null && keyResultSet.next()) {
				libro.setIdLibro(keyResultSet.getInt(1));
			}

		} catch (SQLException e) {
			LoggerError.log(e.getStackTrace(), e.getMessage());
			throw new Exception(e);
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

	public void update(Libro libro) throws Exception {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"update libro set isbn=?, titulo=?, editorial=?, fechaEdicion=?, cantDiasMaxPrestamo=?, idAutor=? where idlibro=?");
			stmt.setString(1, libro.getIsbn());
			stmt.setString(2, libro.getTitulo());
			stmt.setString(3, libro.getEditorial());
			stmt.setString(4, libro.getFechaEdicion().toString()); // OJO ACA (MIRAR COMO MAPEA)
			stmt.setInt(5, libro.getCantDiasMaxPrestamo());
			stmt.setInt(6, libro.getAutor().getIdAutor());
			stmt.setInt(7, libro.getIdLibro());
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

	public void remove(Libro libro) throws Exception {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("delete from libro where idlibro=?");
			stmt.setInt(1, libro.getIdLibro());
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

	public List<Libro> getAllLibrosThatMatch(String matching) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Libro> libros = new ArrayList<>();
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT idlibro, isbn, titulo, editorial, fechaEdicion, cantDiasMaxPrestamo, a.idAutor, a.nombre, a.apellido FROM libro INNER JOIN autor a ON libro.idAutor = a.idautor WHERE titulo LIKE ? OR a.nombre LIKE ? OR a.apellido LIKE ? OR concat(a.nombre, ' ', a.apellido) LIKE ?;");
			String likeMatcher = "%" + matching + "%";
			stmt.setString(1, likeMatcher);
			stmt.setString(2, likeMatcher);
			stmt.setString(3, likeMatcher);
			stmt.setString(4, likeMatcher);
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Autor a = new Autor();
					a.setNombre(rs.getString("a.nombre"));
					a.setApellido(rs.getString("a.apellido"));
					Libro l = new Libro();
					l.setIdLibro(rs.getInt("idLibro"));
					l.setTitulo(rs.getString("titulo"));
					l.setIsbn(rs.getString("isbn"));
					l.setEditorial(rs.getString("editorial"));
					l.setFechaEdicion(rs.getDate("fechaEdicion"));
					l.setCantDiasMaxPrestamo(rs.getInt("cantDiasMaxPrestamo"));
					l.setAutor(a);
					libros.add(l);
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
		return libros;
	}

	public List<Libro> getAllLibrosDisponibles() {
		Statement stmt = null;
		ResultSet rs = null;
		List<Libro> libros = new LinkedList<>();
		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery(
					"select *, count(e.idEjemplar) as cantidad from ejemplar e inner join libro l on l.idlibro = e.idLibro where e.disponible = 1 group by l.idLibro having cantidad > 0;");
			if (rs != null) {
				while (rs.next()) {
					Libro l = new Libro();
					l.setIdLibro(rs.getInt("idlibro"));
					l.setIsbn(rs.getString("isbn"));
					l.setTitulo(rs.getString("titulo"));
					l.setEditorial(rs.getString("editorial"));
					l.setFechaEdicion(rs.getDate("fechaEdicion"));
					l.setCantDiasMaxPrestamo(rs.getInt("cantDiasMaxPrestamo"));

					int idAutor = rs.getInt("idAutor");
					AutorLogic autlog = new AutorLogic();
					Autor elAutor = autlog.getOneById(new Autor(idAutor));
					l.setAutor(elAutor);
					libros.add(l);
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
		return libros;
	}

	public List<Libro> getAllLibrosDisponiblesThatMatch(String input) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Libro> libros = new ArrayList<>();
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"select *, count(e.idejemplar) as cantidad from ejemplar e inner join libro l on l.idlibro = e.idLibro inner join autor a on l.idAutor = a.idautor where e.disponible = 1 and l.titulo LIKE ? OR a.nombre LIKE ? OR a.apellido LIKE ? OR concat(a.nombre, ' ', a.apellido) LIKE ? group by l.idLibro having cantidad > 0;");
			String likeMatcher = "%" + input + "%";
			stmt.setString(1, likeMatcher);
			stmt.setString(2, likeMatcher);
			stmt.setString(3, likeMatcher);
			stmt.setString(4, likeMatcher);
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Autor a = new Autor();
					a.setNombre(rs.getString("nombre"));
					a.setApellido(rs.getString("apellido"));
					Libro l = new Libro();
					l.setIdLibro(rs.getInt("idLibro"));
					l.setTitulo(rs.getString("titulo"));
					l.setIsbn(rs.getString("isbn"));
					l.setEditorial(rs.getString("editorial"));
					l.setFechaEdicion(rs.getDate("fechaEdicion"));
					l.setCantDiasMaxPrestamo(rs.getInt("cantDiasMaxPrestamo"));
					l.setAutor(a);
					libros.add(l);
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
		return libros;
	}

}
