package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

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
	} // fin metodo GetAll

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
	} // Fin Metodo GetById

	public void add(Libro libro) {
		PreparedStatement stmt = null;
		ResultSet keyResultSet = null;
		// SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
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

	public void update(Libro libro) {
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

	public void remove(Libro libro) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("delete from libro where idlibro=?");
			stmt.setInt(1, libro.getIdLibro());
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
