package impl.misc;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import views.misc.TamanoView;

@Embeddable
public class Tamano {
	@Column(name = "profundidad")
	private Float profundidad;
	@Column(name = "alto")
	private Float alto;
	@Column(name = "ancho")
	private Float ancho;

	public Tamano() {
		
	}

	public Tamano(TamanoView tamano) {
		profundidad = tamano.getProfundidad();
		alto = tamano.getAlto();
		ancho = tamano.getAlto();
	}

	public Float getProfundidad() {
		return profundidad;
	}

	public void setProfundidad(Float profundidad) {
		this.profundidad = profundidad;
	}

	public Float getAlto() {
		return alto;
	}

	public void setAlto(Float alto) {
		this.alto = alto;
	}

	public Float getAncho() {
		return ancho;
	}

	public void setAncho(Float ancho) {
		this.ancho = ancho;
	}

	public Float calcularVolumen() {
		return profundidad * alto * ancho;
	}
	
	public TamanoView getView(){
		return new TamanoView(alto, ancho, profundidad);	
	}
}
