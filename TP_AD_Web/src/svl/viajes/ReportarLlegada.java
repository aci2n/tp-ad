package svl.viajes;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.ControladorPrincipal;
import svl.GenericHttpServlet;

@WebServlet("/reportarLlegada")
public class ReportarLlegada extends GenericHttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7355179795447336756L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Integer idViaje = request.getParameter("idViaje") != null ? Integer.parseInt(request.getParameter("idViaje")) : null;
		
		if (idViaje != null) {
			try {
				ControladorPrincipal.getInstance().registrarLlegada(idViaje);
			} catch (Exception e) {
				e.printStackTrace();
				forwardError(request, response);
			}
		} else {
			forwardError(request, response);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
