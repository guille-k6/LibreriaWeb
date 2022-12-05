package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Libro;
import Entities.Socio;
import Logic.LibroLogic;
import Logic.SocioLogic;

/**
 * Servlet implementation class UsuariosAConfirmarForm
 */
@WebServlet("/UsuariosAConfirmarForm")
public class UsuariosAConfirmarForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuariosAConfirmarForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Socio socio = new Socio();		
		socio = (Socio)request.getSession().getAttribute("usuario");		
		String pagar = request.getParameter("pagar");
		if(pagar!=null){
			// Recupero el ID y lo mando a la sección modificar
			String idACobrar = request.getParameter("pagar");
			SocioLogic soclog = new SocioLogic();
			Socio socioACobrar = new Socio();
			
			request.setAttribute("idACobrar", idACobrar);
			
			//Busco el libro con ese ID y lo mando como parámetro al autor a la página de modificar.
			socioACobrar.setIdSocio(Integer.parseInt(idACobrar));
			socioACobrar = soclog.getOneById(socioACobrar);
			request.setAttribute("socioACobrar", socioACobrar);
			request.getRequestDispatcher("WEB-INF/pages/admin/CobrarSocio.jsp").forward(request, response);			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
