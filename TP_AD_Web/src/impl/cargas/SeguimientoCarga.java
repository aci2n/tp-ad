package impl.cargas;

import impl.PersistentObject;
import impl.sucursales.Sucursal;
import impl.viajes.Viaje;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import views.cargas.SeguimientoCargaView;

@Entity
@Table(name = "SeguimientoCargas")
@AttributeOverride(name = "id", column = @Column(name = "id_seguimientoCarga"))
public class SeguimientoCarga extends PersistentObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4200679741545305740L;

	@OneToOne
	@JoinColumn(name = "id_carga")
	private Carga carga;
	@Column(name = "estadoCarga")
	@Enumerated(EnumType.STRING)
	private EstadoCarga estadoCarga;
	@OneToOne
	@JoinColumn(name = "id_viaje")
	private Viaje viaje;
	@OneToOne
	@JoinColumn(name = "id_sucursal")
	private Sucursal sucursal;
	@Column(name = "fecha")
	private Date fecha;

	public SeguimientoCarga() {

	}

	public SeguimientoCarga(Carga carga, EstadoCarga estadoCarga, Sucursal sucursal, Viaje viaje) {
		this.carga = carga;
		this.estadoCarga = estadoCarga;
		this.viaje = viaje;
		this.sucursal = sucursal;
		fecha = new Date();
	}

	public Viaje getViaje() {
		return viaje;
	}

	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Carga getCarga() {
		return carga;
	}

	public void setCarga(Carga carga) {
		this.carga = carga;
	}

	public EstadoCarga getEstadoCarga() {
		return estadoCarga;
	}

	public void setEstadoCarga(EstadoCarga estadoCarga) {
		this.estadoCarga = estadoCarga;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void actualizarEstado(EstadoCarga estadoCarga, Viaje viaje) {
		this.estadoCarga = estadoCarga;
		this.viaje = viaje;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public SeguimientoCargaView getView() {

		SeguimientoCargaView sv = new SeguimientoCargaView(carga.getView(),
				estadoCarga.toString(), null, fecha);
		if (viaje != null) {
			sv.setIdViaje(viaje.getId());
		}
		if(sucursal != null)
			sv.setIdViaje(sucursal.getId());
		return sv;
	}
}
