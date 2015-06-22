package views.clientes;

import views.misc.UbicacionView;

public class ReceptorView {
	private String dni;
	private String nombre;
	private String apellido;
	private UbicacionView ubicacion;

	public ReceptorView() {
	}

	public ReceptorView(String dni, String nombre, String apellido,
			UbicacionView ubicacion) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.ubicacion = ubicacion;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public UbicacionView getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(UbicacionView ubicacion) {
		this.ubicacion = ubicacion;
	}

}
