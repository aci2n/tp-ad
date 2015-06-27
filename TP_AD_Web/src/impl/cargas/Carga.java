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

import views.cargas.CargaView;

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

	public Carga(TipoCarga tipoCarga, Date fechaMaximaEntrega,
			Date fechaProbableEntrega, Cliente cliente, String manifiesto,
			Ubicacion origen, Ubicacion destino, EstadoCarga estadoCarga) {
		this.tipo = tipoCarga;
		this.fechaMaximaEntrega = fechaMaximaEntrega;
		this.fechaProbableEntrega = fechaProbableEntrega;
		this.cliente = cliente;
		this.manifiesto = manifiesto;
		this.origen = origen;
		this.destino = destino;
		this.estadoCarga = estadoCarga;
		this.productos = new ArrayList<ItemProducto>();
	}

	public Carga() {

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

	public Float calcularCosto() {
		// uso $60 como costo base
		return 60f * calcularFactorProductos() * calcularFactorDistancia();
	}

	public Float calcularFactorProductos() { // public para testear
		Float total = 1f;
		for (ItemProducto ip : productos) {
			total += ip.getProducto().calcularFactorProducto()
					* ip.getCantidad();
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

		CargaView view = new CargaView(tipo, fechaMaximaEntrega,
				fechaProbableEntrega, manifiesto, origen.getView(),
				destino.getView(), estadoCarga, id);
		
		return view;

	}

}
