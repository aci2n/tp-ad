package views.viajes;

import java.util.ArrayList;
import java.util.Collection;

public class CompaniaSeguroView {
	private String cuil;
	private String nombre;
	private Collection<SeguroView> segurosView;

	public CompaniaSeguroView(String cuit, String nombre) {
		this.cuil = cuit;
		this.nombre = nombre;
		this.segurosView = new ArrayList<SeguroView>();
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

	public Collection<SeguroView> getSegurosView() {
		return segurosView;
	}

	public void setSegurosView(Collection<SeguroView> segurosView) {
		this.segurosView = segurosView;
	}

	@Override
	public String toString() {
		return cuil + " - " + nombre;
	}

}
