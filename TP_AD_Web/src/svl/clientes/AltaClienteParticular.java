package svl.clientes;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svl.GenericHttpServlet;
import views.clientes.ParticularView;
import controllers.ControladorPrincipal;

@WebServlet("/AltaClienteParticular")
public class AltaClienteParticular extends GenericHttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dni = request.getParameter("dni");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		try {
			ParticularView particular = new ParticularView();
			particular.setApellido(apellido);
			particular.setNombre(nombre);
			particular.setDni(dni);			
			Integer id = ControladorPrincipal.getInstance().altaParticular(particular);
			forwardGenerico(request, response, "Cliente agregado exitosamente con id: " + id + ".");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			forwardGenerico(request, response, e.getMessage());
		}
	}
}
