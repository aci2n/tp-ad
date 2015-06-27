package rmi.tda;

import java.rmi.Remote;
import java.util.List;

import views.personal.EmpleadoView;
import views.vehiculos.TareaView;
import views.viajes.CompaniaSeguroView;

public interface TDAControllerAdministrador extends Remote {
	public void actualizarPrecioVehiculo(Integer id, Float precio) throws Exception;

	public void agregarTarea(Integer id, TareaView tarea) throws Exception;

	public void agregarEmpleadoASucursal(Integer idSucursal, EmpleadoView empleado) throws Exception;

	public void bajaEmpleado(Integer id) throws Exception;

	public List<CompaniaSeguroView> getCompaniasSeguroView() throws Exception;
}
