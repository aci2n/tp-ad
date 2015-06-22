package views.viajes;

public class CompaniaSeguroView {
	private String cuil;
	private String nombre;

	public CompaniaSeguroView(String cuit, String nombre) {
		super();
		this.cuil = cuit;
		this.nombre = nombre;
	}

	public String getCuil() {
		return cuil;
	}

	public void setCuil(String cuit) {
		this.cuil = cuit;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
