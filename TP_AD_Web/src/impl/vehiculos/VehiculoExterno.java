package impl.vehiculos;

import impl.misc.Tamano;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import persistence.SucursalDAO;
import views.vehiculos.VehiculoExternoView;

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

	public VehiculoExterno(Proveedor p, VehiculoExternoView v) {
		patente = v.getPatente();
		tamano = new Tamano(v.getTamano());
		peso = v.getPeso();
		tara = v.getTara();
		tarifa = v.getTarifa();
		proveedor = p;
		tipo = TipoVehiculo.obtenerPorTipo(v.getTipo());
		id = SucursalDAO.getInstance().insert(this);
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

	public VehiculoExternoView getView() {

		return new VehiculoExternoView(patente, tamano.getView(), peso, tara,
				tarifa, tipo.toString());
	}

}
