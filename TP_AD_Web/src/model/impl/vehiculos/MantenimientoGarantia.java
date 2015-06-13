package model.impl.vehiculos;



public class MantenimientoGarantia extends EstrategiaMantenimiento {

	@Override
	protected void mantener() {
		System.out.println("Se hizo un mantenimiento de un vehiculo en garantia.");			
	}

}
