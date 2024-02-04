package utils;

import java.util.ArrayList;

import Data.DataEjemplar;

public class Test {

	public static void main(String[] args) {

		/*
		 * AutorLogic autlog = new AutorLogic(); Autor elAutor = new Autor();
		 * elAutor.setIdAutor(12); autlog.remove(elAutor);
		 */
		// PRUEBA GETALL

		// PRUEBA GETONEBYID
		/*
		 * Socio miSocio = new Socio(); miSocio.setIdSocio(1); miSocio =
		 * soclog.getOneById(miSocio); System.out.println(miSocio.getIdSocio() +" "+
		 * miSocio.getNombre());
		 */

		// PRUEBA ADD
		/*
		 * Socio miSocio = new Socio(); miSocio.setNombre("Juanjo");
		 * miSocio.setApellido("Rodriguez"); miSocio.setEmail("juanjorof@gmail.com");
		 * miSocio.setDomicilio("av eva peron 3223"); miSocio.setTelefono("233322332");
		 * miSocio.setEstadoSocio("Activo"); miSocio.setContrasenia("juanjorodriguez1");
		 * miSocio.setAdmin(true); miSocio.setUsuario("juanjorodriguez");
		 * soclog.add(miSocio); System.out.println(miSocio.getIdSocio() +" "+
		 * miSocio.getNombre() +" "+miSocio.getAdmin());
		 */

		// PRUEBA UPDATE

		/*
		 * Socio miSocio = new Socio(); miSocio.setIdSocio(3); miSocio =
		 * soclog.getOneById(miSocio);
		 * 
		 * miSocio.setNombre("Jorge"); miSocio.setApellido("LopezCambiado");
		 * soclog.update(miSocio); System.out.println(miSocio.getIdSocio() +" "+
		 * miSocio.getNombre()+" "+ miSocio.getApellido());
		 */

		// PRUEBA REMOVE
		/*
		 * Socio miSocio = new Socio(); miSocio.setIdSocio(3); miSocio =
		 * soclog.getOneById(miSocio); soclog.remove(miSocio);
		 */

//		CuotasLogic cuolog = new CuotasLogic();
//
//		Cuotas laCuota = new Cuotas();
//		laCuota.setIdCuota(7);
//		laCuota = cuolog.getOneById(laCuota);
//		laCuota.setEstado("Pendiente");
//		cuolog.update(laCuota);
//		System.out.println(laCuota.getIdCuota() +" "+laCuota.getEstado()+" "+laCuota.getFechaHasta());

		/*
		 * AutorLogic aulog = new AutorLogic(); Autor elAutor = new Autor();
		 * LinkedList<String> pruebaLinked = new LinkedList<String>();
		 * System.out.println(pruebaLinked.isEmpty());
		 * 
		 * DataPrestamo dp = new DataPrestamo(); Prestamo np = new Prestamo();
		 * np.setIdPrestamo(1); Prestamo p = dp.getOneById(np); DataLineaDePrestamo dldp
		 * = new DataLineaDePrestamo(); try { dldp.addOne(p, new LineaDePrestamo(55, new
		 * java.sql.Date(System.currentTimeMillis()), null, "todo bien 2", new
		 * Ejemplar(1, true, null)));
		 * System.out.println("Se inserto una linea de prestamo...."); }catch(Exception
		 * e) { System.out.println(e.getMessage()); }
		 */
		try {
			DataEjemplar de = new DataEjemplar();
			ArrayList<Integer> listorti = new ArrayList<Integer>();
			listorti.add(4);
			listorti.add(6);

			System.out.println(de.updateEjemplaresToAlquilados(listorti));
		} catch (Exception e) {
			LoggerError.log(e.getStackTrace(), e.getMessage());
		}

//		ArrayList<Integer> asd = new ArrayList<>();
//		asd.add(1);
//		asd.add(2);
//		asd.add(3);
//		String asd2 = asd.toString().replace('[', '(');
//		String asd3 = asd2.toString().replace(']', ')');
//		System.out.println(asd3);

	}

}
