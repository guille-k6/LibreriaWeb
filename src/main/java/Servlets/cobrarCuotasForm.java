package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Socio;
import Logic.SocioLogic;

/**
 * Servlet implementation class cobrarCuotasForm
 */
@WebServlet("/cobrarCuotasForm")
public class cobrarCuotasForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cobrarCuotasForm() {
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
		Socio socio = new Socio();		
		socio = (Socio)request.getSession().getAttribute("usuario");		
		String opc = request.getParameter("opcion");
		
		switch(opc) {
		case("cobrar"):
			// Recupero el nombre y el apellido del form.
			String cuotasCobrar[] = request.getParameterValues("idcheck"); // Array con los ID de las cuotas a pagar.
			if(cuotasCobrar==null) {
				String idSocio[] = request.getParameterValues("idcheckSocio");
				String elidSocio = idSocio[0];
				SocioLogic soclog = new SocioLogic();
				Socio socioACobrar = new Socio();
				
				
				//Busco el libro con ese ID y lo mando como parámetro al autor a la página de modificar.
				socioACobrar.setIdSocio(Integer.parseInt(elidSocio));
				socioACobrar = soclog.getOneById(socioACobrar);
				request.setAttribute("socioACobrar", socioACobrar);
				//
				String error = "Selecciona como minimo una cuota";
				request.setAttribute("error", error);
				request.getRequestDispatcher("WEB-INF/pages/admin/CobrarSocio.jsp").forward(request, response);		
				return;
				
			}
			request.setAttribute("cuotasCobrar", cuotasCobrar);
			
			request.getRequestDispatcher("WEB-INF/pages/admin/confirmarCobroCuotas.jsp").forward(request, response);		
			
		
		
		
//			CuotasLogic cuolog = new CuotasLogic();
//			for(String idCuota : cuotasPagar) {
//				int elId = Integer.parseInt(idCuota);
//				Cuotas cuota = new Cuotas();
//				cuota.setIdCuota(elId);
//				cuota = cuolog.getOneById(cuota);
//				cuota.setEstado("A_Confirmar"); // Las actualiza para que el admin se las de como pagas
//				cuolog.update(cuota);
//				
//			}
//			String estado = "Se elevó un pedido de pago. Acercate a la sucursal a pagar"
			break;
			
		case("cancelar"):
			request.getRequestDispatcher("WEB-INF/pages/menuAdmin.jsp").forward(request, response);
			break;
	}

	}
}
