package views.viajes;

import views.GenericView;
import views.misc.UbicacionView;

public class ViajeView extends GenericView{
	private String fechaSalida;
	private String fechaLlegada;
	private UbicacionView origen;
	private UbicacionView destino;

	public ViajeView(String fechaSalida, String tipoLlegada, UbicacionView origen, UbicacionView destino) {
		this.fechaSalida = fechaSalida;
		this.fechaLlegada = tipoLlegada;
		this.origen = origen;
		this.destino = destino;
	}

	public String getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public String getFechaLlegada() {
		return fechaLlegada;
	}

	public void setFechaLlegada(String tipoLlegada) {
		this.fechaLlegada = tipoLlegada;
	}

	public UbicacionView getOrigen() {
		return origen;
	}

	public void setOrigen(UbicacionView origen) {
		this.origen = origen;
	}

	public UbicacionView getDestino() {
		return destino;
	}

	public void setDestino(UbicacionView destino) {
		this.destino = destino;
	}
}
