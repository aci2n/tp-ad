package impl.cargas;

import impl.PersistentObject;

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
	@Column(name = "id_viaje")
	private int idViaje;

	public SeguimientoCarga() {

	}

	public SeguimientoCarga(Carga carga, EstadoCarga estadoCarga,
			int idViaje) {
		this.carga = carga;
		this.estadoCarga = estadoCarga;
		this.idViaje = idViaje;
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

	public int getIdEstadoCarga() {
		return idViaje;
	}

	public void setIdEstadoCarga(int idViaje) {
		this.idViaje = idViaje;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void actualizarEstado(EstadoCarga estadoCarga, int idViaje) {
		this.estadoCarga = estadoCarga;
		this.idViaje = idViaje;
	}

	public SeguimientoCargaView getView() {
		
		return new SeguimientoCargaView(carga.getView(),
				estadoCarga.toString(), idViaje);
	}
}
