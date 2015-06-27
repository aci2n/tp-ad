package svl.viajes;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svl.GenericHttpServlet;
import views.misc.CoordenadaView;
import views.misc.UbicacionView;
import views.viajes.ViajeView;
import controllers.ControladorPrincipal;

@WebServlet("/jsp/Viajes/AltaViaje")
public class AltaViaje extends GenericHttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idVehiculo = request.getParameter("idVehiculo");
		String idSeguro = request.getParameter("idSeguro");
		String fechaSalida = request.getParameter("fechaSalida");
		String fechaLlegada = request.getParameter("fechaLlegada");
		String paisO = request.getParameter("paisO");
		String provinciaO = request.getParameter("provinciaO");
		String ciudadO = request.getParameter("ciudadO");
		String calleO = request.getParameter("calleO");
		String alturaO = request.getParameter("alturaO");
		String pisoO = request.getParameter("pisoO");
		String departamentoO = request.getParameter("departamentoO");
		String latitudO = request.getParameter("latitudO");
		String longitudO = request.getParameter("longitudD");
		String paisD = request.getParameter("paisD");
		String provinciaD = request.getParameter("provinciaD");
		String ciudadD = request.getParameter("ciudadD");
		String calleD = request.getParameter("calleD");
		String alturaD = request.getParameter("alturaD");
		String pisoD = request.getParameter("pisoD");
		String departamentoD = request.getParameter("departamentoD");
		String latitudD = request.getParameter("latitudD");
		String longitudD = request.getParameter("longitudD");
		try {
			UbicacionView origen = new UbicacionView(paisO, provinciaO, ciudadO, calleO, alturaO, pisoO, departamentoO, new CoordenadaView(
					Integer.parseInt(latitudO), Integer.parseInt(longitudO)));
			UbicacionView destino = new UbicacionView(paisD, provinciaD, ciudadD, calleD, alturaD, pisoD, departamentoD, new CoordenadaView(
					Integer.parseInt(latitudD), Integer.parseInt(longitudD)));
			ViajeView viaje = new ViajeView(fechaSalida, fechaLlegada, origen, destino);
			Integer i = ControladorPrincipal.getInstance().altaViaje(Integer.parseInt(idVehiculo), Integer.parseInt(idSeguro), viaje);
			forwardGenerico(request, response, "Viaje agregado exitosamente con id: " + i + ".");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			forwardGenerico(request, response, e.getMessage());
		}
	}
}
