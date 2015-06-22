package views.vehiculos;

public class ProveedorView {

	private int id;
	private String cuit;
	private String nombre;

	public ProveedorView() {

	}

	public ProveedorView(String cuit, String nombre) {
	
		this.cuit = cuit;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
