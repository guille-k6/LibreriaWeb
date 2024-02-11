package Logic;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import Data.DataLineaDePrestamo;
import Entities.Ejemplar;
import Entities.LineaDePrestamo;
import Entities.Prestamo;

public class LineaDePrestamoLogic {
	private DataLineaDePrestamo dataLineaDePrestamo;

	public LineaDePrestamoLogic() {
		dataLineaDePrestamo = new DataLineaDePrestamo();
	}

	public Prestamo getAllByPrestamo(Prestamo prestamo) {
		return dataLineaDePrestamo.getAllByPrestamo(prestamo);
	}

	public LineaDePrestamo getById(LineaDePrestamo lineaToSearch) {
		return dataLineaDePrestamo.getById(lineaToSearch);
	}

	/**
	 * Inserta lineas de prestamo en batch
	 * 
	 * @param lineasDePrestamo
	 */
	public void addAll(List<LineaDePrestamo> lineasDePrestamo) {
		dataLineaDePrestamo.addAll(lineasDePrestamo);
	}

	public void addOne(Prestamo prestamo, LineaDePrestamo linea) {
		dataLineaDePrestamo.addOne(prestamo, linea);
	}

	public void update(LineaDePrestamo linea, Prestamo pres) {
		dataLineaDePrestamo.update(linea, pres);
	}

	public void remove(LineaDePrestamo linea) {
		dataLineaDePrestamo.remove(linea);
	}

	/**
	 * @param idPrestamoCreado       id del prestamo que se creo
	 * @param ejemplaresActualizados los ejemplares que estaran en cada una de las
	 *                               lineas de prestsamo para ese prestamo
	 */
	public void createLineasDePrestamoByIdPrestamo(int idPrestamoCreado, Set<Integer> ejemplaresActualizados) {
		EjemplarLogic ejLogic = new EjemplarLogic();
		List<Ejemplar> ejemplaresCargados = ejLogic.fillEjemplaresById(ejemplaresActualizados);
		List<LineaDePrestamo> lineasDePrestamo = new ArrayList<>();
		for (Ejemplar ejemplar : ejemplaresCargados) {
			LineaDePrestamo ldp = new LineaDePrestamo();
			ldp.setEjemplar(ejemplar);
			ldp.setIdPrestamo(idPrestamoCreado);
			LocalDate devolucionTeoricaLocalDate = LocalDate.now()
					.plusDays(ejemplar.getLibro().getCantDiasMaxPrestamo());
			Date fechaDevolucionTeorica = Date.valueOf(devolucionTeoricaLocalDate);
			ldp.setFechaDevolucionTeorica(fechaDevolucionTeorica);
			lineasDePrestamo.add(ldp);
		}
		addAll(lineasDePrestamo);

	}

}
