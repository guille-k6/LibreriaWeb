package Servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Cuotas;
import Entities.Socio;
import Logic.CuotasLogic;

@WebServlet("/listadoCuotas")
public class listadoCuotas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public listadoCuotas() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		CuotasLogic cuotasLogic = new CuotasLogic();

		String idSocioString = request.getParameter("socio");
		Socio socio = new Socio(Integer.parseInt(idSocioString));

		String fechaDesde = request.getParameter("fechaDesde");
		String fechaHasta = request.getParameter("fechaHasta");
		if (fechaDesde.isBlank() || fechaHasta.isBlank()) {
			request.setAttribute("mensaje", "Complete las fechas");
			request.getRequestDispatcher("WEB-INF/pages/admin/ListadoCuotas.jsp").forward(request, response);
			return;
		}

		try {
			java.util.Date fechaDesdeDate = dateFormat.parse(fechaDesde);
			java.sql.Date fechaDesdeSqlDate = new java.sql.Date(fechaDesdeDate.getTime());
			java.util.Date fechaHastaDate = dateFormat.parse(fechaHasta);
			java.sql.Date fechaHastaSqlDate = new java.sql.Date(fechaHastaDate.getTime());
			List<Cuotas> cuotasBuscadas = cuotasLogic.getCuotasBySocioAndDate(socio, fechaDesdeSqlDate,
					fechaHastaSqlDate);
			request.setAttribute("cuotasBuscadas", cuotasBuscadas);
			request.setAttribute("fechaDesde", fechaDesde);
			request.setAttribute("fechaHasta", fechaHasta);
			request.setAttribute("idSocio", idSocioString);
			request.getRequestDispatcher("WEB-INF/pages/admin/ListadoCuotas.jsp").forward(request, response);
			return;
		} catch (Exception e) {
			request.setAttribute("mensaje", e.getMessage());
			request.getRequestDispatcher("WEB-INF/pages/admin/ListadoCuotas.jsp").forward(request, response);
		}
	}

}
