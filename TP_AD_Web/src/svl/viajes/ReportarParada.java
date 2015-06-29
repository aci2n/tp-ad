package svl.viajes;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.ControladorPrincipal;
import svl.GenericHttpServlet;

@WebServlet("/reportarParada")
public class ReportarParada extends GenericHttpServlet {

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Integer idParada = request.getParameter("idParada") != null ? Integer.parseInt(request.getParameter("idParada")) : null;
		
		if (idParada != null) {
			try {
				ControladorPrincipal.getInstance().registrarParada(idParada);
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
