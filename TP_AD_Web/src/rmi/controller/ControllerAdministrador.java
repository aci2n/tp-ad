package rmi.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import rmi.tda.TDAControllerAdministrador;
import views.personal.EmpleadoView;
import views.vehiculos.TareaView;
import views.viajes.CompaniaSeguroView;
import controllers.ControladorPrincipal;

public class ControllerAdministrador extends UnicastRemoteObject implements TDAControllerAdministrador {

	private static final long serialVersionUID = 1L;
	private static ControllerAdministrador instance;

	private ControllerAdministrador() throws RemoteException {

	}

	public static ControllerAdministrador getInstance() throws RemoteException {
		if (instance == null)
			instance = new ControllerAdministrador();
		return instance;
	}

	@Override
	public void actualizarPrecioVehiculo(Integer id, Float precio) throws Exception {
		ControladorPrincipal.getInstance().getAdministradorVehiculos().actualizarPrecioVehiculo(id, precio);
	}

	@Override
	public void agregarTarea(Integer id, TareaView tarea) throws Exception {
		ControladorPrincipal.getInstance().getAdministradorVehiculos().agregarTarea(id, tarea);
	}

	@Override
	public void agregarEmpleadoASucursal(Integer idSucursal, EmpleadoView empleado) throws Exception {
		ControladorPrincipal.getInstance().getAdministradorSucursales().agregarEmpleadoASucursal(idSucursal, empleado);

	}

	@Override
	public void bajaEmpleado(Integer id) throws Exception {
		ControladorPrincipal.getInstance().getAdministradorSucursales().bajaEmpleado(id);
	}

	@Override
	public List<CompaniaSeguroView> getCompaniasSeguroView() throws Exception {
		return ControladorPrincipal.getInstance().getAdministradorViajes().getCompaniasSeguroView();
	}

}
