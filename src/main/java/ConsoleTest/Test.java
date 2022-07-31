package ConsoleTest;
import java.util.LinkedList;

import Data.DataSocio;
import Entities.*;
import Logic.*;
public class Test {

	public static void main(String[] args) {
		SocioLogic soclog = new SocioLogic();
		DataSocio socData = new DataSocio();
		
		// PRUEBA GETBYUSER
		Socio miSocio = new Socio();
		miSocio.setUsuario("juanperez");
		miSocio.setContrasenia("juanperez1");
		miSocio = socData.getByUser(miSocio);
		System.out.println(miSocio.getIdSocio() +" "+ miSocio.getNombre());		
		
		// PRUEBA GETALL
		/*LinkedList<Socio> losSocios = soclog.getAll();
		for (Socio socio : losSocios) {
			System.out.println(socio.getIdSocio() +" "+ socio.getNombre());
		}*/
		
		// PRUEBA GETONEBYID
		/*Socio miSocio = new Socio();
		miSocio.setIdSocio(1);
		miSocio = soclog.getOneById(miSocio);
		System.out.println(miSocio.getIdSocio() +" "+ miSocio.getNombre());*/
		
		// PRUEBA ADD
		/*Socio miSocio = new Socio();
		miSocio.setNombre("Juanjo");
		miSocio.setApellido("Rodriguez");
		miSocio.setEmail("juanjorof@gmail.com");
		miSocio.setDomicilio("av eva peron 3223");
		miSocio.setTelefono("233322332");
		miSocio.setEstadoSocio("Activo");
		miSocio.setContrasenia("juanjorodriguez1");
		miSocio.setAdmin(true);
		miSocio.setUsuario("juanjorodriguez");
		soclog.add(miSocio);
		System.out.println(miSocio.getIdSocio() +" "+ miSocio.getNombre() +" "+miSocio.getAdmin());
		*/
		
		// PRUEBA UPDATE
		
		/*Socio miSocio = new Socio();
		miSocio.setIdSocio(3);
		miSocio = soclog.getOneById(miSocio);
		
		miSocio.setNombre("Jorge");
		miSocio.setApellido("LopezCambiado");
		soclog.update(miSocio);
		System.out.println(miSocio.getIdSocio() +" "+ miSocio.getNombre()+" "+ miSocio.getApellido());
		*/
		
		// PRUEBA REMOVE
		/*
		Socio miSocio = new Socio();
		miSocio.setIdSocio(3);
		miSocio = soclog.getOneById(miSocio);
		soclog.remove(miSocio);
		*/
			
		
	}

}
