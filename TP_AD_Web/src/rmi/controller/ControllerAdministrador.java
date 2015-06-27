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
	private ControladorPrincipal controlador;

	private ControllerAdministrador() throws RemoteException {
		controlador = ControladorPrincipal.getInstance();
	}

	public static ControllerAdministrador getInstance() throws RemoteException {
		if (instance == null)
			instance = new ControllerAdministrador();
		return instance;
	}

	@Override
	public void actualizarPrecioVehiculo(Integer id, Float precio) throws Exception {
		controlador.actualizarPrecioVehiculo(id, precio);
	}

	@Override
	public void agregarTarea(Integer id, TareaView tarea) throws Exception {
		controlador.agregarTarea(id, tarea);
	}

	@Override
	public void agregarEmpleadoASucursal(Integer idSucursal, EmpleadoView empleado) throws Exception {
		controlador.agregarEmpleadoASucursal(idSucursal, empleado);

	}

	@Override
	public void bajaEmpleado(Integer id) throws Exception {
		controlador.bajaEmpleado(id);
	}

	@Override
	public List<CompaniaSeguroView> getCompaniasSeguroView() throws Exception {
		return controlador.getCompaniasSeguroView();
	}

}
