package Servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Autor;
import Entities.Cuotas;
import Logic.AutorLogic;
import Logic.CuotasLogic;

/**
 * Servlet implementation class ConfirmarPagoCuotas
 */
@WebServlet("/ConfirmarPagoCuotas")
public class ConfirmarPagoCuotas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmarPagoCuotas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		CuotasLogic cuolog = new CuotasLogic();
		
		String opc = request.getParameter("opcion");
		switch(opc) {
		case("pagar"):
			LinkedList<Cuotas> lasCuotas = new LinkedList<Cuotas>();
			String cuotasPagar[] = request.getParameterValues("idcheck"); // Array con los ID de las cuotas a pagar.
			
			for(String idCuota : cuotasPagar) {
				int elId = Integer.parseInt(idCuota);
				Cuotas cuota = new Cuotas();
				cuota.setIdCuota(elId);
				cuota = cuolog.getOneById(cuota);
				lasCuotas.add(cuota);
				cuota.setEstado("A_Confirmar"); // Las actualiza para que el admin se las de como pagas
				cuolog.update(cuota);			
			}
			String estado = "Solicitud de pago enviada. Realice el pago fisicamente a la biblioteca y se le aprobará la solicitud.";
			
			request.setAttribute("estado", estado);
			request.getRequestDispatcher("WEB-INF/pages/menuUser.jsp").forward(request, response);
			break;
		case("cancelar"):
			request.getRequestDispatcher("WEB-INF/pages/user/pagarCuotas.jsp").forward(request, response);
			break;
		}
		
		
		

		
		
	}

}
