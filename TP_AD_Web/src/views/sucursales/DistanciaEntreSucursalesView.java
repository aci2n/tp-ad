package views.sucursales;

import views.GenericView;

public class DistanciaEntreSucursalesView extends GenericView{
	private Integer sucursalA;
	private Integer sucursalB;
	private Float duracionEnKm;
	private Float duracionEnHoras;
	private Float costo;

	public DistanciaEntreSucursalesView(Integer sucursalA, Integer sucursalB, Float duracionEnKm, Float duracionEnHoras, Float costo) {

		this.sucursalA = sucursalA;
		this.sucursalB = sucursalB;
		this.duracionEnKm = duracionEnKm;
		this.duracionEnHoras = duracionEnHoras;
		this.costo = costo;
	}

	public Integer getSucursalA() {
		return sucursalA;
	}

	public void setSucursalA(Integer sucursalA) {
		this.sucursalA = sucursalA;
	}

	public Integer getSucursalB() {
		return sucursalB;
	}

	public void setSucursalB(Integer sucursalB) {
		this.sucursalB = sucursalB;
	}

	public Float getDuracionEnKm() {
		return duracionEnKm;
	}

	public void setDuracionEnKm(Float duracionEnKm) {
		this.duracionEnKm = duracionEnKm;
	}

	public Float getDuracionEnHoras() {
		return duracionEnHoras;
	}

	public void setDuracionEnHoras(Float duracionEnHoras) {
		this.duracionEnHoras = duracionEnHoras;
	}

	public Float getCosto() {
		return costo;
	}

	public void setCosto(Float costo) {
		this.costo = costo;
	}

}
