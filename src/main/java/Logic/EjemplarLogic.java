package Logic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Data.DataEjemplar;
import Entities.Ejemplar;
import Entities.EjemplarCantidad;
import Entities.Libro;

public class EjemplarLogic {
	private DataEjemplar dataEjemplar;

	public EjemplarLogic() {
		dataEjemplar = new DataEjemplar();
	}

	public LinkedList<Ejemplar> getAll() {
		return dataEjemplar.getAll();
	}

	public Ejemplar getOneById(Ejemplar ejemplar) {
		return dataEjemplar.getById(ejemplar);
	}

	public void add(Ejemplar ejemplar) {
		dataEjemplar.add(ejemplar);
	}

	public void update(Ejemplar ejemplar) {
		dataEjemplar.update(ejemplar);
	}

	public void remove(Ejemplar ejemplar) {
		dataEjemplar.remove(ejemplar);
	}

	public LinkedList<EjemplarCantidad> getAmountOfLibros() {
		return dataEjemplar.getAmountOfLibros();
	}

	public LinkedList<Ejemplar> getAllEjemplaresByLibro(Libro libro) {
		// Dado un libro devuelve los ejemplares de ese libro
		LinkedList<Ejemplar> listaLibros = dataEjemplar.getAll();
		LinkedList<Ejemplar> listaFiltrada = new LinkedList<>();
		for (Ejemplar ejemplar : listaLibros) {
			if (ejemplar.getLibro().getIdLibro() == libro.getIdLibro()) {
				listaFiltrada.add(ejemplar);
			}
		}
		return listaFiltrada;
	}

	/**
	 * Dado un mapa de idLibros - cantidad retorna actualiza cantidad de ejemplares
	 * para ese libro disponibles
	 * 
	 * @param idLibroCantidad
	 * @return true si la actualizacion fue exitosa, false si hubo un error
	 */
	public boolean updateEjemplaresAvailables(Map<String, String> idLibroCantidad) {
		ArrayList<Integer> idEjemplaresActualizar = new ArrayList<>();
		for (String idLibroString : idLibroCantidad.keySet()) {
			int idLibro = Integer.parseInt(idLibroString);
			int cantidad = Integer.parseInt(idLibroCantidad.get(idLibroString));
			List<Integer> idEjemplaresLibro = getIdsOfEjemplares(idLibro, cantidad);
			// Si me trae la cantidad de ejemplares que le pedí los agrego, sino retorno
			// false ya que no habria la cantidad de ejemplares disponibles que necesito
			// para el libro buscado
			if (idEjemplaresLibro.size() == cantidad) {
				idEjemplaresActualizar.addAll(idEjemplaresLibro);
			} else {
				return false;
			}
		}
		return updateEjemplaresToAlquilados(idEjemplaresActualizar);
	}

	public List<Integer> getIdsOfEjemplares(int idLibro, int cantidad) {
		return dataEjemplar.getIdsOfEjemplares(idLibro, cantidad);
	}

	public boolean updateEjemplaresToAlquilados(List<Integer> idList) {
		return dataEjemplar.updateEjemplaresToAlquilados(idList);
	}

}
