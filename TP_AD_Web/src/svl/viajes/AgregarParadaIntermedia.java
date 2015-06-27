package svl.viajes;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svl.GenericHttpServlet;
import views.misc.CoordenadaView;
import views.misc.UbicacionView;
import views.viajes.ParadaIntermediaView;
import controllers.ControladorPrincipal;

@WebServlet("/jsp/Viajes/AgregarParadaIntermedia")
public class AgregarParadaIntermedia extends GenericHttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String llegada = request.getParameter("llegada");
		String pais = request.getParameter("pais");
		String provincia = request.getParameter("provincia");
		String ciudad = request.getParameter("ciudad");
		String calle = request.getParameter("calle");
		String altura = request.getParameter("altura");
		String piso = request.getParameter("piso");
		String departamento = request.getParameter("departamento");
		String latitud = request.getParameter("latitud");
		String longitud = request.getParameter("longitud");
		try {
			CoordenadaView c = new CoordenadaView(Float.parseFloat(latitud), Float.parseFloat(longitud));
			UbicacionView u = new UbicacionView(pais, provincia, ciudad, calle, altura, piso, departamento, c);
			ParadaIntermediaView p = new ParadaIntermediaView(llegada, u);
			ControladorPrincipal.getInstance().agregarParadaIntermediaAViaje(Integer.parseInt(id), p);
			forwardGenerico(request, response, "Parada intermedia agregada exitosamente.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			forwardGenerico(request, response, e.getMessage());
		}
	}
}
