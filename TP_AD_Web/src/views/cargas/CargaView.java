package views.cargas;

import java.util.Collection;

import views.GenericView;
import views.misc.UbicacionView;
import views.productos.ItemProductoView;

public class CargaView extends GenericView {
	private static final long serialVersionUID = 1L;
	private Collection<ItemProductoView> productos;
	// private Cliente cliente;
	private UbicacionView origen;
	private UbicacionView destino;
	private String tipo;
	private String estadoCarga;
	private String fechaMaximaEntrega;
	private String fechaProbableEntrega;
	private String manifiesto;
	private boolean retiraPorSucursal;

	public CargaView() {
	}

	public CargaView(String tipoCarga, String fechaMaximaEntrega, String fechaProbableEntrega, String manifiesto, UbicacionView origen,
			UbicacionView destino, String estadoCarga, Collection<ItemProductoView> productos, boolean retiraPorSucursal) {
		this.tipo = tipoCarga;
		this.fechaMaximaEntrega = fechaMaximaEntrega;
		this.fechaProbableEntrega = fechaProbableEntrega == null ? "" : fechaProbableEntrega;
		this.manifiesto = manifiesto;
		this.origen = origen;
		this.destino = destino;
		this.estadoCarga = estadoCarga;
		// this.id = id;
		this.productos = productos;
		this.retiraPorSucursal = retiraPorSucursal;
	}

	public Collection<ItemProductoView> getProductos() {
		return productos;
	}

	public void setProductos(Collection<ItemProductoView> productos) {
		this.productos = productos;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEstadoCarga() {
		return estadoCarga;
	}

	public void setEstadoCarga(String estadoCarga) {
		this.estadoCarga = estadoCarga;
	}

	public String getFechaMaximaEntrega() {
		return fechaMaximaEntrega;
	}

	public void setFechaMaximaEntrega(String fechaMaximaEntrega) {
		this.fechaMaximaEntrega = fechaMaximaEntrega;
	}

	public String getFechaProbableEntrega() {
		return fechaProbableEntrega;
	}

	public void setFechaProbableEntrega(String fechaProbableEntrega) {
		this.fechaProbableEntrega = fechaProbableEntrega;
	}

	public String getManifiesto() {
		return manifiesto;
	}

	public void setManifiesto(String manifiesto) {
		this.manifiesto = manifiesto;
	}

	public boolean isRetiraPorSucursal() {
		return retiraPorSucursal;
	}

	public void setRetiraPorSucursal(boolean retiraPorSucursal) {
		this.retiraPorSucursal = retiraPorSucursal;
	}
}
