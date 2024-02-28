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

	public cobrarCuotasForm() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opc = request.getParameter("opcion");
		if (opc == null || opc.isBlank() || opc.equals("cancelar")) {
			request.getRequestDispatcher("WEB-INF/pages/menuAdmin.jsp").forward(request, response);
		}
		SocioLogic soclog = new SocioLogic();
		String idSocioString = request.getParameter("idcheckSocio");
		Socio socioACobrar = new Socio(Integer.parseInt(idSocioString));
		String cuotasCobrar[] = request.getParameterValues("idcheck"); // Array con los ID de las cuotas a pagar.
		if (cuotasCobrar == null || cuotasCobrar.length == 0) {
			socioACobrar = soclog.getOneById(socioACobrar);
			request.setAttribute("socioACobrar", socioACobrar);
			request.setAttribute("error", "Selecciona como minimo una cuota");
			request.getRequestDispatcher("WEB-INF/pages/admin/CobrarSocio.jsp").forward(request, response);
			return;
		} else {
			request.setAttribute("cuotasCobrar", cuotasCobrar);
			request.getRequestDispatcher("WEB-INF/pages/admin/confirmarCobroCuotas.jsp").forward(request, response);
		}

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
//			String estado = "Se elev� un pedido de pago. Acercate a la sucursal a pagar"
	}
}
