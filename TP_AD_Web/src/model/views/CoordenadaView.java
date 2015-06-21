package model.views;


public class CoordenadaView {
	private float latitud;
	private float longitud;

	public CoordenadaView() {
	}

	public CoordenadaView(float latitud, float longitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public Float getLatitud() {
		return latitud;
	}

	public void setLatitud(Float latitud) {
		this.latitud = latitud;
	}

	public Float getLongitud() {
		return longitud;
	}

	public void setLongitud(Float longitud) {
		this.longitud = longitud;
	}
}
