package views.productos;

import views.GenericView;

public class ItemProductoView extends GenericView {
	private ProductoView producto;
	private float cantidad;

	public ItemProductoView(ProductoView producto, float cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public ProductoView getProducto() {
		return producto;
	}

	public void setProducto(ProductoView producto) {
		this.producto = producto;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}
}
