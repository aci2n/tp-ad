package model.impl.vehiculos;



public class MantenimientoGeneral extends EstrategiaMantenimiento {

	@Override
	protected void mantener() {
		System.out.println("Se hizo un mantenimiento general.");			
	}

}
