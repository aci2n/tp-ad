package views.vehiculos;

import views.GenericView;

public class ProveedorView extends GenericView{

	private String cuit;
	private String nombre;

	public ProveedorView() {

	}

	public ProveedorView(String cuit, String nombre) {
		this.cuit = cuit;
		this.nombre = nombre;
	}


	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
