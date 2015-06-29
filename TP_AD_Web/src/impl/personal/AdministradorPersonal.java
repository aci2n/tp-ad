package impl.personal;

import java.util.ArrayList;
import java.util.List;

import persistence.EmpleadoDAO;
import views.personal.EmpleadoView;

public class AdministradorPersonal {
	private static AdministradorPersonal instance;
	private EmpleadoDAO empleadoDao;
	
	private AdministradorPersonal() {
		this.empleadoDao = EmpleadoDAO.getInstance();
	}
	
	public static AdministradorPersonal getInstance() {
		if (instance == null)
			instance = new AdministradorPersonal();
		return instance;
	}
	
	public List<EmpleadoView> obtenerChoferes() {
		List<EmpleadoView> empleados = new ArrayList<EmpleadoView>();
		for (Empleado empleado : empleadoDao.obtenerPorPuesto(TipoPuesto.CHOFER)) {
			empleados.add(empleado.getView());
		}
		return empleados;
	}
}
