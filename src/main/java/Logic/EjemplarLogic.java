package Logic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

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

	public void update(Ejemplar ejemplar) throws Exception {
		dataEjemplar.update(ejemplar);
	}

	public void remove(Ejemplar ejemplar) throws Exception {
		dataEjemplar.remove(ejemplar);
	}

	public LinkedList<EjemplarCantidad> getAmountOfLibros() {
		return dataEjemplar.getAmountOfLibros();
	}

	public List<Ejemplar> getAllEjemplaresThatMatch(String matching) {
		return dataEjemplar.getAllEjemplaresThatMatch(matching);
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
	 * @return El set de id de ejemplares actualizacion fue exitosa, empty si hubo
	 *         un error
	 */
	public Optional<Set<Integer>> updateEjemplaresAvailables(Map<String, String> idLibroCantidad) {
		Set<Integer> idEjemplaresActualizar = new HashSet<>();
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
				return Optional.empty();
			}
		}
		if (updateEjemplaresToAlquilados(idEjemplaresActualizar)) {
			return Optional.of(idEjemplaresActualizar);
		} else {
			return Optional.empty();
		}
	}

	public List<Integer> getIdsOfEjemplares(int idLibro, int cantidad) {
		return dataEjemplar.getIdsOfEjemplares(idLibro, cantidad);
	}

	public boolean updateEjemplaresToAlquilados(Set<Integer> idList) {
		return dataEjemplar.updateEjemplaresToAlquilados(idList);
	}

	/**
	 * Recibe un set de ids de ejemplares y retorna una lista de ejemplares con
	 * todos los datos
	 * 
	 * @param ejemplaresActualizados set ids de ejemplares
	 * @return lista de ejemplares con todos los datos cargados
	 */
	public List<Ejemplar> fillEjemplaresById(Set<Integer> ejemplaresActualizados) {
		List<Ejemplar> ejemplaresCompletos = new ArrayList<>();
		for (int ejemplarId : ejemplaresActualizados) {
			// TODO: Implementar un método que busque todos los ejemplares
			// y no tirar una query dentro de un for
			ejemplaresCompletos.add(getOneById(new Ejemplar(ejemplarId)));
		}
		return ejemplaresCompletos;
	}

}
