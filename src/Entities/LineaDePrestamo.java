package Entities;

public class LineaDePrestamo {
	private int idLineaPrestamo;
	private String fechaDevolucionTeorica;
	private String fechaDevolucionReal;
	private String estadoLinea;
	private Ejemplar ejemplar;
	public int getIdLineaPrestamo() {
		return idLineaPrestamo;
	}
	public void setIdLineaPrestamo(int idLineaPrestamo) {
		this.idLineaPrestamo = idLineaPrestamo;
	}
	public String getFechaDevolucionTeorica() {
		return fechaDevolucionTeorica;
	}
	public void setFechaDevolucionTeorica(String fechaDevolucionTeorica) {
		this.fechaDevolucionTeorica = fechaDevolucionTeorica;
	}
	public String getFechaDevolucionReal() {
		return fechaDevolucionReal;
	}
	public void setFechaDevolucionReal(String fechaDevolucionReal) {
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
