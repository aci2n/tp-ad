package views.viajes;

import java.util.List;

import views.GenericView;
import views.misc.UbicacionView;

public class ViajeView extends GenericView {

	private int id;
	private String fechaSalida;
	private String fechaLlegada;
	private UbicacionView origen;
	private UbicacionView destino;
	private List<ParadaIntermediaView> paradas;

	public ViajeView(String fechaSalida, String tipoLlegada,
			UbicacionView origen, UbicacionView destino) {
		this(fechaSalida, tipoLlegada, origen, destino, null);
	}
	
	public ViajeView(String fechaSalida, String tipoLlegada,
			UbicacionView origen, UbicacionView destino, List<ParadaIntermediaView> paradas) {

		this.fechaSalida = fechaSalida;
		this.fechaLlegada = tipoLlegada;
		this.origen = origen;
		this.destino = destino;
		this.setParadas(paradas);
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
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

	public List<ParadaIntermediaView> getParadas() {
		return paradas;
	}

	public void setParadas(List<ParadaIntermediaView> paradas) {
		this.paradas = paradas;
	}
}
