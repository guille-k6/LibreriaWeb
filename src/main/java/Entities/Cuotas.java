package Entities;

import Data.DataFecha;

public class Cuotas {
	private int idCuota;
	private java.sql.Date fechaPago;
	private java.sql.Date fechaDesde;
	private java.sql.Date fechaHasta;
	private Socio socio;
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
	
	public String getEstado() { // es una variable calculada
		String respuesta = null;
		DataFecha dataFecha = new DataFecha();
		int valor = this.getFechaHasta().compareTo(dataFecha.getFechaActual());	
		
		if(valor<0) {
			respuesta="Cuota vencida";
		}else {
			respuesta="Pendiente";
		}
		return respuesta;
	}

}
