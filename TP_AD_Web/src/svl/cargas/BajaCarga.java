package svl.cargas;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rmi.delegate.BusinessDelegate;
import svl.GenericHttpServlet;

@WebServlet("/cancelarCarga")
public class BajaCarga extends GenericHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer idCarga = request.getParameter("idCarga") == null ? -1 : Integer.parseInt(request.getParameter("idCarga"));
		try {
			BusinessDelegate.getInstance().getInterfaz().cancelarCarga(idCarga);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			forwardError(request, response);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
}
