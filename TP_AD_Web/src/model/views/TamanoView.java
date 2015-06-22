package model.views;


public class TamanoView {
	private Float profundidad;
	private Float alto;
	private Float ancho;

	public TamanoView() {
		
	}
	
	public TamanoView(Float i, Float j, Float k) {
		profundidad = i;
		alto = j;
		ancho = k;
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
