package Logic;

import java.util.LinkedList;

import Data.DataCuotas;
import Entities.Cuotas;
import Entities.Socio;

public class CuotasLogic {

	private DataCuotas dataCuotas;

	public CuotasLogic() {
		dataCuotas = new DataCuotas();
	}

	public LinkedList<Cuotas> getAll() {
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

	public LinkedList<Cuotas> getCuotasByUser(Socio socio) {
		return dataCuotas.getCuotasByUser(socio);
	}

	/**
	 * Recupera las cuotas que no tengan el estado a confirmar y que su fecha de
	 * pago sea nula. O sea, las que ni fueron pagadas ni se hizo una peticion para
	 * pagarlas
	 * 
	 * @param socio que adeuda las cuotas
	 * @return cuotas adeudadas
	 */
	public LinkedList<Cuotas> getCuotasImpagasByUser(Socio socio) {
		LinkedList<Cuotas> cuotasImpagas = new LinkedList<>();
		// Estas cuotas estan impagas, pero pueden tener el estado "A_Confirmar",
		// entonces las filtramos.
		LinkedList<Cuotas> lasCuotas = dataCuotas.getCuotasImpagasByUser(socio);
		for (Cuotas cuo : lasCuotas) {
			if (!cuo.getEstado().equals("A_Confirmar")) {
				cuotasImpagas.add(cuo);
			}
		}
		return cuotasImpagas;
	}

	public LinkedList<Cuotas> getCuotasAConfirmarByUser(Socio socio) {
		return dataCuotas.getCuotasAConfirmarByUser(socio);
	}

	public LinkedList<Socio> getSocioAConfirmar() {
		return dataCuotas.getUsuariosAConfirmar();
	}

	public void addCuotaForAll() {
		dataCuotas.addCuotaForAll();
	}
}
