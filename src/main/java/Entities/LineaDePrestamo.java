package Entities;

public class LineaDePrestamo {
	private int idLineaPrestamo;
	private java.sql.Date fechaDevolucionTeorica;
	private java.sql.Date fechaDevolucionReal;
	private String estadoLinea;
	private Ejemplar ejemplar;
	
	public LineaDePrestamo() {};
	public LineaDePrestamo(int idLineaPrestamo,
						   java.sql.Date fechaDevolucionTeorica,
						   java.sql.Date fechaDevolucionReal,
						   String estadoLinea,
						   Ejemplar ejemplar) {
		this.idLineaPrestamo = idLineaPrestamo;
		this.fechaDevolucionReal = fechaDevolucionReal;
		this.fechaDevolucionTeorica = fechaDevolucionTeorica;
		this.estadoLinea = estadoLinea;
		this.ejemplar = ejemplar;
	}
	
	public int getIdLineaPrestamo() {
		return idLineaPrestamo;
	}
	public void setIdLineaPrestamo(int idLineaPrestamo) {
		this.idLineaPrestamo = idLineaPrestamo;
	}
	public java.sql.Date getFechaDevolucionTeorica() {
		return fechaDevolucionTeorica;
	}
	public void setFechaDevolucionTeorica(java.sql.Date fechaDevolucionTeorica) {
		this.fechaDevolucionTeorica = fechaDevolucionTeorica;
	}
	public java.sql.Date getFechaDevolucionReal() {
		return fechaDevolucionReal;
	}
	public void setFechaDevolucionReal(java.sql.Date fechaDevolucionReal) {
		this.fechaDevolucionReal = fechaDevolucionReal;
	}
	public String getEstadoLinea() {
		return estadoLinea;
	}
	public void setEstadoLinea(String estadoLinea) {
		this.estadoLinea = estadoLinea;
	}
	public Ejemplar getEjemplar() {
		return ejemplar;
	}
	public void setEjemplar(Ejemplar ejemplar) {
		this.ejemplar = ejemplar;
	}

}
