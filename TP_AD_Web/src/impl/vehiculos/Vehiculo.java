package impl.vehiculos;

import impl.PersistentObject;
import impl.misc.Tamano;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import persistence.VehiculoDAO;

@Entity
@Table(name = "Vehiculos")
@Inheritance(strategy = InheritanceType.JOINED)
@AttributeOverride(name = "id", column = @Column(name = "id_vehiculo"))
public abstract class Vehiculo extends PersistentObject {
	/**
	 * 
	 */
	protected static final long serialVersionUID = 8056700348323209606L;

	@Column(name = "patente")
	protected String patente;

	@Embedded
	protected Tamano tamano;

	@Column(name = "peso")
	protected Float peso;

	@Column(name = "tara")
	protected Float tara;

	@Column(name = "tarifa")
	protected Float tarifa;

	@Column(name = "tipo")
	@Enumerated(EnumType.STRING)
	protected TipoVehiculo tipo;

	public Vehiculo() {

	}

	public TipoVehiculo getTipo() {
		return tipo;
	}

	public void setTipo(TipoVehiculo tipo) {
		this.tipo = tipo;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public Tamano getTamano() {
		return tamano;
	}

	public void setTamano(Tamano tamano) {
		this.tamano = tamano;
	}

	public Float getPeso() {
		return peso;
	}

	public void setPeso(Float peso) {
		this.peso = peso;
	}

	public Float getTara() {
		return tara;
	}

	public void setTara(Float tara) {
		this.tara = tara;
	}

	public Float getTarifa() {
		return tarifa;
	}

	public void setTarifa(Float tarifa) {
		this.tarifa = tarifa;
	}

	public abstract boolean estaDisponible();

	public void actualizarPrecio(float precio) {
		setTarifa(precio);
		VehiculoDAO.getInstance().update(this);
	}
}
