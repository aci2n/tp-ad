package impl.sucursales;

import impl.PersistentObject;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import persistence.DistanciaEntreSucursalesDAO;

@Entity
@Table(name = "Distancia_Sucursales")
@AttributeOverride(name = "id", column = @Column(name = "id_distancia_sucursales"))
public class DistanciaEntreSucursales extends PersistentObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7127719412272598512L;
	@ManyToOne
	@JoinColumn(name = "id_sucursal_a")
	private Sucursal sucursalA;
	@ManyToOne
	@JoinColumn(name = "id_sucursal_b")
	private Sucursal sucursalB;
	@Column(name = "distancia")
	private float distanciaEnKm;
	@Column(name = "duracion")
	private float duracionEnHoras;
	@Column(name = "costo")
	private float costo;

	public DistanciaEntreSucursales(Sucursal sucursalA, Sucursal sucursalB,
			float distanciaEnKm, float duracionEnHoras, float costo) {
		this.sucursalA = sucursalA;
		this.sucursalB = sucursalB;
		this.distanciaEnKm = distanciaEnKm;
		this.duracionEnHoras = duracionEnHoras;
		this.costo = costo;
		this.id = DistanciaEntreSucursalesDAO.getInstance().insert(this);
	}

	public DistanciaEntreSucursales() {

	}

	public Sucursal getSucursalA() {
		return sucursalA;
	}

	public void setSucursalA(Sucursal sucursalA) {
		this.sucursalA = sucursalA;
	}

	public Sucursal getSucursalB() {
		return sucursalB;
	}

	public void setSucursalB(Sucursal sucursalB) {
		this.sucursalB = sucursalB;
	}

	public float getDistanciaEnKm() {
		return distanciaEnKm;
	}

	public void setDistanciaEnKm(float distanciaEnKm) {
		this.distanciaEnKm = distanciaEnKm;
	}

	public float getDuracionEnHoras() {
		return duracionEnHoras;
	}

	public void setDuracionEnHoras(float duracionEnHoras) {
		this.duracionEnHoras = duracionEnHoras;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

}
