package Logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Data.DataEjemplar;
import Entities.Ejemplar;
import Entities.Libro;

public class EjemplarLogic {
	private DataEjemplar dataEjemplar;
	
	/*public class EjemplarCantidad {
		public int cantidad;
		public Ejemplar ejemplar;
		public EjemplarCantidad(int cantidad, Ejemplar ejemplar) {
			this.cantidad = cantidad;
			this.ejemplar = ejemplar;
		}
	}*/

	public EjemplarLogic() {
		dataEjemplar = new DataEjemplar();
	}
	public LinkedList<Ejemplar> getAll(){
		return dataEjemplar.getAll();
	}
	/*public LinkedList<EjemplarCantidad> getAvailableEjemplares(){
		List<Ejemplar> allEjemplares = this.getAll();
		Map<String, Integer> cantidades = new HashMap<>();
		for(Ejemplar ej : allEjemplares) {
			if(ej.isDisponible()) {
				String tituloEjemplar = ej.getLibro().getTitulo();
				if(cantidades.containsKey(tituloEjemplar)) {
					cantidades.put(tituloEjemplar, cantidades.get(tituloEjemplar) + 1);
				}else {
					cantidades.put(tituloEjemplar, 1);
				}
			}
			ej.getLibro().getTitulo();
		}
		
	}*/
	
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
	public LinkedList<Ejemplar> getAllEjemplaresByLibro(Libro libro){
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
	 public String[] dividirString(String entrada, char separador) {
	        return entrada.split(String.valueOf(separador));
	 }

}
