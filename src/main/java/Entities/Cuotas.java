package Entities;

public class Cuotas {
	private int idCuota;
	private String estado;
	private java.sql.Date fechaDesde;
	private java.sql.Date fechaHasta;
	private Socio socio;
	public int getIdCuota() {
		return idCuota;
	}
	public void setIdCuota(int idCuota) {
		this.idCuota = idCuota;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public java.sql.Date getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(java.sql.Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public java.sql.Date getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(java.sql.Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public Socio getSocio() {
		return socio;
	}
	public void setSocio(Socio socio) {
		this.socio = socio;
	}

}
