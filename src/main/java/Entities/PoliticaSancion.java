package Entities;

public class PoliticaSancion {
	private int idPoliticaSancion;
	private int diasDeAtrasoDesde;
	private int diasDeAtrasoHasta;
	public int getIdPoliticaSancion() {
		return idPoliticaSancion;
	}
	public void setIdPoliticaSancion(int idPoliticaSancion) {
		this.idPoliticaSancion = idPoliticaSancion;
	}
	public int getDiasDeAtrasoDesde() {
		return diasDeAtrasoDesde;
	}
	public void setDiasDeAtrasoDesde(int diasDeAtrasoDesde) {
		this.diasDeAtrasoDesde = diasDeAtrasoDesde;
	}
	public int getDiasDeAtrasoHasta() {
		return diasDeAtrasoHasta;
	}
	public void setDiasDeAtrasoHasta(int diasDeAtrasoHasta) {
		this.diasDeAtrasoHasta = diasDeAtrasoHasta;
	}

}
