package views.cargas;

import java.util.Date;

import impl.cargas.Carga;
import impl.cargas.EstadoCarga;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import views.GenericView;

public class SeguimientoCargaView extends GenericView {

	private static final long serialVersionUID = 1L;
	private CargaView carga;
	private String estadoCarga;
	private Integer idViaje;
	private Date fecha;
	private Integer idSucursal;

	public SeguimientoCargaView() {

	}

	public SeguimientoCargaView(CargaView carga, String estadoCarga, Integer idViaje, Date fecha) {
		this.carga = carga;
		this.estadoCarga = estadoCarga;
		this.idViaje = idViaje;
		this.fecha = fecha;
	}

	public CargaView getCarga() {
		return carga;
	}

	public void setCarga(CargaView carga) {
		this.carga = carga;
	}

	public String getEstadoCarga() {
		return estadoCarga;
	}

	public void setEstadoCarga(String estadoCarga) {
		this.estadoCarga = estadoCarga;
	}

	public Integer getIdViaje() {
		return idViaje;
	}

	public void setIdViaje(Integer idViaje) {
		this.idViaje = idViaje;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(Integer idSucursal) {
		this.idSucursal = idSucursal;
	}

}
