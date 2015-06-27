package views.viajes;

public class ViajeOptimoView {
	private Integer viaje;
	private Float distanciaOptima;
	private Float duracionOptima;
	private Float costoOptimo;

	public ViajeOptimoView(Integer viaje, Float distanciaOptima, Float duracionOptima, Float costoOptimo) {
		this.viaje = viaje;
		this.distanciaOptima = distanciaOptima;
		this.duracionOptima = duracionOptima;
		this.costoOptimo = costoOptimo;
	}

	public Integer getViaje() {
		return viaje;
	}

	public void setViaje(Integer viaje) {
		this.viaje = viaje;
	}

	public Float getDistanciaOptima() {
		return distanciaOptima;
	}

	public void setDistanciaOptima(Float distanciaOptima) {
		this.distanciaOptima = distanciaOptima;
	}

	public Float getDuracionOptima() {
		return duracionOptima;
	}

	public void setDuracionOptima(Float duracionOptima) {
		this.duracionOptima = duracionOptima;
	}

	public Float getCostoOptimo() {
		return costoOptimo;
	}

	public void setCostoOptimo(Float costoOptimo) {
		this.costoOptimo = costoOptimo;
	}

}
