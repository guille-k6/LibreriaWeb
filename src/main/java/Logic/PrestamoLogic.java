package Logic;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import Data.DataPrestamo;
import Entities.LineaDePrestamo;
import Entities.Prestamo;
import Entities.Socio;

public class PrestamoLogic {
	private DataPrestamo dataPrestamo;
	private LineaDePrestamoLogic liLogic;

	public PrestamoLogic() {
		dataPrestamo = new DataPrestamo();
		liLogic = new LineaDePrestamoLogic();
	}

	public List<Prestamo> getAll() {
		return dataPrestamo.getAll();
	}

	/**
	 * Busca un prestamo y luego todas sus lineas de prestamo
	 * 
	 * @param prestamo
	 * @return un prestamo con todas sus lineas de prestamo
	 */
	public Prestamo getOneById(Prestamo prestamo) {
		return liLogic.getAllByPrestamo(dataPrestamo.getOneById(prestamo));
	}

	public Optional<Integer> add(Prestamo prestamo) {
		Optional<Integer> idPrestamo = dataPrestamo.add(prestamo);
		// liLogic.addAll(prestamo);
		return idPrestamo;
	}

	public void update(Prestamo prestamo) {
		dataPrestamo.update(prestamo);
	}

	public void remove(Prestamo prestamo) {
		// Antes de borrar un prestamo borro todas sus lineas de prestamo
		Prestamo prestamoCargado = this.getOneById(prestamo); // Primero se cargan las lineas al prestamo para borrarlas
		LinkedList<LineaDePrestamo> lineas = new LinkedList<>();
		lineas = prestamoCargado.getLineasDePrestamo();
		for (LineaDePrestamo lineaDePrestamo : lineas) {
			liLogic.remove(lineaDePrestamo);
		}
		dataPrestamo.remove(prestamo);
	}

	public Optional<String> generatePrestamo(Socio socioDeudor, Map<String, String> librosCantidades) {
		Optional<String> mensaje = null;
		EjemplarLogic ejLogic = new EjemplarLogic();
		// Seteo los ejemplares como no disponibles
		Optional<Set<Integer>> ejemplaresActualizados = ejLogic.updateEjemplaresAvailables(librosCantidades);
		if (ejemplaresActualizados.isPresent()) {
			// Creo un prestamo
			Prestamo prestamo = new Prestamo(socioDeudor);
			Optional<Integer> idPrestamoCreado = add(prestamo);
			if (idPrestamoCreado.isEmpty()) {
				mensaje = Optional.of("No fue posible crear el prestamo");
				return mensaje;
			}
			liLogic.createLineasDePrestamoByIdPrestamo(idPrestamoCreado.get(), ejemplaresActualizados.get());
			mensaje = Optional.of("Prestamo generado con id = " + idPrestamoCreado.get());
		} else {
			mensaje = Optional.of("No hay suficientes ejemplares disponibles");
		}
		return mensaje;
	}

	/**
	 * Indica la cantidad de libros que tiene fecha de devolucion nula para un
	 * socio.
	 * 
	 * @param socio socio del que queremos saber la cantidad de libros pendientes
	 *              por devolver
	 * @return un entero que representa la cantidad de libros que aun no ha devuelto
	 *         el socio
	 */
	public int getCantidadLibrosPrestadosBySocio(Socio socio) {
		return dataPrestamo.getCantidadLibrosPrestadosBySocio(socio);
	}

	/**
	 * Metodo que indica si un usuario puede efectuar un prestamo
	 * 
	 * @param socio socio que puede ser capaz de realizar un prestamo
	 * @return Un opcional que si es Optional.empty() que el socio puede y sino el
	 *         opcional tiene el mensaje de error por el cual el usuario no puede
	 *         ejecutar un prestamo
	 */
	public static Optional<String> isUserCapableOfLoan(Socio socio) {
		SocioLogic sLogic = new SocioLogic();
		int cantidadLibrosPrestados = sLogic.getPossibleAmountOfBooks(socio);
		if (cantidadLibrosPrestados <= 0) {
			return Optional.of("El socio no puede pedir prestado mas libros");
		}
		if (!sLogic.checkIfSocioPaidCurrentCuota(socio)) {
			return Optional.of("El socio no pago la cuota este mes");
		}
		return Optional.empty();
	}

}
