package Logic;

import Data.DataLineaDePrestamo;
import Entities.LineaDePrestamo;
import Entities.Prestamo;

public class LineaDePrestamoLogic {
	private DataLineaDePrestamo dataLineaDePrestamo;

	public LineaDePrestamoLogic(){
		dataLineaDePrestamo = new DataLineaDePrestamo();
	}
	public Prestamo getAllByPrestamo(Prestamo prestamo){
		return dataLineaDePrestamo.getAllByPrestamo(prestamo);
	}
	public LineaDePrestamo getById(LineaDePrestamo lineaToSearch) {
		return dataLineaDePrestamo.getById(lineaToSearch);
	}
	public void addAll(Prestamo prestamo)  {
		dataLineaDePrestamo.addAll(prestamo);
	}
	public void addOne(Prestamo prestamo,LineaDePrestamo linea)  {
		dataLineaDePrestamo.addOne( prestamo,linea);
	}
	public void update(LineaDePrestamo linea,Prestamo pres) {
		dataLineaDePrestamo.update(linea,pres);
	}
	public void remove(LineaDePrestamo linea) {
		dataLineaDePrestamo.remove(linea);
	}

}
