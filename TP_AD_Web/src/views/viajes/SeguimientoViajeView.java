package views.viajes;

import impl.viajes.Viaje;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class SeguimientoViajeView {

	private ViajeView viaje;
	private Integer idParadaIntemedia;
	private Date fecha;
	private boolean checked;

	public SeguimientoViajeView(ViajeView viaje, Integer idParadaIntermedia,
			Date fecha, boolean checked) {

		this.viaje = viaje;
		this.fecha = fecha;
		this.idParadaIntemedia = idParadaIntermedia;
		this.checked = checked;
	}

	public ViajeView getViaje() {
		return viaje;
	}

	public void setViaje(ViajeView viaje) {
		this.viaje = viaje;
	}

	public Integer getIdParadaIntemedia() {
		return idParadaIntemedia;
	}

	public void setIdParadaIntemedia(Integer idParadaIntemedia) {
		this.idParadaIntemedia = idParadaIntemedia;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}
