package Servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Ejemplar;
import Entities.LineaDePrestamo;
import Entities.Prestamo;
import Entities.Socio;
import Logic.EjemplarLogic;
import Logic.LineaDePrestamoLogic;
import Logic.PrestamoLogic;
import Logic.SocioLogic;
import utils.LoggerError;

@WebServlet("/devolverUnEjemplar")
public class devolverUnEjemplar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public devolverUnEjemplar() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Actualiza fecha de devolución real a hoy y setea ejemplar a como disponible
		PrestamoLogic pl = new PrestamoLogic();
		LineaDePrestamoLogic ldpLogic = new LineaDePrestamoLogic();
		EjemplarLogic ejemplarLogic = new EjemplarLogic();
		try {
			String idPrestamoString = request.getParameter("prestamoId");
			String idLineaDePrestamoString = request.getParameter("idLineaDePrestamo");
			LineaDePrestamo ldp = ldpLogic.getById(new LineaDePrestamo(Integer.valueOf(idLineaDePrestamoString)));
			ldp.setFechaDevolucionReal(new Date(System.currentTimeMillis()));
			Ejemplar ejemplarToUpdate = ldp.getEjemplar();
			ejemplarToUpdate.setDisponible(true);
			ejemplarLogic.update(ejemplarToUpdate);
			ldpLogic.update(ldp);
			Prestamo prestamo = pl.getOneById(new Prestamo(Integer.valueOf(idPrestamoString)));
			// TODO: Aplicar una sancion a un socio si devolvio el libro muy tarde
			if (ldp.getFechaDevolucionReal().after(ldp.getFechaDevolucionTeorica())) {
				Socio socioASancionar = prestamo.getSocio();
				SocioLogic socioLogic = new SocioLogic();
				// socioLogic.sancionarAUnSocio(socioASancionar);
			}
			request.setAttribute("prestamo", prestamo);
			request.getRequestDispatcher("WEB-INF/pages/admin/PrestamoDetallado.jsp").forward(request, response);
		} catch (Exception e) {
			LoggerError.log(e.getStackTrace(), e.getMessage());
			request.setAttribute("errorMessage", "Hubo un error al actualizar el ejemplar a disponible");
		}
	}

}
