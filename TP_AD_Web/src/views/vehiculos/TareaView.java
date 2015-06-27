package views.vehiculos;

import views.GenericView;

public class TareaView extends GenericView{
	Float kilometraje;
	String fechaEntrega;
	String fechaDevolucion;

	public TareaView(Float kilometraje, String fechaEntrega, String fechaDevolucion) {

		this.kilometraje = kilometraje;
		this.fechaEntrega = fechaEntrega;
		this.fechaDevolucion = fechaDevolucion;
	}

	public Float getKilometraje() {
		return kilometraje;
	}

	public void setKilometraje(Float kilometraje) {
		this.kilometraje = kilometraje;
	}

	public String getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public String getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(String fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

}
