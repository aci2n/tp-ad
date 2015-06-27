package views.viajes;

import views.GenericView;

public class SeguroView extends GenericView {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String tipoCarga;
	private Float tarifa;

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
