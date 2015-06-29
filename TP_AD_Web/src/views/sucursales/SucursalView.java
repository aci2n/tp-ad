package views.sucursales;

import views.GenericView;
import views.misc.UbicacionView;

public class SucursalView extends GenericView{
	private String nombre;
	private UbicacionView ubicacion;
	private Integer id;

	public SucursalView(String nombre, UbicacionView u) {
		this(nombre, u, null);
	}
	public SucursalView(String nombre, UbicacionView u, Integer id) {
		this.nombre = nombre;
		this.ubicacion = u;
		this.id = id;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
