package impl.viajes;

import views.cargas.CargaView;
import views.vehiculos.VehiculoExternoView;

public class AdministradorContratacionViajes {
	private static AdministradorContratacionViajes instance;
	
	private AdministradorContratacionViajes() {
		
	}
	
	public static AdministradorContratacionViajes getInstance() {
		if (instance == null) {
			instance = new AdministradorContratacionViajes();
		}
		return instance;
	}
	
	public VehiculoExternoView obtenerMejorVehiculo(CargaView carga) {
		//	TODO
		return null;
	}
}
