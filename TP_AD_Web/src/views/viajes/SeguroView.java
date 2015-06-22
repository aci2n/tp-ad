package views.viajes;

public class SeguroView {
	String nombre;
	String tipoCarga;
	Float tarifa;

	public SeguroView(String nombre, String tipoCarga, Float tarifa) {

		this.nombre = nombre;
		this.tipoCarga = tipoCarga;
		this.tarifa = tarifa;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoCarga() {
		return tipoCarga;
	}

	public void setTipoCarga(String tipoCarga) {
		this.tipoCarga = tipoCarga;
	}

	public Float getTarifa() {
		return tarifa;
	}

	public void setTarifa(Float tarifa) {
		this.tarifa = tarifa;
	}

}
