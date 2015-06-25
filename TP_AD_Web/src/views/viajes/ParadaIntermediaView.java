package views.viajes;

import views.misc.UbicacionView;

public class ParadaIntermediaView {
	private String llegada;
	private UbicacionView ubicacion;

	public ParadaIntermediaView(String llegada, UbicacionView ubicacion) {
		this.llegada = llegada;
		this.ubicacion = ubicacion;
	}

	public String getLlegada() {
		return llegada;
	}

	public void setLlegada(String llegada) {
		this.llegada = llegada;
	}

	public UbicacionView getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(UbicacionView ubicacion) {
		this.ubicacion = ubicacion;
	}
}
