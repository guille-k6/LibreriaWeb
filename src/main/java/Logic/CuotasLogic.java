package Logic;

import java.util.LinkedList;

import Data.DataCuotas;
import Entities.Cuotas;
import Entities.Socio;

public class CuotasLogic {

	private DataCuotas dataCuotas;

	public CuotasLogic(){
		dataCuotas = new DataCuotas();
	}
	public LinkedList<Cuotas> getAll(){
		return dataCuotas.getAll();
	}
	public Cuotas getOneById(Cuotas cuota) {
		return dataCuotas.getById(cuota);
	}
	public void add(Cuotas cuota) {
		dataCuotas.add(cuota);
	}
	public void update(Cuotas cuota) {
		dataCuotas.update(cuota);
	}
	public void remove(Cuotas cuota) {
		dataCuotas.remove(cuota);
	}
	public LinkedList<Cuotas> getCuotasByUser(Socio socio){
		return dataCuotas.getCuotasByUser(socio);
	}

	public LinkedList<Cuotas> getCuotasImpagasByUser(Socio socio){
		// Estas cuotas son las que est�n impagas y no tienen el estado "A_Confirmar".
		LinkedList<Cuotas> cuotasNoPendientes = new LinkedList<>();
		// Estas cuotas estan impagas, pero pueden tener el estado "A_Confirmar".
		LinkedList<Cuotas> lasCuotas = dataCuotas.getCuotasImpagasByUser(socio);

		for(Cuotas cuo : lasCuotas) {
			if(!cuo.getEstado().equals("A_Confirmar")) {
				cuotasNoPendientes.add(cuo);
			}
		}

		return cuotasNoPendientes;
	}
	public LinkedList<Cuotas> getCuotasAConfirmarByUser(Socio socio){
		return dataCuotas.getCuotasAConfirmarByUser(socio);
	}
	public LinkedList<Socio> getSocioAConfirmar(){
		return dataCuotas.getUsuariosAConfirmar();
	}
}
