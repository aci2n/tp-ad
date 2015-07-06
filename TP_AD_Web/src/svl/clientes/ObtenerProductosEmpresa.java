package svl.clientes;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rmi.delegate.BusinessDelegate;
import views.productos.ProductoView;

import com.google.gson.Gson;

/**
 * Servlet implementation class ActualizarParadasViaje
 */
@WebServlet("/ObtenerProductosEmpresa")
public class ObtenerProductosEmpresa extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ObtenerProductosEmpresa() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Obtiene el Id del input
		Integer id = Integer.parseInt(request.getParameter("idEmpresa"));

		
		try {
			List<ProductoView> productos = BusinessDelegate.getInstance().getInterfaz().obtenerProductosEmpresa(id);
			
			if (productos != null) {
				Gson gson = new Gson();
				String json = gson.toJson(productos);
				System.out.println(json);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
			} else {
				response.setStatus(404);
				return;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setStatus(500);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
