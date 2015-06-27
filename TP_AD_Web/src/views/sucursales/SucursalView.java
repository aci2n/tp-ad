package views.sucursales;

import views.GenericView;
import views.misc.UbicacionView;

public class SucursalView extends GenericView{
	private String nombre;
	private UbicacionView ubicacion;

	public SucursalView(String nombre, UbicacionView u) {
		this.nombre = nombre;
		this.ubicacion = u;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public UbicacionView getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(UbicacionView ubicacion) {
		this.ubicacion = ubicacion;
	}

}
