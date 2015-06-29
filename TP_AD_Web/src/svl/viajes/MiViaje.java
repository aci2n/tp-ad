package svl.viajes;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svl.GenericHttpServlet;
import views.personal.EmpleadoView;
import controllers.ControladorPrincipal;

@WebServlet("/MiViaje")
public class MiViaje extends GenericHttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			List<EmpleadoView> choferes = ControladorPrincipal.getInstance().obtenerChoferes();
			request.setAttribute("choferes", choferes);
			forwardJsp(request, response, "/jsp/Choferes/MostrarMiViaje.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
