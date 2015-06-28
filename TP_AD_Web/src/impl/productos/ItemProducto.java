package impl.productos;

import impl.PersistentObject;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import views.productos.ItemProductoView;

@Entity
@Table(name = "Cargas_Productos")
@AttributeOverride(name = "id", column = @Column(name = "id_carga_producto"))
public class ItemProducto extends PersistentObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6147736562215085115L;
	@ManyToOne
	@JoinColumn(name = "id_producto")
	private Producto producto;
	@Column(name = "cantidad")
	private float cantidad;

	public ItemProducto(Producto producto, float cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public ItemProducto(ItemProductoView ipv) {
		producto = new Producto(ipv.getProducto());
		cantidad = ipv.getCantidad();
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public float calcularVolumenParcial() {
		return producto.getTamano().calcularVolumen() * cantidad;
	}

	public float calcularPesoParcial() {
		return producto.getPeso() * cantidad;
	}
}
