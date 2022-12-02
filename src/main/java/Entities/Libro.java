package Entities;

public class Libro {
	private int idLibro;
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
	private String isbn;
	private String titulo;
	private String editorial;
	private java.sql.Date fechaEdicion;
	private int cantDiasMaxPrestamo;
	private Autor autor;
	
}
