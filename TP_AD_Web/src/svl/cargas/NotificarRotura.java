package svl.cargas;

import impl.cargas.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.CargaDAO;
import persistence.SeguimientoCargasDAO;
import rmi.delegate.BusinessDelegate;
import views.cargas.SeguimientoCargaView;

import com.google.gson.Gson;

/**
 * Servlet implementation class NotificarRotura
 */
@WebServlet("/NotificarRotura")
public class NotificarRotura extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotificarRotura() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// Obtiene el Id del input
			Integer id = Integer.parseInt(request.getParameter("id_carga"));

			// Obtiene la Carga
			List<SeguimientoCarga> sc = SeguimientoCargasDAO.getInstance().getByCarga(id);
			
			SeguimientoCarga ultimoSeguimiento = sc.get(sc.size()-1);
			
			//Agrega un seguimiento de carga con estado roto
			SeguimientoCarga s = new SeguimientoCarga();
			s.setCarga(ultimoSeguimiento.getCarga());
			s.setEstadoCarga(EstadoCarga.ROTO);
			s.setFecha(new Date());
			s.setSucursal(ultimoSeguimiento.getSucursal());
			s.setViaje(ultimoSeguimiento.getViaje());
			
			CargaDAO.getInstance().update(s.getCarga());
			
			//Inserta el nuevo seguimiento
			SeguimientoCargasDAO.getInstance().insert(s);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
