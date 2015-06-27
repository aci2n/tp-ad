package impl.misc;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import views.misc.CoordenadaView;

@Embeddable
public class Coordenada {
	@Column(name = "latitud")
	private float latitud;
	@Column(name = "longitud")
	private float longitud;

	public Coordenada() {

	}

	public Coordenada(float latitud, float longitud) {
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

	public float calcularDistanciaEnKilometros(Coordenada coordenada) {
		float kilometrosPorGrado = 111.12F;

		float difLatitud = Math.abs(this.latitud - coordenada.getLatitud());
		float difLongitud = Math.abs(this.longitud - coordenada.getLongitud());

		float cateto = (float) Math.sqrt((difLatitud * difLatitud) + (difLongitud * difLongitud));

		return cateto * kilometrosPorGrado;
	}

	public CoordenadaView getView() {

		return new CoordenadaView(latitud, longitud);
	}

	@Override
	public boolean equals(Object o) {
		if (o != null && o instanceof Coordenada) {
			Coordenada c = (Coordenada) o;
			return c.getLatitud().equals(latitud) && c.getLongitud().equals(longitud);
		}
		return false;
	}
}
