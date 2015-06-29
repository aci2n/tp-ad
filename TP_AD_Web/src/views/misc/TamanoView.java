package views.misc;

import views.GenericView;


public class TamanoView extends GenericView{
	private Float profundidad;
	private Float alto;
	private Float ancho;

	public TamanoView() {
		
	}
	
	public TamanoView(Float alto, Float ancho, Float profundidad) {
		this.profundidad = profundidad;
		this.alto = alto;
		this.ancho = ancho;
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
}
