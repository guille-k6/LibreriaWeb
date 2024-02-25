package Entities;

public class LineaDePrestamo {
	private int idLineaPrestamo;
	private java.sql.Date fechaDevolucionTeorica;
	private java.sql.Date fechaDevolucionReal;
	private Ejemplar ejemplar;
	private int idPrestamo;

	public LineaDePrestamo() {
	}

	public LineaDePrestamo(int idLineaDePrestamo) {
		this.idLineaPrestamo = idLineaDePrestamo;
	}

	public LineaDePrestamo(int idLineaPrestamo, java.sql.Date fechaDevolucionTeorica, java.sql.Date fechaDevolucionReal,
			Ejemplar ejemplar) {
		this.idLineaPrestamo = idLineaPrestamo;
		this.fechaDevolucionReal = fechaDevolucionReal;
		this.fechaDevolucionTeorica = fechaDevolucionTeorica;
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

	public Ejemplar getEjemplar() {
		return ejemplar;
	}

	public void setEjemplar(Ejemplar ejemplar) {
		this.ejemplar = ejemplar;
	}

	public int getIdPrestamo() {
		return idPrestamo;
	}

	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}

}
