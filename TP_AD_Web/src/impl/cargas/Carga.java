package impl.cargas;

import impl.PersistentObject;
import impl.clientes.Cliente;
import impl.misc.Ubicacion;
import impl.productos.ItemProducto;
import impl.productos.Producto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import persistence.CargaDAO;
import util.Utilities;
import views.cargas.CargaView;
import views.productos.ItemProductoView;

@Entity
@Table(name = "Cargas")
@AttributeOverride(name = "id", column = @Column(name = "id_carga"))
public class Carga extends PersistentObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -875716574330563168L;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_carga")
	private Collection<ItemProducto> productos;
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	@ManyToOne
	@JoinColumn(name = "id_ubicacionOrigen")
	private Ubicacion origen;
	@ManyToOne
	@JoinColumn(name = "id_ubicacionDestino")
	private Ubicacion destino;
	@Column(name = "tipoCarga")
	@Enumerated(EnumType.STRING)
	private TipoCarga tipo;
	@Column(name = "estadoCarga")
	@Enumerated(EnumType.STRING)
	private EstadoCarga estadoCarga;
	@Column(name = "fechaMaximaEntrega")
	private Date fechaMaximaEntrega;
	@Column(name = "fechaProbableEntrega")
	private Date fechaProbableEntrega;
	@Column(name = "manifiesto")
	private String manifiesto;
	@Column(name = "retira_por_sucursal")
	private boolean retiraPorSucursal;

	public Carga() {
		productos = new ArrayList<ItemProducto>();
	}

	public Carga(CargaView c, Cliente cli) {
		tipo = TipoCarga.obtenerPorTipo(c.getTipo());
		fechaMaximaEntrega = Utilities.parseDate(c.getFechaMaximaEntrega());
		fechaProbableEntrega = Utilities.parseDate(c.getFechaProbableEntrega());
		manifiesto = c.getManifiesto();
		origen = new Ubicacion(c.getOrigen());
		destino = new Ubicacion(c.getDestino());
		estadoCarga = c.getEstadoCarga() != null ? EstadoCarga.valueOf(c.getEstadoCarga()) : EstadoCarga.EN_DEPOSITO;
		cliente = cli;
		retiraPorSucursal = c.isRetiraPorSucursal();
		productos = new ArrayList<ItemProducto>();
		for (ItemProductoView ipv : c.getProductos()) {
			productos.add(new ItemProducto(ipv));
		}
		id = CargaDAO.getInstance().insert(this);

	}

	public TipoCarga getTipo() {
		return tipo;
	}

	public void setTipo(TipoCarga tipo) {
		this.tipo = tipo;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getManifiesto() {
		return manifiesto;
	}

	public void setManifiesto(String manifiesto) {
		this.manifiesto = manifiesto;
	}

	public Ubicacion getOrigen() {
		return origen;
	}

	public void setOrigen(Ubicacion origen) {
		this.origen = origen;
	}

	public Ubicacion getDestino() {
		return destino;
	}

	public void setDestino(Ubicacion destino) {
		this.destino = destino;
	}

	public Collection<ItemProducto> getProductos() {
		return productos;
	}

	public void setProductos(Collection<ItemProducto> productos) {
		this.productos = productos;
	}

	public float calcularVolumenTotal() {
		float volumen = 0;
		for (ItemProducto p : productos)
			volumen += p.calcularPesoParcial();
		return volumen;
	}

	public float calcularPesoTotal() {
		float peso = 0;
		for (ItemProducto p : productos) {
			peso += p.calcularPesoParcial();
		}
		return peso;
	}

	public EstadoCarga getEstadoCarga() {
		return estadoCarga;
	}

	public void setEstadoCarga(EstadoCarga estadoCarga) {
		this.estadoCarga = estadoCarga;
	}

	public boolean isRetiraPorSucursal() {
		return retiraPorSucursal;
	}

	public void setRetiraPorSucursal(boolean retiraPorSucursal) {
		this.retiraPorSucursal = retiraPorSucursal;
	}

	public Float calcularCosto() {
		// uso $60 como costo base
		return 60f * calcularFactorProductos() * calcularFactorDistancia();
	}

	public Float calcularFactorProductos() { // public para testear
		Float total = 1f;
		for (ItemProducto ip : productos) {
			total += ip.getProducto().calcularFactorProducto() * ip.getCantidad();
		}
		return total;
	}

	public Float calcularFactorDistancia() { // public para testear
		return 1f + origen.calcularDistanciaEnKilometros(destino) * 0.001f;
		// costo aumenta 100% cada 1000km
	}

	public void agregarItemProducto(Producto producto, float cantidad) {
		if (productos == null)
			productos = new ArrayList<ItemProducto>();
		productos.add(new ItemProducto(producto, cantidad));
	}

	public CargaView getView() {
		Collection<ItemProductoView> p = new ArrayList<ItemProductoView>();
		for (ItemProducto ip : productos) {
			p.add(new ItemProductoView(ip.getProducto().getView(), ip.getCantidad()));
		}
		String fechaProbableString = fechaProbableEntrega == null ? "" : fechaProbableEntrega.toString();
		CargaView view = new CargaView(tipo.toString(), fechaMaximaEntrega.toString(), fechaProbableString, manifiesto, origen.getView(),
				destino.getView(), estadoCarga.toString(), p, retiraPorSucursal);
		view.setId(id);
		return view;
	}

	public void actualizarFechaProbable(Date fecha) {
		fechaProbableEntrega = fecha;
		CargaDAO.getInstance().update(this);
	}
}
