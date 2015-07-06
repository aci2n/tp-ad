package impl.personal;

import impl.clientes.Empresa;
import impl.productos.Producto;

import java.util.ArrayList;
import java.util.List;

import persistence.ClienteDAO;
import persistence.EmpleadoDAO;
import persistence.ProductoDAO;
import views.personal.EmpleadoView;
import views.productos.ProductoView;

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
