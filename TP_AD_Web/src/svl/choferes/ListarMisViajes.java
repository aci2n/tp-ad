package svl.choferes;

import impl.viajes.Viaje;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.ViajeDAO;

/**
 * Servlet implementation class ListarMisViajes
 */
@WebServlet("/ListarMisViajes")
public class ListarMisViajes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListarMisViajes() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String idChofer = request.getParameter("idChofer");
		try{
			
			List<Viaje> viajes = ViajeDAO.getInstance().getViajesEmpleado(Integer.parseInt(idChofer));
			request.setAttribute("misViajes", viajes);
			
		}catch(Exception e){
			
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
