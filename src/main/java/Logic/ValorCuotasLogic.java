package Logic;

import java.util.LinkedList;
import Data.DataValorCuotas;
import Entities.ValorCuotas;

public class ValorCuotasLogic {
	private DataValorCuotas dataValorCuotas;
	
	public ValorCuotasLogic(){
		dataValorCuotas = new DataValorCuotas();
	}
	public LinkedList<ValorCuotas> getAll(){
		return dataValorCuotas.getAll();
	}
	public ValorCuotas getOneById(ValorCuotas valorCuota) {
		return dataValorCuotas.getById(valorCuota);
	}
	public void add(ValorCuotas valorCuota) {
		dataValorCuotas.add(valorCuota);
	}
	public void update(ValorCuotas valorCuota) {
		dataValorCuotas.update(valorCuota);
	}
	public void remove(ValorCuotas valorCuota) {
		dataValorCuotas.remove(valorCuota);
	}
	
	public double getValorActual() {
		return dataValorCuotas.getValorActual();
	}
}
