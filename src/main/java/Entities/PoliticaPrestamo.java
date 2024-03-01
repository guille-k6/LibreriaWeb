package Entities;

public class PoliticaPrestamo {
	private int idPoliticaPrestamo;
	private java.sql.Date fechaDesde;
	private int cantMaxLibrosPend;

	public PoliticaPrestamo(int idPoliticaPrestamo) {
		this.idPoliticaPrestamo = idPoliticaPrestamo;
	}

	public int getIdPoliticaPrestamo() {
		return idPoliticaPrestamo;
	}

	public void setIdPoliticaPrestamo(int idPoliticaPrestamo) {
		this.idPoliticaPrestamo = idPoliticaPrestamo;
	}

	public java.sql.Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(java.sql.Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public int getCantMaxLibrosPend() {
		return cantMaxLibrosPend;
	}

	public void setCantMaxLibrosPend(int cantMaxLibrosPend) {
		this.cantMaxLibrosPend = cantMaxLibrosPend;
	}

	public PoliticaPrestamo() {
	}

	public PoliticaPrestamo(int idPoliticaPrestamo, java.sql.Date fechaDesde, int cantMaxLibrosPend) {
		this.idPoliticaPrestamo = idPoliticaPrestamo;
		this.fechaDesde = fechaDesde;
		this.cantMaxLibrosPend = cantMaxLibrosPend;
	}
}
