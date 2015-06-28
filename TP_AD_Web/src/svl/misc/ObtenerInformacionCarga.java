package svl.misc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svl.GenericHttpServlet;
import views.misc.SucursalCargaView;

@WebServlet("/jsp/Cargas/ObtenerInformacionCarga")
public class ObtenerInformacionCarga extends GenericHttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		System.out.println("hola");
		String idCarga = request.getParameter("idCarga");
		try {
			SucursalCargaView scv = delegate.getInterfaz().obtenerSucursalCargaView(Integer.parseInt(idCarga));
			StringBuilder sb = new StringBuilder();
			sb.append("<table>");
			sb.append("<tr><th>Sucursal</th><th>ID Carga</th><th>Estado Carga</th>");
			sb.append("<tr><td>" + scv.getSucursal().getNombre() + "</td><td>" + scv.getCarga().getId() + "</td><td>"
					+ scv.getCarga().getEstadoCarga() + "</td></tr>");
			sb.append("</table>");
			response.getWriter().print(sb.toString());
		} catch (Exception e) {
			response.getWriter().print("No se encontro la carga ingresada.");
		}
	}
}
