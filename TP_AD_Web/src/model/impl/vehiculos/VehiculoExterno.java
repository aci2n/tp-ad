package model.impl.vehiculos;

import javax.persistence.*;

import model.impl.misc.Tamano;
import model.impl.viajes.Proveedor;

@Entity
@Table(name = "VehiculosExternos")
public class VehiculoExterno extends Vehiculo {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6523653868930827919L;
	@ManyToOne
	@JoinColumn(name = "id_proveedor")
	private Proveedor proveedor;

	public VehiculoExterno() {

	}

	public VehiculoExterno(String patente, Tamano tamano, Float peso,
			Float tara, Float tarifa, TipoVehiculo tipo) {
		this.patente = patente;
		this.tamano = tamano;
		this.peso = peso;
		this.tara = tara;
		this.tarifa = tarifa;
		this.tipo = tipo;
	}

	public boolean estaDisponible() {
		return true;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

}
