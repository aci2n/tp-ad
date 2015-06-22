package svl.personal;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.ControladorPrincipal;
import svl.GenericHttpServlet;
import views.personal.EmpleadoView;

@WebServlet("/jsp/Personal/AgregarEmpleado")
public class AgregarEmpleado extends GenericHttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String cuit = request.getParameter("cuit");
		String dni = request.getParameter("dni");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String fechaNacimiento = request.getParameter("fechaNacimiento");
		String tipo = request.getParameter("tipo");
		
		try {
			EmpleadoView e = new EmpleadoView(cuit, dni, nombre, apellido, fechaNacimiento, tipo);
			Integer i = ControladorPrincipal.getInstance().getAdministradorSucursales().agregarEmpleadoASucursal(Integer.parseInt(id), e);
			forwardGenerico(request, response, "Empleado agregado exitosamente con id: " + i + ".");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			forwardGenerico(request, response, e.getMessage());
		}
	}
}
