package views.cargas;

import impl.cargas.EstadoCarga;
import impl.cargas.TipoCarga;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import views.GenericView;
import views.misc.UbicacionView;
import views.productos.ItemProductoView;

public class CargaView extends GenericView{

	private int id;
	private Collection<ItemProductoView> productos;
	// private Cliente cliente;
	private UbicacionView origen;
	private UbicacionView destino;
	private TipoCarga tipo;
	private EstadoCarga estadoCarga;
	private Date fechaMaximaEntrega;
	private Date fechaProbableEntrega;
	private String manifiesto;

	public CargaView() {

	}
	
	public CargaView(TipoCarga tipoCarga, Date fechaMaximaEntrega,
			Date fechaProbableEntrega, String manifiesto, UbicacionView origen,
			UbicacionView destino, EstadoCarga estadoCarga, int id) {
		this.id = id;
		this.tipo = tipoCarga;
		this.fechaMaximaEntrega = fechaMaximaEntrega;
		this.fechaProbableEntrega = fechaProbableEntrega;
		this.manifiesto = manifiesto;
		this.origen = origen;
		this.destino = destino;
		this.estadoCarga = estadoCarga;
		this.productos = new ArrayList<ItemProductoView>();
	}

	public Collection<ItemProductoView> getProductos() {
		return productos;
	}

	public void setProductos(Collection<ItemProductoView> productos) {
		this.productos = productos;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
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

	public TipoCarga getTipo() {
		return tipo;
	}

	public void setTipo(TipoCarga tipo) {
		this.tipo = tipo;
	}

	public EstadoCarga getEstadoCarga() {
		return estadoCarga;
	}

	public void setEstadoCarga(EstadoCarga estadoCarga) {
		this.estadoCarga = estadoCarga;
	}

	public Date getFechaMaximaEntrega() {
		return fechaMaximaEntrega;
	}

	public void setFechaMaximaEntrega(Date fechaMaximaEntrega) {
		this.fechaMaximaEntrega = fechaMaximaEntrega;
	}

	public Date getFechaProbableEntrega() {
		return fechaProbableEntrega;
	}

	public void setFechaProbableEntrega(Date fechaProbableEntrega) {
		this.fechaProbableEntrega = fechaProbableEntrega;
	}

	public String getManifiesto() {
		return manifiesto;
	}

	public void setManifiesto(String manifiesto) {
		this.manifiesto = manifiesto;
	}

}
