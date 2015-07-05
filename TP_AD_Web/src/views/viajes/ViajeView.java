package views.viajes;

import java.io.Serializable;
import java.util.List;

import views.GenericView;
import views.misc.UbicacionView;

public class ViajeView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9088830453271912313L;
	private Integer id;
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
			UbicacionView origen, UbicacionView destino,
			List<ParadaIntermediaView> paradas) {

		this.fechaSalida = fechaSalida;
		this.fechaLlegada = tipoLlegada;
		this.origen = origen;
		this.destino = destino;
		this.setParadas(paradas);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
