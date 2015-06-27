package svl.clientes;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svl.GenericHttpServlet;
import views.clientes.EmpresaView;

@WebServlet("/AltaClienteEmpresa")
public class AltaClienteEmpresa extends GenericHttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("nombre");

		try {
			EmpresaView empresa = new EmpresaView();
			empresa.setNombre(nombre);
			Integer id = delegate.getInterfaz().altaEmpresa(empresa);
			forwardGenerico(request, response, "Cliente agregado exitosamente con id: " + id + ".");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			forwardGenerico(request, response, e.getMessage());
		}
	}
}
