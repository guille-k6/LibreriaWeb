package Servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Logic.LibroLogic;
import Entities.Autor;
import Entities.Libro;
import Entities.Socio;
import Logic.SocioLogic;

/**
 * Servlet implementation class ABMLibrosForm
 */
@WebServlet("/BusquedaSocioForm")
public class BusquedaSocioForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BusquedaSocioForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recupero opci�n y usuario.
		Socio socio = new Socio();		
		socio = (Socio)request.getSession().getAttribute("usuario");		
		String opc = request.getParameter("elegir"); // Asi se llama el name del socio a adjudicarle el prestamo.
		
		if(opc!=null){
			// Recupero el ID y lo mando a la secci�n modificar
			String idElegir = request.getParameter("elegir");
			SocioLogic soclog = new SocioLogic();
			Socio socioPrestamista = new Socio();						
			//Busco el libro con ese ID y lo mando como par�metro al autor a la p�gina de modificar.
			socioPrestamista.setIdSocio(Integer.parseInt(idElegir));
			Socio socioPrestar = soclog.getOneById(socioPrestamista);
			request.setAttribute("socioPrestar", socioPrestar);
			request.getRequestDispatcher("WEB-INF/pages/admin/EjemplaresDisponibles.jsp").forward(request, response);			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		SocioLogic soclog = new SocioLogic();
		Socio socio = new Socio();		
		socio = (Socio)request.getSession().getAttribute("usuario");
		String apellido = request.getParameter("apellido");
		Socio socioABuscar = new Socio();
		socioABuscar.setApellido(apellido);
		LinkedList<Socio> sociosApellido = new LinkedList<Socio>();
		sociosApellido= soclog.getAllByApellido(socioABuscar);
		request.setAttribute("sociosApellido", sociosApellido);
		// Busco los socios que tengan un apellido y lo envio a la pagina anterior.
		request.getRequestDispatcher("WEB-INF/pages/admin/BuscarSocio.jsp").forward(request, response);	
	}

}
