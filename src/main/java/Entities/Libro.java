package Entities;

public class Libro {
	private int idLibro;
	private String isbn;
	private String titulo;
	private String editorial;
	private java.sql.Date fechaEdicion;
	private int cantDiasMaxPrestamo;
	private Autor autor;
	
	public Libro() {};
	public Libro(int idLibro,
				 String isbn,
				 String titulo,
				 String editorial,
				 java.sql.Date fechaEdicion,
				 int cantDiasMaxPrestamo,
				 Autor autor) {
		this.idLibro = idLibro;
		this.isbn = isbn;
		this.titulo = titulo;
		this.editorial = editorial;
		this.fechaEdicion = fechaEdicion;
		this.cantDiasMaxPrestamo = cantDiasMaxPrestamo;
		this.autor = autor;
	}
	
	public int getIdLibro() {
		return idLibro;
	}
	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getEditorial() {
		return editorial;
	}
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	public java.sql.Date getFechaEdicion() {
		return fechaEdicion;
	}
	public void setFechaEdicion(java.sql.Date fechaEdicion) {
		this.fechaEdicion = fechaEdicion;
	}
	public int getCantDiasMaxPrestamo() {
		return cantDiasMaxPrestamo;
	}
	public void setCantDiasMaxPrestamo(int cantDiasMaxPrestamo) {
		this.cantDiasMaxPrestamo = cantDiasMaxPrestamo;
	}
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
}
