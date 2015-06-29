package svl.cargas;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.ControladorPrincipal;
import svl.GenericHttpServlet;

@WebServlet("/cancelarCarga")
public class BajaCarga extends GenericHttpServlet {

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer idCarga = Integer.parseInt(request.getParameter("idCarga"));
		try {
			ControladorPrincipal.getInstance().cancelarCarga(idCarga);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			forwardError(request, response);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
