package svl.choferes;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rmi.delegate.BusinessDelegate;
import views.viajes.ViajeView;

import com.google.gson.Gson;

/**
 * Servlet implementation class ActualizarParadasViaje
 */
@WebServlet("/ActualizarParadasViaje")
public class ActualizarParadasViaje extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ActualizarParadasViaje() {
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
		Integer id = Integer.parseInt(request.getParameter("id_chofer"));

		// Obtiene el viaje
		
		try {
			ViajeView vw = BusinessDelegate.getInstance().getInterfaz().obtenerViajeActivo(id);
			// Genera json
			if (vw == null) {
				response.setStatus(404);
				return;
			}
			Gson gson = new Gson();
			String json = gson.toJson(vw);
			System.out.println(json);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
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
		// TODO Auto-generated method stub
	}

}
