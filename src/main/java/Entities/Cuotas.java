package Entities;

public class Cuotas {
	private int idCuota;
	private java.sql.Date fechaPago;
	private java.sql.Date fechaDesde;
	private java.sql.Date fechaHasta;
	private Socio socio;
	private String estado;
	
	public int getIdCuota() {
		return idCuota;
	}
	public void setIdCuota(int idCuota) {
		this.idCuota = idCuota;
	}
	public java.sql.Date getFechaPago() {
		return this.fechaPago;
	}
	public void setFechaPago(java.sql.Date fecha) {
		this.fechaPago = fecha;
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
	public String getEstado() {
		return this.estado;
	}
	public void setEstado(String estado){
		this.estado = estado;
	}

}
