package Servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Cuotas;
import Logic.CuotasLogic;
import java.util.Calendar;

/**
 * Servlet implementation class ConfirmarCobroCuotas
 */
@WebServlet("/ConfirmarCobroCuotas")
public class ConfirmarCobroCuotas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmarCobroCuotas() {
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
		case("cobrar"):
			LinkedList<Cuotas> lasCuotas = new LinkedList<Cuotas>();
			String cuotasPagar[] = request.getParameterValues("idcheck"); // Array con los ID de las cuotas a pagar.
			
			for(String idCuota : cuotasPagar) {
				int elId = Integer.parseInt(idCuota);
				Cuotas cuota = new Cuotas();
				cuota.setIdCuota(elId);
				cuota = cuolog.getOneById(cuota);
				lasCuotas.add(cuota);
				cuota.setEstado("Pagada"); // Las actualiza para que el admin se las de como pagas
				Calendar calendar = Calendar.getInstance();
				java.util.Date currentDate = calendar.getTime();
				java.sql.Date date = new java.sql.Date(currentDate.getTime());
				cuota.setFechaPago(date);
				cuolog.update(cuota);			
			}
			String estado = "Cobro realizado con exito.";
			
			request.setAttribute("estado", estado);
			request.getRequestDispatcher("WEB-INF/pages/menuAdmin.jsp").forward(request, response);
			break;
		case("cancelar"):
			request.getRequestDispatcher("WEB-INF/pages/user/UsuariosAConfirmar.jsp").forward(request, response);
			break;
		}	
	}

}
